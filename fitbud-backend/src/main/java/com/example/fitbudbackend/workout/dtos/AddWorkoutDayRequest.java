package com.example.fitbudbackend.workout.dtos;

import com.example.fitbudbackend.workout.entities.DayOfWeek;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddWorkoutDayRequest {
    @NotNull
    private DayOfWeek day;
}
