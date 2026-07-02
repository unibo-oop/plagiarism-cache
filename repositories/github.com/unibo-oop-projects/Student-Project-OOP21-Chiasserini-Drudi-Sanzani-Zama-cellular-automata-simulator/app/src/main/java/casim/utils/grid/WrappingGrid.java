package casim.utils.grid;

import java.util.function.Function;
import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;

/**
 * An N x M wrapped grid of elements of type {@link T}.
 * 
 * @param <T> the type contained in the {@link WrappingGrid}.
 */
public class WrappingGrid<T> implements Grid2D<T> {

    private final Grid2D<T> grid;

    /**
     * Create e new wrapping grid from a pre-existing one.
     * 
     * @param base initial grid.
     */
    public WrappingGrid(final Grid2D<T> base) {
        this.grid = base;
    }

    @Override
    public int getWidth() {
        return this.grid.getWidth();
    }

    @Override
    public int getHeight() {
        return this.grid.getHeight();
    }

    @Override
    public T get(final Coordinates2D<Integer> coord) {
        return this.get(coord.getX(), coord.getY());
    }

    @Override
    public void set(final Coordinates2D<Integer> coord, final T value) {
        this.set(coord.getX(), coord.getY(), value);
    }

    @Override
    public boolean isCoordValid(final Coordinates2D<Integer> coord) {
        return true;
    }

    @Override
    public Stream<T> stream() {
        return this.grid.stream();
    }

    @Override
    public T get(final int row, final int column) {
        return this.grid.get(this.getWrappedCoordinates(row, column));
    }

    @Override
    public void set(final int row, final int column, final T value) {
        this.grid.set(this.getWrappedCoordinates(row, column), value);
    }

    @Override
    public <O> Grid2D<O> map(final Function<T, O> mapper) {
        return this.grid.map(mapper);
    }

    private Coordinates2D<Integer> getWrappedCoordinates(final int row, final int column) {
        return CoordinatesUtil.of(
            this.wrap(row, this.getHeight()),
            this.wrap(column, this.getWidth()));
    }

    private int wrap(final int coord, final int maxValue) {
        return coord < 0 ? coord % maxValue + maxValue : coord % maxValue;
    }
}
