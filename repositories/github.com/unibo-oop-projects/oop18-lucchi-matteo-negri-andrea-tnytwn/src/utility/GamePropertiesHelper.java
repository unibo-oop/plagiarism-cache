package utility;

import model.construction.ConstructionType;

/**
 * Class used to get some information about the game system.
 */
public final class GamePropertiesHelper {

    /**
     * Number of row present in Game Map.
     */
    public static final int ROW_NUMBER = 15;

    /**
     * Number of column present in Game Map.
     */
    public static final int COLUMN_NUMBER = 20;

    /**
     * Number of building types available.
     */
    public static final int NUMBER_OF_BUILDINGS = ConstructionType.values().length;

    private GamePropertiesHelper() {
    }
}
