package model.minigames.perilouspath;

/**
 * Represents the state of a mine.
 */
public enum MineState {

    /**
     * The mine is not detonated.
     */
    UNDETONATED,

    /**
     * The mine is exploded.
     */
    EXPLODED;

    /**
     * Check if the user hits the mine.
     * 
     * @return true if the mine is exploded, false otherwise
     */
    public boolean isExploded() {
        return this == UNDETONATED ? false : true;
    }
}
