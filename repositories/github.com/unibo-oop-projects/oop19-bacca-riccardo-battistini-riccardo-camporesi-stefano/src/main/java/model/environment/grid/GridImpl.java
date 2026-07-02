package model.environment.grid;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;
import model.environment.Point;

/**
 * Concrete implementation of a grid.
 * 
 * @param <T> the type of elements of the grid
 */
public final class GridImpl<T> implements Grid<T> {

    private Map<Point, T> genericMap = new HashMap<>();
    private final int rows;
    private final int columns;

    /**
     *
     * @param rows the number of rows
     * @param columns the number of columns
     */
    public GridImpl(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    /**
     * @return number of rows in this grid
     */
    @Override
    public int getNumberRows() {
        return this.rows;
    }

    /**
     * @return number of columns in this grid
     */
    @Override
    public int getNumberColumns() {
        return this.columns;
    }

    /**
     * @return a stream of genericMap keySet
     */
    @Override
    public Stream<Point> getKeyStream() {
        return this.genericMap.keySet().stream();
    }

    /**
     * @return a generic stream for genericMap values
     */
    @Override
    public Stream<T> getValueStream() {
        return this.genericMap.values().stream();
    }

    /**
     * @return the element in the genericMap at the specified position
     * @param pos the key of the associated value to be returned
     */
    @Override
    public T getElement(final Point pos) {
        return this.genericMap.get(pos);
    }

    /**
     * @return the genericMap
     */
    @Override
    public Map<Point, T> getGenericMap() {
        return this.genericMap;
    }

    /**
     * @param genericMap the genericMap to set
     */
    @Override
    public void setGenericMap(final Map<Point, T> genericMap) {
        this.genericMap = genericMap;
    }

    @Override
    public void setElement(final Point pos, final T value) {
        this.genericMap.put(pos, value);
    }

}
