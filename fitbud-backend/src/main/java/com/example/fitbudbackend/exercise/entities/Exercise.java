package com.example.fitbudbackend.exercise.entities;

import com.example.fitbudbackend.exercise.enums.MuscleGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "exercises")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String emoji;

    @ElementCollection(targetClass = MuscleGroup.class)
    @CollectionTable(name = "exercise_primary_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle")
    private List<MuscleGroup> primaryMuscles = new ArrayList<>();

    @ElementCollection(targetClass = MuscleGroup.class)
    @CollectionTable(name = "exercise_secondary_muscles", joinColumns = @JoinColumn(name = "exercise_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "muscle")
    private List<MuscleGroup> secondaryMuscles = new ArrayList<>();

}
