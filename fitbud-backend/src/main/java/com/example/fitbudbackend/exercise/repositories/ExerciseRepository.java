package com.example.fitbudbackend.exercise.repositories;

import com.example.fitbudbackend.exercise.entities.Exercise;
import com.example.fitbudbackend.exercise.enums.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findByPrimaryMusclesContaining(MuscleGroup muscle);
    List<Exercise> findByNameContainingIgnoreCase(String name);
    List<Exercise> findByNameContainingIgnoreCaseAndPrimaryMusclesContaining(String name, MuscleGroup muscle);
}
