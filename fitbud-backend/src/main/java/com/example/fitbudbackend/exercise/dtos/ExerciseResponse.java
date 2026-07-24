package com.example.fitbudbackend.exercise.dtos;

import com.example.fitbudbackend.exercise.enums.MuscleGroup;
import lombok.Data;
import lombok.AllArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class ExerciseResponse {

    private Long id;

    private String name;

    private String emoji;

    private List<MuscleGroup> primaryMuscles;

    private List<MuscleGroup> secondaryMuscles;

}
