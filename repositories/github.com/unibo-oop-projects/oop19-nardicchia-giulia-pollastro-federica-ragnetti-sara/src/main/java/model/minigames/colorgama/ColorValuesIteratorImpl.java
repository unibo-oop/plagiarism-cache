package model.minigames.colorgama;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Triple;

/**
 * This class implements {@link ColorValuesIterator}.
 *
 */
public class ColorValuesIteratorImpl implements ColorValuesIterator {

    private static final String NULL_ARG = "passed is null";
    private static final String BAD_SIZE = "size must be greater than 0";

    private final Set<Triple<Number, Number, Number>> colorValuesSet;
    private Optional<Triple<Number, Number, Number>> rightValues;
    private final int rightIndex;
    private final int size;
    private int counter;

    /**
     * Constructor of {@link ColorValuesIterator}.
     * 
     * @param size
     *          the size of the iterator
     *
     * @param colorValuesSet
     *          the set to iterate
     */
    public ColorValuesIteratorImpl(final int size, final Set<Triple<Number, Number, Number>> colorValuesSet) {
        this.checkArgument(size, colorValuesSet);
        this.colorValuesSet = colorValuesSet;
        this.size = size;
        this.rightIndex = new Random().nextInt(this.size);
    }

    private void checkArgument(final int size, final Set<Triple<Number, Number, Number>> colorValuesSet) {
        if (size <= 0) {
            throw new IllegalArgumentException(BAD_SIZE);
        }
        Objects.requireNonNull(colorValuesSet, "Set<Triple<Number, Number, Number>>" + NULL_ARG);
    }

    private Triple<Number, Number, Number> getRandomColorValues() {
        return this.colorValuesSet.stream()
                                  .collect(Collectors.toList())
                                  .get(new Random().nextInt(this.colorValuesSet.size()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return this.counter < this.size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Triple<Number, Number, Number> next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        return (this.counter++ == this.rightIndex) ? this.rightValues.orElseThrow() : this.getRandomColorValues();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRightColorIndex() {
        return this.rightIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Triple<Number, Number, Number> getRightColorValues() {
        return this.rightValues.orElseThrow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRightColorValues(final Triple<Number, Number, Number> rightValues) {
        this.rightValues = Optional.of(rightValues);
    }
}
