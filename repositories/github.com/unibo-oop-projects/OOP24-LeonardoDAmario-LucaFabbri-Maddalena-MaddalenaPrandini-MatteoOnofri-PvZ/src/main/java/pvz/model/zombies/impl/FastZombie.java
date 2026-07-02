package pvz.model.zombies.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.Position;

/**
 * Represents a fast zombie with lower health but higher speed.
 * Extends {@link AbstractZombie}.
 */
public class FastZombie extends AbstractZombie {

    /** The health points of a fast zombie. */
    private static final int FAST_ZOMBIE_HEALTH = 80;
    /** The movement speed of a fast zombie. */
    private static final int FAST_ZOMBIE_SPEED = 2;
    /** The damage dealt by a fast zombie. */
    private static final int FAST_ZOMBIE_DAMAGE = 35;

    /**
     * Constructs a new FastZombie at the specified position.
     *
     * @param position the initial position of the zombie.
     */
    public FastZombie(final Position position) {
        super(position, FAST_ZOMBIE_HEALTH, FAST_ZOMBIE_SPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return FAST_ZOMBIE_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZombieType getType() {
        return ZombieType.FASTZOMBIE;
    }
}
