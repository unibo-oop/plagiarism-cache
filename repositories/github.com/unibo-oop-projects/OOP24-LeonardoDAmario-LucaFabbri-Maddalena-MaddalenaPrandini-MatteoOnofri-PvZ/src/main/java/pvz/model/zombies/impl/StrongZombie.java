package pvz.model.zombies.impl;

import pvz.model.zombies.api.ZombieType;
import pvz.utilities.Position;

/**
 * Represents a strong zombie with higher health and moderate speed.
 * Extends {@link AbstractZombie}.
 */
public class StrongZombie extends AbstractZombie {

    /** The health points of a strong zombie. */
    private static final int STRONG_ZOMBIE_HEALTH = 150;
    /** The movement speed of a strong zombie. */
    private static final int STRONG_ZOMBIE_SPEED = 1;
    /** The damage dealt by a strong zombie. */
    private static final int STRONG_ZOMBIE_DAMAGE = 45;

    /**
     * Constructs a new StrongZombie at the specified position.
     *
     * @param position the initial position of the zombie.
     */
    public StrongZombie(final Position position) {
        super(position, STRONG_ZOMBIE_HEALTH, STRONG_ZOMBIE_SPEED);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getDamage() {
        return STRONG_ZOMBIE_DAMAGE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ZombieType getType() {
        return ZombieType.STRONGZOMBIE;
    }
}
