package casim.utils.grid;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import casim.utils.coordinate.Coordinates2D;
import casim.utils.range.Ranges;

/**
 * Implementation of {@link RowGrid}.
 * 
 * @param <T> the type contained in the {@link RowGrid}.
 */
public class RowGrid<T> implements Grid2D<T> {

    private final Grid2D<T> grid;

    /**
     * Costructor of a {@link RowGrid}.
     * 
     * @param grid the {@link Grid2D} used for crate a {@link RowGrid}.
     */
    public RowGrid(final Grid2D<T> grid) {
        this.grid = grid;
    }

    /**
     * Get one specific row of the {@link RowGrid}.
     * 
     * @param row the index of row to return.
     * @return list the specific row to return.
     */
    public List<T> getRow(final int row) {
        final List<T> list = new ArrayList<>();
        Ranges.of(0, this.getWidth())
            .forEach(col -> list.add(this.get(row, col)));
        return list;
    }

    /**
     * Set one specific row of the {@link RowGrid}.
     * 
     * @param row the row to set.
     * @param list the list of element to substitute to the element contains in that row.
     */
    public void setRow(final int row, final List<T> list) {
        if (list.size() != this.getWidth()) {
            throw new InvalidParameterException("Wrong list size");
        }
        Ranges.of(0, list.size())
            .forEach(col -> this.set(row, col, list.get(col)));
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
        return this.grid.get(coord);
    }

    @Override
    public void set(final Coordinates2D<Integer> coord, final T value) {
        this.grid.set(coord, value);
    }

    @Override
    public boolean isCoordValid(final Coordinates2D<Integer> coord) {
        return this.grid.isCoordValid(coord);
    }

    @Override
    public Stream<T> stream() {
        return this.grid.stream();
    }

    @Override
    public T get(final int row, final int column) {
        return this.grid.get(row, column);
    }

    @Override
    public void set(final int row, final int column, final T value) {
        this.grid.set(row, column, value);
    }

    @Override
    public <O> Grid2D<O> map(final Function<T, O> mapper) {
        return this.grid.map(mapper);
    }
}
