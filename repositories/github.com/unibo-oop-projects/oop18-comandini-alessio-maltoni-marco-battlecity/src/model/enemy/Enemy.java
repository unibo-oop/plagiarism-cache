package model.enemy;

/**
 * An enumeration that represent all the enemy type in the game.
 *
 */
public enum Enemy {
    /**
     * Normal tank, the basic tank that move slow and fire slow.
     */
    NORMAL(100),
    /**
     * Fast tank, tank that move and fire fast.
     */
    FAST(200),
    /**
     * Power tank, tank that move slow but fire fast.
     */

    POWER(300),
    /**
     * Armored tank, tank that move slow fire fast and need 3 shoot to kill him.
     */
    ARMORED(400);

    private final int points;

    Enemy(final int points) {
        this.points = points;
    }
/**
 * 
 * @return relative kill points for every enemy
 */
    public int getPoints() {
        return this.points;
    }

}
