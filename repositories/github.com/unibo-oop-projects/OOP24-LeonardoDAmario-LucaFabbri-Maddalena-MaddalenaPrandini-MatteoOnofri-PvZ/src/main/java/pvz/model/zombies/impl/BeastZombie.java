package pvz.model.zombies.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.Position;

/**
 * Represents a beast zombie with very high health and damage.
 * Extends {@link AbstractZombie}.
 */
public class BeastZombie extends AbstractZombie {

    /** The health points of a beast zombie. */
    private static final int BEAST_ZOMBIE_HEALTH = 250;
    /** The movement speed of a beast zombie. */
    private static final int BEAST_ZOMBIE_SPEED = 1;
    /** The damage dealt by a beast zombie. */
    private static final int BEAST_ZOMBIE_DAMAGE = 150;

    /**
     * Constructs a new BeastZombie at the specified position.
     *
     * @param position the initial position of the zombie.
     */
    public BeastZombie(final Position position) {
        super(position, BEAST_ZOMBIE_HEALTH, BEAST_ZOMBIE_SPEED);
    }
 
    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return BEAST_ZOMBIE_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZombieType getType() {
        return ZombieType.BEASTZOMBIE;
    }
}
