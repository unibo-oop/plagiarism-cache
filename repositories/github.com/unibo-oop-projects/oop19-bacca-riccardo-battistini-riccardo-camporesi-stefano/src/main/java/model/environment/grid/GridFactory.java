package model.environment.grid;

public interface GridFactory {

    /**
     *
     * @param <T>
     * @param rows the number of rows
     * @param columns the number of columns
     * @return new empty grid.
     */
    <T> Grid<T> create(int rows, int columns);

}
