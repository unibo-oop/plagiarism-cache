package clashclass.elements.troops;

/**
 * Represents a troop base stats.
 */
public enum TroopBaseStats {
    BARBARIAN(30, 1, 1),
    ARCHER(25, 2, 2);

    private final int damage;
    private final int attackSpeed;
    private final int movementSpeed;

    /**
     * Constructs the troop base stats.
     *
     * @param damage the damage
     * @param attackSpeed the attack speed
     * @param movementSpeed the movement speed
     */
    TroopBaseStats(
            final int damage,
            final int attackSpeed,
            final int movementSpeed) {
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.movementSpeed = movementSpeed;
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
     * Gets the movement speed.
     *
     * @return the movement speed
     */
    public int getMovementSpeed() {
        return this.movementSpeed;
    }
}
