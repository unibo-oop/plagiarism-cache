package casim.model.langtonsant;

/**
 * The state of a {@link casim.model.langtonsant.LangtonsAntCell}.
 */
public enum LangtonsAntCellState {
    /**
     * The activated state, an ant on this state
     * will turn left.
     */
    ON,
    /**
     * The deactivated state, an ant on this state
     * will turn right.
     */
    OFF;
}
