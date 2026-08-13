import type { Workout } from "../types/Workout";

type WorkoutCardProps = {
    workout: Workout;
    onClick: () => void;
};

const DAYS = [
    { value: "MONDAY", label: "M" },
    { value: "TUESDAY", label: "T" },
    { value: "WEDNESDAY", label: "W" },
    { value: "THURSDAY", label: "T" },
    { value: "FRIDAY", label: "F" },
    { value: "SATURDAY", label: "S" },
    { value: "SUNDAY", label: "S" },
];

export default function WorkoutCard({workout, onClick}: WorkoutCardProps) {

    const activeDays = DAYS.filter((day) =>
        workout.days.some((workoutDay) => workoutDay.day === day.value)
    );

    const totalExercises = workout.days.reduce(
        (total, day) => total + day.exercises.length,
        0
    );

    return (
        <div
            onClick={onClick}
            className="
                bg-[#1c1f24]
                rounded-2xl
                p-4
                border
                border-[#2c2f36]
                cursor-pointer
                hover:border-[#c5f135]/40
                transition-all
                active:scale-[0.99]
            "
        >

            {/* Name + exercise count */}

            <div className="flex items-start justify-between mb-3">

                <h3
                    className="
                        text-xl
                        font-black
                        uppercase
                        tracking-wide
                        text-[#e8eaed]
                    "
                    style={{
                        fontFamily: "Barlow Condensed, sans-serif",
                    }}
                >
                    {workout.name}
                </h3>

                <div
                    className="
                        flex
                        items-center
                        gap-1
                        bg-[#c5f135]/10
                        rounded-lg
                        px-2
                        py-1
                    "
                >
                    <span
                        className="
                            text-[#c5f135]
                            text-sm
                            font-black
                        "
                        style={{
                            fontFamily: "JetBrains Mono, monospace",
                        }}
                    >
                        {totalExercises}
                    </span>

                    <span className="text-[#8b9199] text-xs">
                        ex
                    </span>
                </div>

            </div>


            {/* Days */}

            <div className="flex gap-1.5 mb-2">

                {DAYS.map((day) => {

                    const hasWorkoutDay = workout.days.some(
                        (workoutDay) => workoutDay.day === day.value
                    );

                    return (
                        <div
                            key={day.value}
                            className={`
                                flex-1
                                h-8
                                rounded-lg
                                flex
                                items-center
                                justify-center
                                text-[10px]
                                font-black
                                ${
                                    hasWorkoutDay
                                        ? "bg-[#c5f135] text-[#0b0c0e]"
                                        : "bg-[#141618] text-[#3a3d44]"
                                }
                            `}
                            style={{
                                fontFamily:
                                    "Barlow Condensed, sans-serif",
                            }}
                        >
                            {day.label}
                        </div>
                    );
                })}

            </div>


            {/* Training days */}

            {activeDays.length > 0 && (
                <p className="text-[#8b9199] text-xs">
                    {activeDays.length} training day
                    {activeDays.length !== 1 ? "s" : ""}
                </p>
            )}

        </div>
    );
}