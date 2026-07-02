package jwhale.model.engine.operations.object;

public enum Playback {
    /**
     * Start container operation.
     */
    START("/start"),
    /**
     * Stop container operation.
     */
    STOP("/stop"),
    /**
     * Restart container operation.
     */
    RESTART("/restart");

    private String operation;

    Playback(final String operation) {
        this.operation = operation;
    }
    /**
     * Get to string end point representation.
     * @return
     *          string end point representation
     */
    public String toString() {
        return this.operation;
    }
}
