package breakout.model.levels;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javafx.util.Pair;

/**
 * A map that associates positions (as a pair [row,column]) with bricks (as a
 * pair [bricktype,color]).
 *
 * @param <T>
 *            The class of the elements in the grid
 */
public class Grid<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -7150042223631910087L;
    private final Map<Pair<Integer, Integer>, T> gridMap = new HashMap<>();
    private final int rows;
    private final int columns;

    /**
     * Creates a grid.
     * 
     * @param rows
     *            max number of rows
     * @param columns
     *            max number of columns
     */
    public Grid(final int rows, final int columns) {
        this.rows = rows;
        this.columns = columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                this.gridMap.put(new Pair<>(i, j), null);
            }
        }

    }

    /**
     * Add an element on the grid.
     * 
     * @param row
     *            row number
     * @param column
     *            column number
     * @param element
     *            the element to add
     */
    public void add(final int row, final int column, final T element) {
        if (row > this.rows || column > this.columns) {
            System.out.println(this.rows + " " + row);
            System.out.println(this.columns + " " + column);
            System.out.println(element);
            throw new IllegalArgumentException();
        } else {
            this.gridMap.put(new Pair<>(row, column), element);
        }
    }

    /**
     * Remove an element on the grid.
     * 
     * @param row
     *            row number
     * @param column
     *            column number
     */
    public void removeAt(final int row, final int column) {
        this.gridMap.remove(new Pair<>(row, column));
    }

    /**
     * 
     * @param row
     *            the row of the element
     * @param column
     *            the column of the element
     * @return the element in the position (row,column).
     */
    public T getElement(final int row, final int column) {
        return this.gridMap.get(new Pair<>(row, column));
    }

    /**
     * 
     * @return A the cells of the grid that have an element as a map position ->
     *         element
     */
    public Map<Pair<Integer, Integer>, T> getPresent() {
        final Map<Pair<Integer, Integer>, T> toReturn = new HashMap<>();
        this.gridMap.keySet().stream().filter(p -> this.gridMap.get(p) != null)
                .forEach(p -> toReturn.put(p, this.gridMap.get(p)));
        return Collections.unmodifiableMap(toReturn);
    }

    /**
     * Clear the grid.
     */
    public void clear() {
        this.gridMap.clear();
    }

    /**
     * 
     * @return true if the grid is empty
     */
    public boolean isEmpty() {
        return this.gridMap.isEmpty();
    }

}
