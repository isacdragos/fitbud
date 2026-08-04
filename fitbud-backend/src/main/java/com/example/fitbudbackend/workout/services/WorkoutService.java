package com.example.fitbudbackend.workout.services;

import com.example.fitbudbackend.auth.entities.User;
import com.example.fitbudbackend.auth.repositories.UserRepository;
import com.example.fitbudbackend.workout.dtos.*;
import com.example.fitbudbackend.workout.entities.Exercise;
import com.example.fitbudbackend.workout.entities.Workout;
import com.example.fitbudbackend.workout.entities.WorkoutDay;
import com.example.fitbudbackend.workout.entities.WorkoutExercise;
import com.example.fitbudbackend.workout.exceptions.ExerciseNotFoundException;
import com.example.fitbudbackend.workout.exceptions.WorkoutDayAlreadyExistsException;
import com.example.fitbudbackend.workout.exceptions.WorkoutDayNotFoundException;
import com.example.fitbudbackend.workout.exceptions.WorkoutNotFoundException;
import com.example.fitbudbackend.workout.repositories.ExerciseRepository;
import com.example.fitbudbackend.workout.repositories.WorkoutDayRepository;
import com.example.fitbudbackend.workout.repositories.WorkoutExerciseRepository;
import com.example.fitbudbackend.workout.repositories.WorkoutRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkoutService {

    private final WorkoutRepository workoutRepository;
    private final WorkoutDayRepository workoutDayRepository;
    private final WorkoutExerciseRepository workoutExerciseRepository;
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;

    public WorkoutResponse createWorkout(CreateWorkoutRequest request, Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Workout workout = Workout.builder()
                .name(request.getName())
                .user(user)
                .build();

        workout = workoutRepository.save(workout);

        return toResponse(workout);
    }

    public List<WorkoutResponse> getWorkouts(Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return workoutRepository.findByUser(user)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public WorkoutResponse addDay(Long workoutId, AddWorkoutDayRequest request, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        Workout workout = workoutRepository
                .findByIdAndUser(workoutId, user)
                .orElseThrow(WorkoutNotFoundException::new);

        boolean exists = workoutDayRepository
                .findByWorkoutAndDay(workout, request.getDay())
                .isPresent();

        if (exists) {
            throw new WorkoutDayAlreadyExistsException();
        }

        WorkoutDay workoutDay = WorkoutDay.builder()
                .workout(workout)
                .day(request.getDay())
                .build();

        workoutDayRepository.save(workoutDay);

        return toResponse(workout);
    }

    public void deleteDay(Long workoutDayId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        WorkoutDay workoutDay = workoutDayRepository
                .findByIdAndWorkout_User(workoutDayId, user)
                .orElseThrow(WorkoutDayNotFoundException::new);

        workoutDayRepository.delete(workoutDay);
    }

    public WorkoutResponse addExercise(Long workoutDayId,
                                       AddWorkoutExerciseRequest request,
                                       Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        WorkoutDay workoutDay = workoutDayRepository
                .findByIdAndWorkout_User(workoutDayId, user)
                .orElseThrow(WorkoutDayNotFoundException::new);

        if (request.getExerciseId() == null && request.getCustomName() == null)
            throw new IllegalArgumentException("Either exerciseId or customName must be provided.");
        if (request.getExerciseId() != null && request.getCustomName() != null)
            throw new IllegalArgumentException("Provide either exerciseId or customName.");

        Exercise exercise = null;
        if (request.getExerciseId() != null) {
            exercise = exerciseRepository.findById(request.getExerciseId())
                    .orElseThrow(() -> new ExerciseNotFoundException(request.getExerciseId()));
        }
        int position = workoutExerciseRepository
                .findByWorkoutDayOrderByExerciseOrder(workoutDay)
                .size() + 1;

        WorkoutExercise workoutExercise = WorkoutExercise.builder()
                .workoutDay(workoutDay)
                .exercise(exercise)
                .customName(request.getCustomName())
                .sets(request.getSets())
                .reps(request.getReps())
                .weight(request.getWeight())
                .exerciseOrder(position)
                .build();
        workoutExerciseRepository.save(workoutExercise);
        return toResponse(workoutDay.getWorkout());
    }

    public void removeExercise(Long workoutExerciseId, Authentication authentication) {
        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        WorkoutExercise workoutExercise = workoutExerciseRepository
                .findByIdAndWorkoutDay_Workout_User(workoutExerciseId, user)
                .orElseThrow(() -> new ExerciseNotFoundException(workoutExerciseId));

        workoutExerciseRepository.delete(workoutExercise);

        WorkoutDay workoutDay = workoutExercise.getWorkoutDay();
        List<WorkoutExercise> exercises = workoutExerciseRepository.findByWorkoutDayOrderByExerciseOrder(workoutDay);
        for (int i = 0; i < exercises.size(); i++) {
            exercises.get(i).setExerciseOrder(i + 1);
        }
        workoutExerciseRepository.saveAll(exercises);
    }

    public void reorderExercises(Long workoutDayId,
            List<ReorderExerciseRequest> request,
            Authentication authentication) {

        User user = userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));

        WorkoutDay workoutDay = workoutDayRepository
                .findByIdAndWorkout_User(workoutDayId, user)
                .orElseThrow(WorkoutDayNotFoundException::new);

        List<WorkoutExercise> exercises =
                workoutExerciseRepository.findByWorkoutDayOrderByExerciseOrder(workoutDay);

        if (request.size() != exercises.size()) {
            throw new IllegalArgumentException("Invalid exercise list.");
        }

        Map<Long, WorkoutExercise> exerciseMap = exercises.stream()
                .collect(Collectors.toMap(
                        WorkoutExercise::getId,
                        Function.identity()
                ));

        for (ReorderExerciseRequest reorder : request) {
            WorkoutExercise exercise = exerciseMap.get(reorder.getWorkoutExerciseId());

            if (exercise == null) {
                throw new ExerciseNotFoundException(reorder.getWorkoutExerciseId());
            }

            exercise.setExerciseOrder(reorder.getPosition());
        }
        workoutExerciseRepository.saveAll(exercises);
    }

    private WorkoutResponse toResponse(Workout workout) {
        return new WorkoutResponse(
                workout.getId(),
                workout.getName(),
                new ArrayList<>()
        );
    }

}
