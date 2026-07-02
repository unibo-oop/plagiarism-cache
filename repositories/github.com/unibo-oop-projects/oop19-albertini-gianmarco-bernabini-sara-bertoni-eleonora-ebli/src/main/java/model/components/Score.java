package model.components;

/**
 * 
 * Useful functionalities to build a score with a combo value.
 *
 */
public interface Score extends WordSetPause, Runnable {

    /**
     * Returns the current score.
     * 
     * @return score
     */
    int getGlobalScore();

    /**
     * Computes and adds points to the global score based on the number of typed letters.
     * 
     * @param typedLetters
     *          the number of typed letters
     */
    void setPoints(int typedLetters);

    /**
     * Increments the combo by one.
     */
    void incCombo();

    /**
     * Resets the combo.
     */
    void resetCombo();

    /**
     * Resets the global score and the combo.
     */
    void reset();
}
