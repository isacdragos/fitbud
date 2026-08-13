import api from "../../../shared/services/api";

import type { Workout } from "../types/Workout";

export async function getWorkouts(): Promise<Workout[]> {
    const response = await api.get<Workout[]>("/workouts");
    return response.data;
}

export async function getWorkout(workoutId: number): Promise<Workout> {
    const response = await api.get<Workout>(`/workouts/${workoutId}`);
    return response.data;
}

export async function createWorkout(name: string): Promise<Workout> {
    const response = await api.post<Workout>("/workouts", {
        name,
    });

    return response.data;
}

export async function deleteWorkout(workoutId: number): Promise<void> {
    await api.delete(`/workouts/${workoutId}`);
}

export async function addDay(
    workoutId: number,
    day: string
): Promise<Workout> {
    const response = await api.post<Workout>(
        `/workouts/${workoutId}/days`,
        {
            day,
        }
    );

    return response.data;
}

export async function deleteDay(workoutDayId: number): Promise<void> {
    await api.delete(`/workout-days/${workoutDayId}`);
}

export async function addExercise(
    workoutDayId: number,
    request: {
        exerciseId?: number;
        customName?: string;
        sets: number;
        reps: number;
        weight: number;
    }
): Promise<Workout> {
    const response = await api.post<Workout>(
        `/workout-days/${workoutDayId}/exercises`,
        request
    );

    return response.data;
}

export async function removeExercise(
    workoutExerciseId: number
): Promise<void> {
    await api.delete(`/workout-exercises/${workoutExerciseId}`);
}

export async function reorderExercises(
    workoutDayId: number,
    request: {
        workoutExerciseId: number;
        position: number;
    }[]
): Promise<void> {
    await api.put(
        `/workout-days/${workoutDayId}/reorder`,
        request
    );
}