package model.minigames.perilouspath;

/**
 * Represents the difficulty at each level.
 */
public interface PerilousPathDifficulty {

    /**
     * Specific size of the current difficulty.
     * 
     * @return the size
     */
    int getSize();

    /**
     * Specific amount of mines of the current difficulty.
     * 
     * @return the number of mines
     */
    int getNumMines();
}
