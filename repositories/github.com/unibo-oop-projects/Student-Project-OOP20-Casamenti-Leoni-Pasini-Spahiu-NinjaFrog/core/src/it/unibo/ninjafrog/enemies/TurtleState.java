package it.unibo.ninjafrog.enemies;

/**
 * Enum that is useful for TurtleViewImpl.
 */

public enum TurtleState {
    /**
     * State where the turtle's spikes are disappearing.
     */
    SPIKES_IN,
    /**
     * State where the turtle's spikes are appearing.
     */
    SPIKES_OUT,
    /**
     * State where the turtle's spikes are fully out.
     */
    SPIKES,
    /**
     * State where the turtle's spike are fully in.
     */
    NO_SPIKES
}
