package com.example.fitbudbackend.exercise.controllers;

import com.example.fitbudbackend.exercise.dtos.ExerciseResponse;
import com.example.fitbudbackend.exercise.enums.MuscleGroup;
import com.example.fitbudbackend.exercise.services.ExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
public class ExerciseController {

    private final ExerciseService exerciseService;

    @GetMapping
    public List<ExerciseResponse> getExercises(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) MuscleGroup muscle
    ) {
        return exerciseService.getExercises(search, muscle);
    }

}
