package model.utility;

/**
 * Spawn Enumeration.
 */
public enum Spawn {

    /**
     * A spawn point.
     */
    A(SpawnUtility.getSpawnAX(), SpawnUtility.getSpawnAY()),

    /**
     * B spawn point.
     */
    B(SpawnUtility.getSpawnBX(), SpawnUtility.getSpawnBY()),

    /**
     * C spawn point.
     */
    C(SpawnUtility.getSpawnCX(), SpawnUtility.getSpawnCY()),

    /**
     * D spawn point.
     */
    D(SpawnUtility.getSpawnDX(), SpawnUtility.getSpawnDY()),

    /**
     * E spawn point.
     */
    E(SpawnUtility.getSpawnEX(), SpawnUtility.getSpawnEY()),

    /**
     * F spawn point.
     */
    F(SpawnUtility.getSpawnFX(), SpawnUtility.getSpawnFY()),

    /**
     * G spawn point.
     */
    G(SpawnUtility.getSpawnGX(), SpawnUtility.getSpawnGY()),

    /**
     * H spawn point.
     */
    H(SpawnUtility.getSpawnHX(), SpawnUtility.getSpawnHY());

    private double x;
    private double y;

    Spawn(final double x, final double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * @return the x of the point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * @return the y of the point.
     */
    public double getY() {
        return this.y;
    }
}
