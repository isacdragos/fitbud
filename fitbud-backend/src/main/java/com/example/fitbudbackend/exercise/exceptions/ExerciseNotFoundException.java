package com.example.fitbudbackend.exercise.exceptions;

public class ExerciseNotFoundException extends RuntimeException {
    public ExerciseNotFoundException(Long id) {
        super("Exercise with id " + id + " not found.");
    }
}