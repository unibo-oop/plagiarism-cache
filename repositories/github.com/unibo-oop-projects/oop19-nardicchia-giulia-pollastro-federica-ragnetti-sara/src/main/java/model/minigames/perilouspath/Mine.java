package model.minigames.perilouspath;

import utility.Pair;

/**
 * Represents a mine.
 */
public interface Mine {

    /**
     * Gets the mine's position.
     * 
     * @return the mine coordinate.
     */
    Pair<Integer, Integer> getMinePosition();

    /**
     * Sets the state of this mine as exploded.
     */
    void setExploded();

    /**
     * Check if the mine is exploded.
     * 
     * @return true if this mine is exploded, false otherwise.
     */
    boolean isExploded();
}
