package model.den;

/**
 * This interface represents the top lane of the game world.
 * It's a lane with many dens the frog can safely jumps into.
 * Dens can be free or not.
 * A frog can only jump into free dens.
 */
public interface Den {

    /**
     * Checks whether the den is free or not.
     * @return true if den is free, false otherwise.
     */
    boolean isDenFree();

    /**
     * Occupy the den.
     */
    void occupy();

    /**
     * Method used to obtain the center of the den in game lane coordinates.
     * @return the center of the den.
     */
    double getCenter();

}
