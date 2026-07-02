package model.minigames.colorgama;

import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

/**
 * This interface calculates the values of the colors for the minigame.
 *
 */
public interface ColorValuesCalculator {

    /**
     * Get the method of color value representations.
     * 
     * @return method
     *           the method of representations
     */
    ColorMethod getColorMethod();

    /**
     * Calculate the right color values according to the question type from the set.
     *
     * @param question
     *          the current question.
     *
     * @param colorValuesSet
     *          the set to calculate
     *
     * @return values
     *          the right color value
     */
    Optional<Triple<Number, Number, Number>> calculateRightColor(QuestionType question, Set<Triple<Number, Number, Number>> colorValuesSet);

    /**
     * Calculate a random color value.
     * 
     * @return values
     *          the color values
     */
    Triple<Number, Number, Number> calculateRandomColorValue();

    /**
     * Reset all values.
     */
    void reset();
}
