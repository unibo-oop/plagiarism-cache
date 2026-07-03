package game.buffs;

/**
 * The interface for powerUps.
 */
public interface PowerUp {
    /**
     * Applies the buff.
     */
    void applyBuff();

    /**
     * Resets the buff applied by this powerUp.
     */
    void reset();

    /**
     * @return whether this powerUp is activated or not
     */
    boolean isActivated();
}
