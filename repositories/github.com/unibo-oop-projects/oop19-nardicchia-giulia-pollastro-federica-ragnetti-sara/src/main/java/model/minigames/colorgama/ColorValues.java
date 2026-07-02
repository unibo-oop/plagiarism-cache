package model.minigames.colorgama;

/**
 * This interface represents the color values of the minigame.
 *
 */
public interface ColorValues {

    /**
     * Getter for a color values iterator.
     * 
     * @return iterator
     *          a color values iterator
     */
    ColorValuesIterator getIterator();

    /**
     * Get the method of color value representations.
     * 
     * @return method
     *          the method of representations
     */
    ColorMethod getColorMethod();

    /**
     * Reset all values.
     * 
     * @param question
     *          the current question type.
     */
    void resetValues(QuestionType question);
}
