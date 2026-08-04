package com.example.fitbudbackend.workout.exceptions;

public class WorkoutDayNotFoundException extends RuntimeException {
    public WorkoutDayNotFoundException() {
        super("Workout day not found.");
    }
}
