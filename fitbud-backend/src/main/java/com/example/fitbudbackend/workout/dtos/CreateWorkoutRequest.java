package com.example.fitbudbackend.workout.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateWorkoutRequest {
    @NotBlank
    private String name;
}
