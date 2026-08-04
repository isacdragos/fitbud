package com.example.fitbudbackend.workout.dtos;

import lombok.Data;

@Data
public class ReorderExerciseRequest {
    private Long workoutExerciseId;
    private Integer position;
}
