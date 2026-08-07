package com.example.fitbudbackend.workout.controllers;

import com.example.fitbudbackend.workout.dtos.*;
import com.example.fitbudbackend.workout.services.WorkoutService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class WorkoutController {

    private final WorkoutService workoutService;

    @PostMapping("/workouts")
    public WorkoutResponse createWorkout(
            @Valid @RequestBody CreateWorkoutRequest request,
            Authentication authentication) {
        return workoutService.createWorkout(request, authentication);
    }

    @GetMapping("/workouts")
    public List<WorkoutResponse> getWorkouts(
            Authentication authentication) {
        return workoutService.getWorkouts(authentication);
    }

    @GetMapping("/workouts/{workoutId}")
    public WorkoutResponse getWorkout(
            @PathVariable Long workoutId,
            Authentication authentication) {
        return workoutService.getWorkout(workoutId, authentication);
    }

    @DeleteMapping("/workouts/{workoutId}")
    public void deleteWorkout(
            @PathVariable Long workoutId,
            Authentication authentication) {
        workoutService.deleteWorkout(workoutId, authentication);
    }

    @PostMapping("/workouts/{workoutId}/days")
    public WorkoutResponse addDay(
            @PathVariable Long workoutId,
            @Valid @RequestBody AddWorkoutDayRequest request,
            Authentication authentication) {
        return workoutService.addDay(workoutId, request, authentication);
    }

    @DeleteMapping("/workout-days/{workoutDayId}")
    public void deleteDay(
            @PathVariable Long workoutDayId,
            Authentication authentication) {
        workoutService.deleteDay(workoutDayId, authentication);
    }

    @PostMapping("/workout-days/{workoutDayId}/exercises")
    public WorkoutResponse addExercise(
            @PathVariable Long workoutDayId,
            @Valid @RequestBody AddWorkoutExerciseRequest request,
            Authentication authentication) {
        return workoutService.addExercise(
                workoutDayId,
                request,
                authentication
        );
    }

    @DeleteMapping("/workout-exercises/{workoutExerciseId}")
    public void removeExercise(
            @PathVariable Long workoutExerciseId,
            Authentication authentication) {
        workoutService.removeExercise(
                workoutExerciseId,
                authentication
        );
    }

    @PutMapping("/workout-days/{workoutDayId}/reorder")
    public void reorderExercises(
            @PathVariable Long workoutDayId,
            @RequestBody List<ReorderExerciseRequest> request,
            Authentication authentication) {
        workoutService.reorderExercises(
                workoutDayId,
                request,
                authentication
        );
    }
}
