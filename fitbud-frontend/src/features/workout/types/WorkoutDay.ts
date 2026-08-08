import type { WorkoutExercise } from "./WorkoutExercise";

export interface WorkoutDay {
    id: number;
    day: string;
    exercises: WorkoutExercise[];
}