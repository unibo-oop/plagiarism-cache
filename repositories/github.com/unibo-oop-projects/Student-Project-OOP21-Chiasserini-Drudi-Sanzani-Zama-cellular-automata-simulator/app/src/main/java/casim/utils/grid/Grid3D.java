package casim.utils.grid;

import java.util.function.Function;

import casim.utils.coordinate.Coordinates3D;

/**
 * 3-Dimensional grid interface.
 * 
 * @param <T> the type contained in the {@link Grid3D}.
 */
 public interface Grid3D<T> extends Grid<Coordinates3D<Integer>, T> {
    /**
     * Return the width of the {@link Grid}.
     *
     * @return an integer representing the width of {@link Grid}.
     */
    int getWidth();

    /**
     * Return the height of the {@link Grid}.
     *
     * @return an integer representing the height of {@link Grid}.
     */
    int getHeight();

    /**
     * Return the depth of the {@link Grid}.
     *
     * @return an integer representing the depth of {@link Grid}.
     */
    int getDepth();

    /**
     * Return the value at the given coordinate.
     *
     * @param row of the element to get.
     * @param column of the element to get.
     * @param depth of the element to get.
     * @return the value at coord.
     */
    T get(int row, int column, int depth);

    /**
     * Sets a value at a given cell.
     * 
     * @param row of the element to set.
     * @param column of the element to set.
     * @param depth of the element to set.
     * @param value to set.
     */
    void set(int row, int column, int depth, T value);

    /**
     * Return a new {@link Grid3D} applying a mapper function to the elements of grid.
     * 
     * @param <O> the type of the elements of the output grid.
     * @param mapper the map function.
     * @return the mapped grid.
     */
    <O> Grid3D<O> map(Function<T, O> mapper);
}
