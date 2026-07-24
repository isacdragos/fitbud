package com.example.fitbudbackend.exercise.config;

import com.example.fitbudbackend.exercise.entities.Exercise;
import com.example.fitbudbackend.exercise.enums.MuscleGroup;
import com.example.fitbudbackend.exercise.repositories.ExerciseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExerciseSeeder implements CommandLineRunner {

    private final ExerciseRepository exerciseRepository;

    @Override
    public void run(String... args) {

        if (exerciseRepository.count() > 0) {
            return;
        }

        exerciseRepository.saveAll(List.of(

                Exercise.builder()
                        .name("Bench Press")
                        .emoji("🏋️")
                        .primaryMuscles(List.of(MuscleGroup.CHEST))
                        .secondaryMuscles(List.of(
                                MuscleGroup.TRICEPS,
                                MuscleGroup.SHOULDERS))
                        .build(),

                Exercise.builder()
                        .name("Incline Bench Press")
                        .emoji("🏋️")
                        .primaryMuscles(List.of(MuscleGroup.CHEST))
                        .secondaryMuscles(List.of(
                                MuscleGroup.TRICEPS,
                                MuscleGroup.SHOULDERS))
                        .build(),

                Exercise.builder()
                        .name("Pull Up")
                        .emoji("⬆️")
                        .primaryMuscles(List.of(MuscleGroup.BACK))
                        .secondaryMuscles(List.of(
                                MuscleGroup.BICEPS))
                        .build(),

                Exercise.builder()
                        .name("Barbell Row")
                        .emoji("🚣")
                        .primaryMuscles(List.of(MuscleGroup.BACK))
                        .secondaryMuscles(List.of(
                                MuscleGroup.BICEPS))
                        .build(),

                Exercise.builder()
                        .name("Squat")
                        .emoji("🏋️")
                        .primaryMuscles(List.of(
                                MuscleGroup.QUADS,
                                MuscleGroup.GLUTES))
                        .secondaryMuscles(List.of(
                                MuscleGroup.CORE))
                        .build()

        ));

    }

}