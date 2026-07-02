package it.unibo.briscoola.model.api.attributes;

/**
 * The possible difficulties that the CPU can accept.
 */
public enum Difficulty {
    EASY(1),
    MEDIUM(1.5),
    HARD(2);

    private final double value;

    Difficulty(final double value) {
        this.value = value;
    }

    /**
     * Method to return the value associated to the Difficulty.
     *
     * @return the value associated to the difficulty
     */
    public double getValue() {
        return this.value;
    }
}
