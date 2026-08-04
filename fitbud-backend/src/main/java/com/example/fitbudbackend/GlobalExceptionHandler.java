package com.example.fitbudbackend;

import com.example.fitbudbackend.auth.exceptions.EmailAlreadyExistsException;
import com.example.fitbudbackend.auth.exceptions.InvalidCredentialsException;
import com.example.fitbudbackend.workout.exceptions.ExerciseNotFoundException;
import com.example.fitbudbackend.workout.exceptions.WorkoutDayAlreadyExistsException;
import com.example.fitbudbackend.workout.exceptions.WorkoutDayNotFoundException;
import com.example.fitbudbackend.workout.exceptions.WorkoutNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<String> handleEmailAlreadyExists(EmailAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String> handleInvalidCredentials(InvalidCredentialsException ex) {

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(ex.getMessage());
    }

    @ExceptionHandler(ExerciseNotFoundException.class)
    public ResponseEntity<String> handleExerciseNotFound(ExerciseNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(WorkoutNotFoundException.class)
    public ResponseEntity<String> handleWorkoutNotFound(WorkoutNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(WorkoutDayNotFoundException.class)
    public ResponseEntity<String> handleWorkoutDayNotFound(WorkoutDayNotFoundException ex) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ex.getMessage());
    }

    @ExceptionHandler(WorkoutDayAlreadyExistsException.class)
    public ResponseEntity<String> handleWorkoutDayAlreadyExists(WorkoutDayAlreadyExistsException ex) {

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ex.getMessage());
    }
}
