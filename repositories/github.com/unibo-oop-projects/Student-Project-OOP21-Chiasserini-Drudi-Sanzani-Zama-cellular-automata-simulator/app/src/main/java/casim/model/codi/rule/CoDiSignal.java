package casim.model.codi.rule;

/**
 * An enum for the growth signals for the CoDi growth phase (see in GrowthUpdateRule).
 */
//Package-private
enum CoDiSignal {

    /**
     * An axon signal.
     */
    AXON_SIGNAL(1),
    /**
     * A dendrite signal.
     */
    DENDRITE_SIGNAL(4);

    private final int value;

    CoDiSignal(final int value) {
        this.value = value;
    }

    /**
     * Get the value linked to the signal.
     * 
     * @return the int value linked to the current signal.
     */
    public int getValue() {
        return this.value;
    }
}
