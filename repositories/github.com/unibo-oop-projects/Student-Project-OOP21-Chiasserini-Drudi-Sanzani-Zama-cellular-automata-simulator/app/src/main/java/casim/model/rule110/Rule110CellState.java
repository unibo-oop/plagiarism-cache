package casim.model.rule110;

/**
 * The state of Rule110Cell.
 */
public enum Rule110CellState {
    /**
     * The active state of the cell.
     */
    ALIVE(1),
    /**
     * The deactivated state of the cell.
     */
    DEAD(0);

    private final int value;

    /**
     * Method associating an int to its state.
     * 
     * @param value int to associate.
     */
    Rule110CellState(final int value) {
        this.value = value;
    }

    /**
     * Get the associated int.
     * 
     * @return the associated value 
     */
    public int getValue() {
        return this.value;
    }
}
