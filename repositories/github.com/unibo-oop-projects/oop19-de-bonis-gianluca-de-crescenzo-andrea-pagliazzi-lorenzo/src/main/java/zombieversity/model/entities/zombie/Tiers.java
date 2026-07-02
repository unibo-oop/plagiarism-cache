package zombieversity.model.entities.zombie;

/**
 * Zombies tiers.
 */
public enum Tiers {

    /**
     * Weakest tier: slow, low health points, low damage.
     */
    WEAK(0.5, 100, 1),
    /**
     * Low tier: a bit faster, more health points, a bit more damage.
     */
    LOW(0.6, 120, 2),
    /**
     * Average tier: medium speed, great quantity of health point, great damage.
     */
    AVERAGE(0.7, 150, 4),
    /**
     * Strong tier: fast, lot of health points, high damage.
     */
    STRONG(0.8, 180, 6),
    /**
     * Brutal: fast and strong.
     */
    BRUTAL(1.0, 220, 8);

    private final double velocity;
    private final int maxHp;
    private final int damageDealt;

    Tiers(final double velocity, final int maxHp, final int damageDealt) {
        this.velocity = velocity;
        this.maxHp = maxHp;
        this.damageDealt = damageDealt;
    }

    /**
     * 
     * @return zombie's velocity.
     */
    public double getVelocity() {
        return this.velocity;
    }

    /**
     * 
     * @return zombie's health points at spawn.
     */
    public int getMxHp() {
        return this.maxHp;
    }

    /**
     * 
     * @return damage dealt to player when hit.
     */
    public int getDamageDealt() {
        return this.damageDealt;
    }

}
