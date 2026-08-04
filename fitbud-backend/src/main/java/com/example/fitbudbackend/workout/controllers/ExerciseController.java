package com.example.fitbudbackend.workout.controllers;

import com.example.fitbudbackend.workout.dtos.ExerciseResponse;
import com.example.fitbudbackend.workout.enums.MuscleGroup;
import com.example.fitbudbackend.workout.services.ExerciseService;
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
