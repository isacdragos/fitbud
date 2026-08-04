package com.example.fitbudbackend.workout.dtos;

import com.example.fitbudbackend.workout.entities.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutDayResponse {
    private Long id;
    private DayOfWeek day;
    private List<WorkoutExerciseResponse> exercises;
}
