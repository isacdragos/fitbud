package com.example.fitbudbackend.workout.dtos;

import lombok.Data;

@Data
public class AddWorkoutExerciseRequest {
    private Long exerciseId;
    private String customName;
    private Integer sets;
    private Integer reps;
    private Double weight;
}
