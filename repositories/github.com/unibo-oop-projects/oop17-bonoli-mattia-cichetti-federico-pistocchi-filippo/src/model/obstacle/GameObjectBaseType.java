package model.obstacle;

/**
 * This enumeration represents the base type of an obstacle.
 * Now is easier distinguish obstacles.
 *
 */
public enum GameObjectBaseType {

    /**
     * Logs moves in the river.
     */
    LOG,

    /**
     * Vehicles moves in the street.
     */
    VEHICLE,

    /**
     * Turtles moves in the river.
     */
    TURTLE,

    /**
     * Mod can be in each lane.
     */
    MOD,

    /**
     * The frog.
     */
    FROG;

}
