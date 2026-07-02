package model.minigames.perilouspath;

import model.DifficultyLevel;

/**
 * Represents the generic difficulty builder.
 *
 * @param <X> 
 *            the specific difficulty builder
 */
public interface DifficultyBuilder<X> {

    /**
     * Initialize the current difficulty level.
     * 
     * @param currentDifficultyLevel
     *              the current difficulty level
     * @return a generic difficulty builder
     */
    X setDifficultyLevel(DifficultyLevel currentDifficultyLevel); //NOPMD

    /**
     * Modify the default size in a specific difficulty.
     * 
     * @param size
     *          the dimension's size
     * @return a generic difficulty builder
     */
    X setSize(int size); //NOPMD
}
