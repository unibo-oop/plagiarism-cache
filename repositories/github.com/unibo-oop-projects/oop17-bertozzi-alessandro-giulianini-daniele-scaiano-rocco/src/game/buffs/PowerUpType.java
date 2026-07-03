package game.buffs;

/**
 * Defines the type of the power up.
 */
public enum PowerUpType {
    /**
     * Regenerates some health to the player.
     */
    HEALTH_RECOVERY(false, 0),

    /**
     * Speeds ups the player.
     */
    SPEED_BOOST(true, (int) (PowerUpImpl.POWER_UP_LIFETIME * 1.5)),

    /**
     * Increase the fire rate of the player.
     */
    FIRE_RATE_BOOST(false, (int) (PowerUpImpl.POWER_UP_LIFETIME * 1.5)),

    /**
     * Protects the player from some damage.
     */
    SHIELD(true, PowerUpImpl.POWER_UP_LIFETIME),

    /**
     * I don't want to tell what this does. Get one and you'll see.
     * Caution. It's a nuclear weapon.
     */
    NUKE(false, 0),

    /**
     * I don't want to tell what this does. Get one and you'll see.
     * It's suddenly cold isn't it.
     */
    FREEZE(false, PowerUpImpl.POWER_UP_LIFETIME);

    private final boolean requiringUpdate;
    private final int lifetime;

    /**
     * @param requiringUpdate whether this buff needs to be updated at every cycle of the gameLoop
     */
    PowerUpType(final boolean requiringUpdate, final int lifetime) {
        this.requiringUpdate = requiringUpdate;
        this.lifetime = lifetime;
    }

    /**
     * @return whether this buff needs to be updated at every cycle of the gameLoop
     */
    public final boolean isRequiringUpdate() {
        return this.requiringUpdate;
    }

    /**
     * @return the lifetime of this entity
     */
    public final int getLifetime() {
        return this.lifetime;
    }
}
