package model.powerup;

/**
 * The Enum PowerUpT.
 */
public enum PowerUpT {

    /** The health. */
    HEALTH(false,0),

    /** The fire boost. */
    FIRE_BOOST(false,(int) (PowerUpImpl.LIFETIME_P * 1.5)),
    
    /** The shield. */
    SHIELD(true, PowerUpImpl.LIFETIME_P),

    /** The freeze. */
    FREEZE(false,PowerUpImpl.LIFETIME_P);

    /** The requiring update. */
    private final boolean requiringUpdate;
    
    /** The lifetime. */
    private final int lifetime;

    /**
     * Instantiates a new power up T.
     *
     * @param requiringUpdate the requiring update
     * @param lifetime the lifetime
     */
    PowerUpT(final boolean requiringUpdate, final int lifetime){
        this.requiringUpdate = requiringUpdate;
        this.lifetime = lifetime;
    }

    /**
     * Checks if is requiring update.
     *
     * @return true, if is requiring update
     */
    public final boolean isRequiringUpdate() {
        return this.requiringUpdate;
    }
    
    /**
     * Gets the lifetime.
     *
     * @return the lifetime
     */
    public final int getLifetime() {
        return this.lifetime;
    }

}
