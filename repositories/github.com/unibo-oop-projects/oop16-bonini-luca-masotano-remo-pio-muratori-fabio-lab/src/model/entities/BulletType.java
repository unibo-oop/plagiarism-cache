package model.entities;

/**
 * 
 * Defines all the types of bullets in the game.
 *
 */
public enum BulletType {

    /**
     * The bullets shot by the player.
     */
    BULLET_PLAYER(1, 800, 11),

    /**
     * The bullets shot by the turret.
     */
    BULLET_TURRET(1, 800, 8),

    /**
     * The bullets shot by the tank.
     */
    BULLET_TANK(1, 800, 6),

    /**
     * The bullets shot by the boss.
     */
    BULLET_BOSS(2, 1000, 10);

    private final double damage;
    private final double range;
    private final double steps;

    BulletType(final double damage, final double range, final double steps) {
        this.damage = damage;
        this.range = range;
        this.steps = steps;
    }

    /**
     * Get the value of the damage.
     * 
     * @return The damage made by this bullet.
     */
    public double getDamage() {
        return damage;
    }

    /**
     * Get the value of the range.
     * 
     * @return The range of this bullet
     */
    public double getRange() {
        return range;
    }

    /**
     * Get the value of the steps, the speed of this bullet.
     * 
     * @return The steps of this bullet.
     */
    public double getSteps() {
        return steps;
    }
}
