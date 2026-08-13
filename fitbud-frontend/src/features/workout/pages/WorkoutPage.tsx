import { useEffect, useState } from "react";

import { getWorkouts } from "../services/workoutService";
import { createWorkout } from "../services/workoutService";
import  WorkoutCard from "../components/WorkoutCard";
import type { Workout } from "../types/Workout";

export default function WorkoutsPage() {

    const [workouts, setWorkouts] = useState<Workout[]>([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState("");
    const [isCreating, setIsCreating] = useState(false);
    const [workoutName, setWorkoutName] = useState("");
    const [creating, setCreating] = useState(false);

    useEffect(() => {
        async function loadWorkouts() {
            try {
                const data = await getWorkouts();
                setWorkouts(data);
                setError("");
            } catch (error) {
                console.error("Failed to load workouts:", error);
                setError("Failed to load your workouts. Please try again.");
            } finally {
                setLoading(false);
            }
        }

        loadWorkouts();
    }, []);


    return (
        <div className="min-h-screen bg-[#0d0f12] text-white">

            <div className="mx-auto w-full max-w-2xl px-4 py-10">

                {/* Header */}

                <div className="mb-10">

                    <p className="mb-2 text-sm font-semibold tracking-[3px] text-lime-400">
                        YOUR PROGRAMS
                    </p>

                    <h1 className="logo-font text-5xl font-black tracking-tight">
                        WORKOUTS
                    </h1>

                </div>





                {/* New workout */}

                {!isCreating && (
                <button
                    onClick={() => setIsCreating(true)}
                    className="
                        mb-10
                        flex
                        w-full
                        items-center
                        justify-center
                        rounded-2xl
                        border
                        border-dashed
                        border-[#3a4048]
                        bg-[#15181d]
                        py-8
                        text-gray-400
                        transition-all
                        duration-300
                        hover:border-lime-400
                        hover:text-lime-400
                    "
                >
                    <span className="mr-3 text-3xl">
                        +
                    </span>

                    <span className="font-semibold">
                        NEW PROGRAM
                    </span>
                </button>
            )}
            {isCreating && (
            <div
                className="
                    mb-10
                    rounded-2xl
                    border
                    border-[#2b3037]
                    bg-[#15181d]
                    p-6
                "
            >
                <p className="mb-2 text-xs font-semibold tracking-[2px] text-gray-400">
                    PROGRAM NAME
                </p>

                <input
                    type="text"
                    placeholder="Push Pull Legs"
                    value={workoutName}
                    onChange={(e) => setWorkoutName(e.target.value)}
                    autoFocus
                    className="
                        mb-4
                        w-full
                        rounded-xl
                        border
                        border-[#2b3037]
                        bg-[#1b1f25]
                        px-4
                        py-3
                        text-white
                        outline-none
                        placeholder:text-gray-600
                        focus:border-lime-400
                    "
                />

                <div className="flex justify-end gap-3">

                    <button
                        onClick={() => {
                            setIsCreating(false);
                            setWorkoutName("");
                        }}
                        className="
                            rounded-xl
                            px-5
                            py-2
                            text-sm
                            font-semibold
                            text-gray-400
                            hover:text-white
                        "
                    >
                        CANCEL
                    </button>

                    <button
                        disabled={!workoutName.trim() || creating}
                        onClick={async () => {
                            try {
                                setCreating(true);

                                const workout = await createWorkout(
                                    workoutName.trim()
                                );

                                setWorkouts((previous) => [
                                    ...previous,
                                    workout
                                ]);

                                setWorkoutName("");
                                setIsCreating(false);

                            } catch (error) {
                                console.error(
                                    "Failed to create workout:",
                                    error
                                );

                                setError(
                                    "Failed to create workout. Please try again."
                                );

                            } finally {
                                setCreating(false);
                            }
                        }}
                        className="
                            rounded-xl
                            bg-lime-400
                            px-5
                            py-2
                            text-sm
                            font-bold
                            text-black
                            transition-all
                            hover:brightness-110
                            disabled:cursor-not-allowed
                            disabled:opacity-40
                        "
                    >
                        {creating ? "CREATING..." : "CREATE"}
                    </button>

                </div>
            </div>
        )}


                {/* Loading */}

                {loading && (
                    <div className="py-20 text-center text-gray-500">
                        Loading workouts...
                    </div>
                )}


                {/* Empty state */}

                {!loading && !error && workouts.length === 0 && (
                    <div className="flex flex-col items-center py-20 text-center">

                        <div className="mb-5 text-6xl">
                            🏋️
                        </div>

                        <h2 className="mb-2 text-2xl font-bold text-white">
                            No programs yet
                        </h2>

                        <p className="text-gray-500">
                            Create your first workout program above
                        </p>

                    </div>
                )}

                {error && (
                <div
                    className="
                        mb-6
                        rounded-xl
                        border
                        border-red-500/40
                        bg-red-500/10
                        px-4
                        py-3
                        text-sm
                        text-red-400
                    "
                >
                    {error}
                </div>
            )}


                {/* Workouts */}

                {!loading && !error && workouts.length > 0 && (
                    <div className="space-y-3">

                        {workouts.map((workout) => (

                            <WorkoutCard workout={workout} onClick={() => {}} />

                        ))}

                    </div>
                )}

            </div>

        </div>
    );
}