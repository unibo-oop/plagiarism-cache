package utilities;

import model.mod.ModType;

/**
 * This is an utility class containing public constants.
 */
public final class Constants {

    /**
     * The left border of the field.
     */
    public static final double WORLD_LEFT_LIMIT = 0;

    /**
     * The right limit of the field.
     */
    public static final double WORLD_RIGHT_LIMIT = 100;

    /**
     * The number of lanes in the world.
     */
    public static final int WORLD_NUMBER_OF_LANE = 12;

    /**
     * This is the with of a den.
     */
    public static final double DEN_WIDTH = 8.0;

    /**
     * This is an array containing all the available ModTypes.
     */
    public static final ModType[] MODARRAY = ModType.values();

    private Constants() { 

    }

}
