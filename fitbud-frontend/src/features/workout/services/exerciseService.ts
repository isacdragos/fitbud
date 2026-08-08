import api from "../../../shared/services/api";

import type { Exercise } from "../types/Exercise";

export async function getExercises(
    search?: string,
    muscle?: string
): Promise<Exercise[]> {

    const response = await api.get<Exercise[]>("/exercises", {
        params: {
            search,
            muscle,
        },
    });

    return response.data;
}