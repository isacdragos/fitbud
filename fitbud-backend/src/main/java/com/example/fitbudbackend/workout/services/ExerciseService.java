package com.example.fitbudbackend.workout.services;

import com.example.fitbudbackend.workout.dtos.ExerciseResponse;
import com.example.fitbudbackend.workout.entities.Exercise;
import com.example.fitbudbackend.workout.enums.MuscleGroup;
import com.example.fitbudbackend.workout.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;

    public List<ExerciseResponse> getExercises(String search, MuscleGroup muscle) {

        List<Exercise> exercises;

        if (search != null && !search.isBlank() && muscle != null) {
            exercises = exerciseRepository.findByNameContainingIgnoreCaseAndPrimaryMusclesContaining(search, muscle);
        } else if (search != null && !search.isBlank()) {
            exercises = exerciseRepository.findByNameContainingIgnoreCase(search);
        } else if (muscle != null) {
            exercises = exerciseRepository.findByPrimaryMusclesContaining(muscle);
        } else {
            exercises = exerciseRepository.findAll();
        }

        return exercises.stream()
                .map(this::toResponse)
                .toList();
    }

    private ExerciseResponse toResponse(Exercise exercise) {
        return new ExerciseResponse(
                exercise.getId(),
                exercise.getName(),
                exercise.getEmoji(),
                exercise.getPrimaryMuscles(),
                exercise.getSecondaryMuscles()
        );
    }
}
