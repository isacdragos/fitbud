package com.example.fitbudbackend.workout.repositories;

import com.example.fitbudbackend.auth.entities.User;
import com.example.fitbudbackend.workout.entities.WorkoutDay;
import com.example.fitbudbackend.workout.entities.WorkoutExercise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutExerciseRepository extends JpaRepository<WorkoutExercise, Long> {
    List<WorkoutExercise> findByWorkoutDayOrderByExerciseOrder(WorkoutDay workoutDay);
    Optional<WorkoutExercise> findByIdAndWorkoutDay_Workout_User(Long id, User user);
}