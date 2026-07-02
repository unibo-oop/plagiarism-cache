package gymman.fitness;

/**
 *
 * Enumeration of all Metric Types.
 *
 */
public enum MetricType {
    METRIC_WITH_TIMER("with timer"),
    METRIC_WITH_REPETITIONS("with repetitions");

	/** Type denomination of metric. */
    private String type;

    MetricType(final String type) {
        this.type = type;
    }

    @Override
    public final String toString() {
        return this.type;
    }
}