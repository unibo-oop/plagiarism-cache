package gymman.fitness;

/**
 *
 * All the goals that can be added in one
 * training program.
 *
 */
public enum Goal {

    GAIN_STRENGTH("gain_strength"),
    INCREASE_MUSCLE_SIZE("increase_muscle_size"),
    DEFENITION("defenition"),
    SLIMMING("slimming"),
    BUILD_MUSCLE("build_muscle");

    /** goal denomination. */
    private final String goal;

    Goal(final String goal) {
        this.goal = goal;
    }

    @Override
    public String toString() {
        return this.goal;
    }

}
