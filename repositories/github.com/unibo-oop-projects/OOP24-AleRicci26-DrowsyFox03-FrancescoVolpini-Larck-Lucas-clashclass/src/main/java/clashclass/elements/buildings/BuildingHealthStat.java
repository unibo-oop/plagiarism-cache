package clashclass.elements.buildings;

/**
 * Represents a building health stat.
 */
public enum BuildingHealthStat {
    TOWN_HALL(1000),
    WALL(500),
    CANNON(100),
    ARCHER_TOWER(80),
    GOLD_STORAGE(100),
    ELIXIR_STORAGE(100),
    GOLD_EXTRACTOR(100),
    ELIXIR_EXTRACTOR(100),
    ARMY_CAMP(100),
    BARRACKS(100);

    private final int health;

    /**
     * Constructs the building health stat.
     *
     * @param health the health of the building
     */
    BuildingHealthStat(final int health) {
        this.health = health;
    }

    /**
     * Gets the health of the building.
     *
     * @return the health of the building
     */
    public int getHealth() {
        return this.health;
    }
}
