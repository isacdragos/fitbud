package com.example.fitbudbackend.workout.exceptions;

public class WorkoutDayAlreadyExistsException extends RuntimeException {
    public WorkoutDayAlreadyExistsException() {
        super("Workout day already exists.");
    }
}