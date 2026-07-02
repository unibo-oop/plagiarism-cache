package clashclass.elements.buildings;

/**
 * Represents a defense building base stats.
 */
public enum DefenseBuildingBaseStats {
    CANNON(20, 1, 3),
    ARCHER_TOWER(15, 2, 6);

    private final int damage;
    private final int attackSpeed;
    private final int attackRange;

    /**
     * Constructs the defense building base stats.
     *
     * @param damage the damage
     * @param attackSpeed the attack speed
     * @param attackRange the attack range
     */
    DefenseBuildingBaseStats(
            final int damage,
            final int attackSpeed,
            final int attackRange) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.attackRange = attackRange;
    }

    /**
     * Gets the damage.
     *
     * @return the damage
     */
    public int getDamage() {
        return this.damage;
    }

    /**
     * Gets the attack speed.
     *
     * @return the attack speed
     */
    public int getAttackSpeed() {
        return this.attackSpeed;
    }

    /**
     * Gets the attack range.
     *
     * @return the attack range
     */
    public int getAttackRange() {
        return this.attackRange;
    }
}
