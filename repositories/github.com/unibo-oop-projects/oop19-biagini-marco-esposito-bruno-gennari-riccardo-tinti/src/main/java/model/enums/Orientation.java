package model.enums;

import model.util.Pair;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;


/**
 * 
 * Enumeration type to indicate the orientation of a cells block inside a playground.
 * It could be vertical or horizontal.
 *
 */
public enum Orientation {

    /**
     * Orientation is horizontal.
     */
    HORIZONTAL(new Pair<>(0, 1)),

    /**
     * Orientation in vertical.
     */
    VERTICAL(new Pair<>(1, 0));

    private Pair<Integer, Integer> direction;

    Orientation(final Pair<Integer, Integer> direction) {
        this.direction = direction;
    }

    /**
     * Get a list of cells used in the direction of object "Orientation",
     * given the initial cell and the number of cells these will be used.
     * 
     * @param initialCell - The cell where to start.
     * @param cellsNumber - Number of cell used along direction.
     * @return a list of cells used
     */
    public List<Pair<Integer, Integer>> cellsUsedList(final Pair<Integer, Integer> initialCell, final int cellsNumber) {
        return Stream.iterate(initialCell, 
                        i -> new Pair<Integer, Integer>(i.getX() + this.direction.getX(), i.getY() + this.direction.getY()))
                .limit(cellsNumber)
                .collect(toList());
    }
}
