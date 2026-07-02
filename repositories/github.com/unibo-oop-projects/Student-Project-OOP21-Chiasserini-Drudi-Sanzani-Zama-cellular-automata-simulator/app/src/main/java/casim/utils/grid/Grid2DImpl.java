package casim.utils.grid;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.range.Ranges;

/**
 * Implementation of {@link Grid2D}.
 * 
 * @param <T> the type of the elements contained in {@link Grid2DImpl}.
 */
 public class Grid2DImpl<T> implements Grid2D<T> {

    private final int rows;
    private final int columns;
    private final List<List<T>> grid;

    /**
     * Construct a new {@link Grid2D} filled with nulls.
     * 
     * @param rows the number of the rows of the {@link Grid2D}.
     * @param columns the number of the columns of the {@link Grid2D}.
     */
    public Grid2DImpl(final int rows, final int columns) {
        this(rows, columns, () -> null);
    }

    /**
     * Construct a new {@link Grid2D} with a default value supplier.
     * 
     * @param rows the number of the rows of the {@link Grid2D}.
     * @param columns the number of the columns of the {@link Grid2D}.
     * @param defaultValue the default value supplier.
     */
    public Grid2DImpl(final int rows, final int columns, final Supplier<T> defaultValue) {
        this.rows = rows;
        this.columns = columns;
        this.grid = Ranges.of(0, rows).stream()
            .map(x -> Ranges.of(0, columns).stream()
            .map(y -> defaultValue.get())
            .collect(Collectors.toList()))
            .collect(Collectors.toList());
    }

    /**
     * Construct a new {@link Grid2D} with a function that maps coordinates to values.
     * 
     * @param rows the number of the rows of the {@link Grid2D}.
     * @param columns the number of the columns of the {@link Grid2D}.
     * @param valueFunction the function that maps coordinates to value.
     */
    public Grid2DImpl(final int rows, final int columns, final Function<Coordinates2D<Integer>, T> valueFunction) {
        this.rows = rows;
        this.columns = columns;
        this.grid = Ranges.of(0, rows).stream()
            .map(x -> Ranges.of(0, columns).stream()
                .map(y -> valueFunction.apply(CoordinatesUtil.of(x, y)))
                .collect(Collectors.toList()))
            .collect(Collectors.toList());
    }

    @Override
    public int getWidth() {
        return this.columns;
    }

    @Override
    public int getHeight() {
        return this.rows;
    }

    @Override
    public T get(final int row, final int column) {
        this.throwIfOutOfBound(CoordinatesUtil.of(row, column));
        return this.grid.get(row).get(column);
    }

    @Override
    public void set(final int row, final int column, final T value) {
        this.throwIfOutOfBound(CoordinatesUtil.of(row, column));
        this.grid.get(row).set(column, value);
    }

    @Override
    public T get(final Coordinates2D<Integer> coord) {
        this.throwIfOutOfBound(coord);
        return this.grid.get(coord.getX()).get(coord.getY());
    }

    @Override
    public void set(final Coordinates2D<Integer> coord, final T value) {
        this.set(coord.getX(), coord.getY(), value);
    }

    @Override
    public boolean isCoordValid(final Coordinates2D<Integer> coord) {
        return CoordinatesUtil.isValid(coord, this.rows, this.columns);
    }

    @Override
    public Stream<T> stream() {
        return this.grid.stream().flatMap(List :: stream);
    }

    @Override
    public <O> Grid2D<O> map(final Function<T, O> mapper) {
        return new Grid2DImpl<>(
            this.rows, 
            this.columns, 
            coord -> mapper.apply(this.get(coord))
        );
    }

    private void throwIfOutOfBound(final Coordinates2D<Integer> coord) {
        if (!this.isCoordValid(coord)) {
            throw new IndexOutOfBoundsException("Size: " + this.getHeight() + " x " + this.getWidth() + "Coord: " + coord);
        }
    }

}
