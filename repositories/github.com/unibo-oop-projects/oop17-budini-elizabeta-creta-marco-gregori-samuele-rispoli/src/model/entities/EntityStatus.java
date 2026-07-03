package model.entities;

/**
 * Defines the status of an entity.
 */

public enum EntityStatus {

    /**
     * The entity is touching the floor.
     */
    OnTheFloor,

    /**
     * The entity is on the top of a stair (so is touching the floor too).
     */
    CanClimbDown,

    /**
     * The entity is on the bottom of a stair (so is touching the floor too).
     */
    CanClimbUp,

    /**
     * The entity is on a stair (so is not touching the floor).
     */
    Climbing,

    /**
     * The entity is falling.
     */
    Falling,

    /**
     * The entity is dead.
     */
    Dead;

}
