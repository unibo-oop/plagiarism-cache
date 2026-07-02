package model.entities.survivor;

/**
 * Enum representing the possible states of a survivor in the domain model.
 * Each state corresponds to an action or condition of the survivor,
 * such as movement, shooting in a direction, suffering damage, or death.
 */
public enum SurvivorState {

    /** Survivor is idle (not moving). */
    SURVIVOR_IDLE(0),

    /** Survivor is moving left. */
    SURVIVOR_MOVE_LEFT(1),

    /** Survivor is moving right. */
    SURVIVOR_MOVE_RIGHT(2),

    /** Survivor is moving up. */
    SURVIVOR_MOVE_UP(3),

    /** Survivor is moving down. */
    SURVIVOR_MOVE_DOWN(4),

    /** Survivor is shooting to the left. */
    SURVIOR_SHOOT_LEFT(5),

    /** Survivor is shooting to the right. */
    SURVIOR_SHOOT_RIGHT(6),

    /** Survivor is shooting upwards. */
    SURVIOR_SHOOT_UP(7),

    /** Survivor is shooting downwards. */
    SURVIOR_SHOOT_DOWN(8),

    /** Survivor is suffering damage. */
    SURVIVOR_SUFFER_DAMAGE(9),

    /** Survivor is dead. */
    SURVIVOR_DEAD(10);

    private final int index;

    /**
     * Constructs a SurvivorState with an associated index.
     *
     * @param index the integer index of the state
     */
    SurvivorState(final int index) {
        this.index = index;
    }

    /**
     * Returns the integer index associated with the survivor state.
     *
     * @return the index of the state
     */
    public int getIndex() {
        return index;
    }
}
