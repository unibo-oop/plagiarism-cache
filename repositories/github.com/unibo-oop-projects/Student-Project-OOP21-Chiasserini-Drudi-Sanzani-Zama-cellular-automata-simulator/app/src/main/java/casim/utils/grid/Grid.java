package casim.utils.grid;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;

import casim.utils.coordinate.Coordinates;

/**
 * An N x M {@link Grid} of elements of type T.
 * 
 * @param <K> type of the {@link Coordinates} contained in Grid.
 * @param <V> type of the elements contained in Grid
 */
public interface Grid<K extends Coordinates<? extends Number>, V> {
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
     * Return the value at given coordinates.
     *
     * Throws {@link IndexOutOfBoundsException} if the coordinates are out of bound.
     * 
     * @param coord the {@link Coordinates} of the point.
     * @return the value to at coord.
     */
    V get(K coord);

    /**
     * Set an element to a given value.
     *
     * Throws {@link IndexOutOfBoundsException} if the coordinates are out of bound.
     * 
     * @param coord the coordinates of the element to set.
     * @param value to set. 
     */
    void set(K coord, V value);

    /**
     * Return true if parameter coord is inside the {@link Grid}.
     * 
     * @param coord the {@link Coordinates} of the point.
     * @return true if the parameter coord is a valid {@link Coordinates} for {@link Grid}.
     */
    boolean isCoordValid(K coord);

    /**
     * Return a Stream of the elements in {@link Grid}.
     * 
     * @return a Stream of the elements in {@link Grid}.
     */
    Stream<V> stream();

    /**
     * Return a list containing the pairs {@link Coordinates} + value of the {@link Coordinates} taken as input.
     * 
     * @param positions a list containing all the {@link Coordinates} of which the method have to get the values. 
     * @return a list containing all the pair {@link Coordinates} + value.
     */
    default List<Pair<K, V>> getValuesFrom(final List<K> positions) {
        return positions.stream()
            .filter(this::isCoordValid)
            .map(coord -> Pair.of(coord, this.get(coord)))
            .collect(Collectors.toList());
    }

}
