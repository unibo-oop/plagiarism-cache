package model.entities.zombie;

/**
 * Enumeration representing the various states a zombie can be in within the
 * domain model.
 * <p>
 * These states are used to define the zombie's behavior or condition, such as
 * moving, attacking,
 * receiving damage, or being idle or dead.
 */
public enum ZombieState {

    /** Zombie is idle (not performing any action). */
    ZOMBIE_IDLE(0),

    /** Zombie is moving to the left. */
    ZOMBIE_MOVE_LEFT(1),

    /** Zombie is moving to the right. */
    ZOMBIE_MOVE_RIGHT(2),

    /** Zombie is moving upwards. */
    ZOMBIE_MOVE_UP(3),

    /** Zombie is moving downwards. */
    ZOMBIE_MOVE_DOWN(4),

    /** Zombie is performing an attack. */
    ZOMBIE_ATTACK(5),

    /** Zombie is taking damage. */
    ZOMBIE_SUFFER_DAMAGE(6),

    /** Zombie is dead. */
    ZOMBIE_DEAD(7);

    /** Integer index associated with the zombie state. */
    private final int index;

    /**
     * Constructs a {@code ZombieState} with the specified index.
     *
     * @param index the numerical identifier for the state
     */
    ZombieState(final int index) {
        this.index = index;
    }

    /**
     * Returns the index associated with this zombie state.
     *
     * @return the state's numerical index
     */
    public int getIndex() {
        return index;
    }

}
