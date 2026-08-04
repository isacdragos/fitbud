package com.example.fitbudbackend.workout.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class WorkoutResponse {
    private Long id;
    private String name;
    private List<WorkoutDayResponse> days;
}
