import type { WorkoutDay } from './WorkoutDay';

export interface Workout {
    id: number;
    name: string;
    days: WorkoutDay[];
}