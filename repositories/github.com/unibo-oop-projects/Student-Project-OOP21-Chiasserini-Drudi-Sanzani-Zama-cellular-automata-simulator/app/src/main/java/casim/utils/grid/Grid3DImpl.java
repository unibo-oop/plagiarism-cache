package casim.utils.grid;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates3D;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.range.Ranges;

/**
 * Implementation of {@link Grid3D}.
 * 
 * @param <T> the type contained in the {@link Grid3D}.
 */
 public class Grid3DImpl<T> implements Grid3D<T> {

    private final int rows;
    private final int columns;
    private final int depth;
    private  final List<List<List<T>>> grid;

    /**
     * Construct a new {@link Grid3D} filled with nulls.
     * 
     * @param rows the number of the rows of the grid.
     * @param columns the number of the columns of the grid.
     * @param depth the depth of the grid.
     */
    public Grid3DImpl(final int rows, final int columns, final int depth) {
        this(rows, columns, depth, () -> null);
    }

    /**
     * Construct a new {@link Grid3D} with a default value supplier.
     * 
     * @param rows the number of the rows of the grid.
     * @param columns the number of the columns of the grid.
     * @param depth the depth of the grid.
     * @param defaultValue the default value supplier.
     */
    public Grid3DImpl(final int rows, final int columns, final int depth, final Supplier<T> defaultValue) {
        this.rows = rows;
        this.columns = columns;
        this.depth = depth;

        this.grid = Ranges.of(0, depth).stream()
                .map(z -> Ranges.of(0, rows).stream()
                .map(x -> Ranges.of(0, columns).stream()
                .map(y -> defaultValue.get())
                .collect(Collectors.toList()))
                .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * Construct a new {@link Grid3D} with a function that maps coordinates to values.
     * 
     * @param rows the number of the rows of the grid.
     * @param columns the number of the columns of the grid.
     * @param depth the depth of the grid.
     * @param valueFunction a function that maps coordinates to values.
     */
    public Grid3DImpl(final int rows, final int columns, final int depth, final Function<Coordinates3D<Integer>, T> valueFunction) {
        this.rows = rows;
        this.columns = columns;
        this.depth = depth;

        this.grid = Ranges.of(0, depth).stream()
                .map(z -> Ranges.of(0, rows).stream()
                .map(x -> Ranges.of(0, columns).stream()
                .map(y -> valueFunction.apply(CoordinatesUtil.of(x, y, z)))
                .collect(Collectors.toList()))
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
    public int getDepth() {
        return this.depth;
    }

    @Override
    public T get(final int row, final int column, final int depth) {
        this.throwIfOutOfBound(CoordinatesUtil.of(row, column, depth));
        return this.grid.get(depth).get(row).get(column);
    }

    @Override
    public void set(final int row, final int column, final int depth, final T value) {
        this.throwIfOutOfBound(CoordinatesUtil.of(row, column, depth));
        this.grid.get(depth).get(row).set(column, value);
    }

    @Override
    public T get(final Coordinates3D<Integer> coord) {
        this.throwIfOutOfBound(coord);
        return this.get(coord.getX(), coord.getY(), coord.getZ());
    }

    @Override
    public void set(final Coordinates3D<Integer> coord, final T value) {
        this.throwIfOutOfBound(coord);
        this.set(coord.getX(), coord.getY(), coord.getZ(), value);
    }

    @Override
    public boolean isCoordValid(final Coordinates3D<Integer> coord) {
        return CoordinatesUtil.isValid(coord, this.rows, this.columns, this.depth);
    }

    @Override
    public Stream<T> stream() {
        return this.grid.stream().flatMap(e -> e.stream().flatMap(List::stream));
    }

    @Override
    public <O> Grid3D<O> map(final Function<T, O> mapper) {
        return new Grid3DImpl<>(this.rows, this.columns, this.depth, coord -> mapper.apply(this.get(coord)));
    }

    private void throwIfOutOfBound(final Coordinates3D<Integer> coord) {
        if (!this.isCoordValid(coord)) {
            throw new IndexOutOfBoundsException("Size: " + this.getHeight() + " x " + this.getWidth() + "Coord: " + coord);
        }
    }

}
