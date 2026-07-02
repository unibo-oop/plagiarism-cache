package casim.utils.range;

import java.util.Iterator;
import java.util.function.Function;

/**
 * Utility class to that easily allows to create {@link Range}. 
 */
public final class Ranges {

    private Ranges() {

    }

    /**
     * Creates a {@link Range} that goes from startInclusive to endExclusive using a function to define the step.
     * 
     * @param <T> the comparable type of the elements in the {@link Range}.
     * @param startInclusive the inclusive start of the range.
     * @param endExclusive the exclusive end of the range.
     * @param step the function that defines the step between two consecutive elements.
     * @return a {@link Range} that goes from startInclusive to endExclusive with step "step". 
     */
    public static <T extends Comparable<T>> Range<T> of(
        final T startInclusive, 
        final T endExclusive, 
        final Function<T, T> step) {
        return () -> new Iterator<T>() {
            private T current = startInclusive;

            @Override
            public boolean hasNext() {
                return current.compareTo(endExclusive) < 0;
            }

            @Override
            public T next() {
                final var output = this.current;
                this.current = step.apply(this.current);
                return output;
            }
        }; 
    }

    /**
     * Creates a {@link Range} of integers with custom step.
     * 
     * @param startInclusive the inclusive start of the range.
     * @param endExclusive the exclusive end of the range.
     * @param step the step between two consecutive elements.
     * @return an integer {@link Range} that goes from startInclusive to endExclusive with step "step".
     */
    public static Range<Integer> of(final int startInclusive, final int endExclusive, final int step) {
        return Ranges.of(startInclusive, endExclusive, x -> x + step);
    }

    /**
     * Creates a {@link Range} of integers with step of 1.
     * 
     * @param startInclusive the inclusive start of the range.
     * @param endExclusive the exclusive end of the range.
     * @return an integer {@link Range} that goes from startInclusive to endExclusive with step 1.
     */
    public static Range<Integer> of(final int startInclusive, final int endExclusive) {
        return Ranges.of(startInclusive, endExclusive, 1);
    }

    /**
     * Creates a {@link Range} of double with custom step.
     * 
     * @param startInclusive the inclusive start of the range.
     * @param endExclusive the exclusive end of the range.
     * @param step the step between two consecutive elements.
     * @return a double {@link Range} that goes from startInclusive to endExclusive with step "step".
     */
    public static Range<Double> of(final double startInclusive, final double endExclusive, final double step) {
        return Ranges.of(startInclusive, endExclusive, x -> x + step);
    }
}
