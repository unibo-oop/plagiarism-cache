package utility;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RangeFactoryImpl implements RangeFactory {
    @Override
    public Range standardRange(final int limit) throws IllegalArgumentException {
        final int start = 0;
        final int step = 1;
        return this.rangeWithStepFrom(start, limit, step);
    }

    @Override
    public Range rangeWithStep(final int limit, final int step) throws IllegalArgumentException {
        final int start = 0;
        return this.rangeWithStepFrom(start, limit, step);
    }

    @Override
    public Range standardRangeFrom(final int start, final int limit) throws IllegalArgumentException {
        final int step = 1;
        return this.rangeWithStepFrom(start, limit, step);
    }

    @Override
    public Range rangeWithStepFrom(final int start, final int limit, final int step) throws IllegalArgumentException {
        if (step != 0 && (limit - start) / step < 0) {
            throw new IllegalArgumentException();
        }
        return new Range() {
            @Override
            public int getStart() {
                return start;
            }

            @Override
            public int getLimit() {
                return limit;
            }

            @Override
            public int getStep() {
                return step;
            }

            @NonNull
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<>() {

                    private int current = start;

                    @Override
                    public boolean hasNext() {
                        return current < limit;
                    }

                    @Override
                    public Integer next() {
                        if (hasNext()) {
                            return current++;
                        } else {
                            throw new NoSuchElementException("Range reached the end");
                        }
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException("Can't remove values from a Range");
                    }
                };
            }
        };
    }
}
