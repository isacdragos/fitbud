package com.example.fitbudbackend.workout.repositories;

import com.example.fitbudbackend.auth.entities.User;
import com.example.fitbudbackend.workout.entities.Workout;
import com.example.fitbudbackend.workout.entities.WorkoutDay;
import com.example.fitbudbackend.workout.entities.DayOfWeek;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutDayRepository extends JpaRepository<WorkoutDay, Long> {
    Optional<WorkoutDay> findByWorkoutAndDay(Workout workout, DayOfWeek day);
    Optional<WorkoutDay> findByIdAndWorkout_User(Long id, User user);
}
