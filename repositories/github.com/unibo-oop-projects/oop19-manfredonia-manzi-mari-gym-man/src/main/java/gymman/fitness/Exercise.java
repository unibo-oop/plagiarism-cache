package gymman.fitness;

import lombok.Getter;
import lombok.Setter;

/**
 * Class to create a new Exercise with all necessary informations.
 * The {@link Exercise} object will be created by {@link ExerciseBuilder}.
 *
 */
public final class Exercise {
    /** Exercise name. **/
    @Getter @Setter private String name;
    /** Exercise category. **/
    @Getter @Setter private Category category;
    /** Exercise execution metric. It can be with timer or with repetitions **/
    @Getter @Setter private AbstractMetric executionMetric;

    /**
     * Class to implement an exercise builder. Controlling if all the informations
     * are correct, before building a new exercise.
     *
     */
    public static final class ExerciseBuilder implements WorkoutBuilder {
        /** Exercise name. **/
        private String name;
        /** Exercise category. **/
        private Category category;
        /** Exercise execution metric. It can be with timer or with repetitions **/
        private AbstractMetric executionMode;

        @Override
        public ExerciseBuilder name(final String name) {
            this.name = name;
            return this;
        }

        @Override
        public ExerciseBuilder category(final Category category) {
            this.category = category;
            return this;
        }

        @Override
        public ExerciseBuilder executionMetric(final AbstractMetric executionMetric) {
            this.executionMode = executionMetric;
            return this;
        }

        @Override
        public Exercise buildExercise() {
            final Exercise exercise = new Exercise();

            exercise.name = this.name;
            exercise.category = this.category;
            exercise.executionMetric = this.executionMode;

            if (exercise.getName() == null) {
                throw new IllegalStateException("Missing exercise denomination");
            } else {
                if (exercise.getCategory() == null) {
                    throw new IllegalStateException("Missing exercise category");
                } else {
                    if (exercise.getExecutionMetric() == null) {
                        throw new IllegalStateException("Missing exercise execution metric");
                    } else {
                        return exercise;
                    }
                }
            }
        }
    }

    private Exercise() {
        /* Constructor is now private. */
    }
}
