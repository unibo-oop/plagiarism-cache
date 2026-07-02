package it.unibo.oop.manpac.model;

/**
 * Represents all actions that can happen to an entity in a collision.
 *
 */
public enum Action {

    /**
     * The event didn't have any effect.
     *
     */
    NOTHING_HAPPENS, 
    /**
     * Represents the collision between Pac-Man and a pill.
     *
     */
    PILL_EATEN, 
    /**
     * Represents the collision between Pac-Man and a power pill.
     *
     */
    POWER_PILL_EATEN, 
    /**
     * The collision produce a change in direction towards positive y.
     *
     */
    DIRECTION_CHANGED_UP,
    /**
     * The collision produce a change in direction towards negative y.
     *
     */
    DIRECTION_CHANGED_DOWN, 
    /**
     * The collision produce a change in direction towards negative x.
     *
     */
    DIRECTION_CHANGED_LEFT, 
    /**
     * The collision produce a change in direction towards positive x.
     *
     */
    DIRECTION_CHANGED_RIGHT, 
    /**
     * The collision stopped the entity.
     *
     */
    DIRECTION_CHANGED_STOP, 
    /**
     * Represents the collision between Pac-Man and a portal (tunnels at sides of the arena).
     *
     */
    PACMAN_EFFECT,
    /**
     * Represents the collision between Pac-Man and a phantom with the effect of the power pill.
     *
     */
    PACMAN_ATE_PHANTOM,
    /**
    * Represents the collision between Pac-Man and a phantom without the effect of the power pill.
    *
    */
    PHANTOM_KILLED_PACMAN, 
    /**
     * Pac-Man ate the last pill in the level and won.
     */
    WIN,
    /**
     * The player lost the game.
     *
     */
    GAME_OVER,
    /**
     * Represents the case where something went wrong.
     *
     */
    MALFUNCTION 
}
