package constraints;

public final class TrafficLightConstraints {

    /**
     * The default duration of the yellow phase.
     * The duration of this phase cannot be set by the user.
     */
    public static final int YELLOW_DEFAULT_DURATION = 1;

    /**
     * The default duration of the red and green phases.
     * The duration of these phases is used if the configuration
     * fails.
     */
    public static final int DEFAULT_PHASE_DURATION = 3;

    private TrafficLightConstraints() {
    }
}
