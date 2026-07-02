package model.entity;


/**
 * 
 * Enumeration identifying the level on which entities could spawn.
 *
 */
public enum SpawnLevel {

    /**
     * Land level.
     */
    ZERO(1.0), 

    /**
     * First level.
     */
    ONE(0.75),

    /**
     * Second level.
     */
    TWO(0.5);

    private final double spawnY;

    /**
     * 
     * @param spawnY the Y coordinate of the level.
     */
    SpawnLevel(final double spawnY) {
        this.spawnY = spawnY;
    }

    /**
     * 
     * @return the Y coordinate of the level.
     */
    public double getSpawnY() {
        return spawnY;
    }

}
