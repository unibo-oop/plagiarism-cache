package it.unibo.bmbman.model.utilities;
/**
 * enum to manage the different states of the bomb.
 */
public enum BombState {
    /**
     * The first state of the bomb.
     */
    PLANTED,
    /**
     * the timer of the bomb finish and the bomb explode.
     */
    IN_EXPLOSION,
    /**
     * the explosion happened.
     */
    EXPLODED;
}
