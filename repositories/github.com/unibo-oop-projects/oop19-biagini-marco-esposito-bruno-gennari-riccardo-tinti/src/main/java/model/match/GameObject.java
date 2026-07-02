package model.match;

/**
 * A generic object belonging to the game world
 * 
 */
public interface GameObject {

    /**
     * @return the size of the game object
     */
    int getSize();

    /**
     * Hits a ship
     * 
     * @return true if game object was destroyed
     */
    boolean hit();

}
