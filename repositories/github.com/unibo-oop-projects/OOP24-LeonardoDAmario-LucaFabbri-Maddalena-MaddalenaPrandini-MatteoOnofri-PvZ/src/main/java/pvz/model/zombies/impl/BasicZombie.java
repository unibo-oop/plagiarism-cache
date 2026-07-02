package pvz.model.zombies.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.Position;

/**
 * Represents a basic zombie with standard health, speed, and damage.
 * Extends {@link AbstractZombie}.
 */
public class BasicZombie extends AbstractZombie {

    /** The health points of a basic zombie. */
    private static final int BASIC_ZOMBIE_HEALTH = 100;
    /** The movement speed of a basic zombie. */
    private static final int BASIC_ZOMBIE_SPEED = 1;
    /** The damage dealt by a basic zombie. */
    private static final int BASIC_ZOMBIE_DAMAGE = 35;

    /**
     * Constructs a new BasicZombie at the specified position.
     *
     * @param position the initial position of the zombie.
     */
    public BasicZombie(final Position position) {
        super(position, BASIC_ZOMBIE_HEALTH, BASIC_ZOMBIE_SPEED);
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return BASIC_ZOMBIE_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZombieType getType() {
        return ZombieType.BASICZOMBIE;
    }
}
