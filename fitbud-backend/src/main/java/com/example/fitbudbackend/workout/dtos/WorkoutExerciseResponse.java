package com.example.fitbudbackend.workout.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkoutExerciseResponse {
    private Long id;
    private Long exerciseId;
    private String exerciseName;
    private String emoji;
    private String customName;
    private Integer sets;
    private Integer reps;
    private Double weight;
    private Integer position;
}