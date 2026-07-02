package model.environment.grid;

import java.util.Map;
import java.util.stream.Stream;
import model.environment.Point;

/**
 * Interface defining a grid.
 *
 * @param <T> the type of elements of the grid
 */
public interface Grid<T> {

    /**
     * @return the number of rows
     */
    int getNumberRows();

    /**
     * @return the number of columns
     */
    int getNumberColumns();

    /**
     * @return a new stream composed by the keys of the genericMap
     */
    Stream<Point> getKeyStream();

    /**
     * @return a stream composed by the values of the genericMap
     */
    Stream<T> getValueStream();

    /**
     * 
     * @param pos the pos of where the element is
     * @return the value associated with this position
     */
    T getElement(Point pos);

    /**
     * 
     * @param genericMap the new map ready to be set
     */
    void setGenericMap(Map<Point, T> genericMap);

    /**
     *
     * @return the map
     */
    Map<Point, T> getGenericMap();

    /**
     * 
     * @param pos the key of the map
     * @param value the value of the map to be set
     */
    void setElement(Point pos, T value);

}
