package model.minigames.colorgama;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.tuple.Triple;

/**
 * This class implements {@link ColorValues}.
 *
 */
public class ColorValuesImpl implements ColorValues {

    private static final String NULL_ARG = " passed is null";
    private static final String BAD_SIZE = "must be greater than 0";

    private final Set<Triple<Number, Number, Number>> colorValuesSet;
    private final ColorValuesCalculator colorValuesCalulator;
    private final int numColors;
    private final int size;
    private ColorValuesIterator iterator;

    /**
     * Constructor of {@link ColorValues}.
     * 
     * @param size
     *          the size of the iterator.
     *
     * @param numColors
     *          the number of color values.
     *
     * @param calculator
     *          the color values calculator.
     */
    public ColorValuesImpl(final int size, final int numColors, final ColorValuesCalculator calculator) {
        this.check(size, numColors, calculator);
        this.colorValuesCalulator = calculator;
        this.colorValuesSet = new HashSet<>();
        this.numColors = numColors;
        this.size = size;
    }

    private void check(final int size, final int numColors, final ColorValuesCalculator calculator) {
        if (numColors <= 0) {
            throw new IllegalArgumentException("number of colors" + BAD_SIZE);
        }
        if (size <= 0) {
            throw new IllegalArgumentException("size" + BAD_SIZE);
        }
        Objects.requireNonNull(calculator, "ColorValuesCalculator" + NULL_ARG);
    }

    private void populateSet() {
        while (colorValuesSet.size() < this.numColors) {
            colorValuesSet.add(this.colorValuesCalulator.calculateRandomColorValue());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorValuesIterator getIterator() {
        return this.iterator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetValues(final QuestionType question) {
        Objects.requireNonNull(question, "QuestionType" + NULL_ARG);
        this.colorValuesSet.clear();
        this.colorValuesCalulator.reset();
        this.populateSet();
        this.iterator = new ColorValuesIteratorImpl(this.size, this.colorValuesSet);
        this.iterator.setRightColorValues(this.colorValuesCalulator.calculateRightColor(question, this.colorValuesSet).get());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ColorMethod getColorMethod() {
        return this.colorValuesCalulator.getColorMethod();
    }
}
