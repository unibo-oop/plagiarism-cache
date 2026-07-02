package clashclass.elements.troops;

/**
 * Represents a troop health stat.
 */
public enum TroopHealthStat {
    BARBARIAN(100),
    ARCHER(70);

    private final int health;

    /**
     * Constructs the troop health stat.
     *
     * @param health the health of the troop
     */
    TroopHealthStat(final int health) {
        this.health = health;
    }

    /**
     * Gets the health of the troop.
     *
     * @return the health of the troop
     */
    public int getHealth() {
        return this.health;
    }
}
