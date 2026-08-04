package com.example.fitbudbackend.workout.exceptions;

public class WorkoutNotFoundException extends RuntimeException {
    public WorkoutNotFoundException() {
        super("Workout not found.");
    }
}