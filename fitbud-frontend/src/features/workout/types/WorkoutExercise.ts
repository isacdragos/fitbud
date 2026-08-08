export interface WorkoutExercise {
    id: number;
    exerciseId: number | null;
    exerciseName: string | null;
    emoji: string | null;
    customName: string | null;
    sets: number;
    reps: number;
    weight: number;
    exerciseOrder: number;
}