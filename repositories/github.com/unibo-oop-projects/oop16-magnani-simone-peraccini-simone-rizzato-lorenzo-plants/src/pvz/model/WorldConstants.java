package pvz.model;

/**
 * This class contains a series of values describing the metrics of the logical
 * world where the gameplay takes place.<br>
 * It cannot be instantiated.
 */
public final class WorldConstants {

    public static final double CELL_WIDTH = 3d;
    public static final double BACKYARD_WIDTH = CELL_WIDTH * 12;
    public static final double BACKYARD_HEIGHT = CELL_WIDTH * 5;
    public static final double SUN_RADIUS = .75d;

    private WorldConstants() {
    }

}
