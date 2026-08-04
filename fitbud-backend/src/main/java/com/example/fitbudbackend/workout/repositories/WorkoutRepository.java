package com.example.fitbudbackend.workout.repositories;

import com.example.fitbudbackend.auth.entities.User;
import com.example.fitbudbackend.workout.entities.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Long> {
    List<Workout> findByUser(User user);
    Optional<Workout> findByIdAndUser(Long id, User user);
}
