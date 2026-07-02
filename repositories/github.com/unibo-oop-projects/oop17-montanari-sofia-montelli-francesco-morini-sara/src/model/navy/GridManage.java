package model.navy;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import model.basic_component.StaticPoint2D;
import model.ship.Ship;

/**
 * This interface defines the grid management methods for ship positioning.
 * No public modifier required, is just an utility for the {@link NavyBuilder}.
 */
interface GridManage {
    /**
     * The possible status of a {@link StaticPoint2D} in the {@link GridManage}.
     * {@link #FREE}
     * {@link #SHIP}
     * {@link #BOUND}
     */
   enum Status {
       /**
        * All the {@link StaticPoint2D} of the {@link GridManage} that are free.
        */
        FREE,
        /**
         * All the {@link StaticPoint2D} of the {@link GridManage} where there is a ship.
         */
        SHIP,
        /**
         * All the {@link StaticPoint2D} of the {@link GridManage} around a ship.
         */
        BOUND;
    }
    /**
     * 
     * @return the length of the grid side
     */
    int getSide();
    /**
     * 
     * @return the list of the remaining ship to position
     */
    List<Integer> getRemaining();
    /**
     * 
     * @return the list of the initial composition of the navy to position
     */
    List<Integer> getInitial();
    /**
     * 
     * @return a set of {@link StaticPoint2D} of free cells in the grid
     */
    Set<StaticPoint2D> getSetFreeCell();
    /**
     * 
     * @return a set of the unavailable cell
     */
    Set<StaticPoint2D> getUnavailableCell();
    /**
     * 
     * @param first is the first coordinate (settled) of the ship
     * @param size is the dimension of the ship to place
     * @return a set of the possible positions to select 
     *         for the last coordinate of the ship
     * @throws IllegalArgumentException if size is lower than 
     *         1 or bigger than the possible maximum size of a ship
     */
    Set<StaticPoint2D> getPossiblePositionSecondCoord(StaticPoint2D first, int size) throws IllegalArgumentException;
    /**
     * 
     * @param first is the first coordinate (settled) of the ship
     * @return a set of the possible positions to select for 
     *         the last coordinate of the ship
     */
    Set<StaticPoint2D> getPossiblePositionSecondCoord(StaticPoint2D first);
    /**
     * 
     * @param size the size of the ship size that you want to position
     * @return a set of {@link StaticPoint2D} where a ship with dimension = size can be positioned
     */
    Set<StaticPoint2D> getPositionWithSize(int size);
    /**
     * 
     * @param ship is the ship to place
     * @throws IllegalArgumentException if the position of the 
     *         ship is already occupied
     */
    void placeShip(Ship ship) throws IllegalArgumentException;
    /**
     * 
     * @param ship is the ship to remove
     * @throws IllegalArgumentException if the ship is not present in the grid
     */
    void removeShip(Ship ship) throws IllegalArgumentException;
    /**
     * Reset all the cells in the grip (set all as free).
     */
    void reset();
    /**
     * 
     * @param side the length of grid side
     * @param composition quantity of the ship to place
     * @return true: if the grid could contain all the ship in composition ,
     *         false: otherwise 
     */
     static boolean checkComposition(final int side, final List<Integer> composition) {
         return composition.size() == Ship.MAX_SHIP_SIZE 
                &&
                side > 0
                &&
                IntStream.range(0, Ship.MAX_SHIP_SIZE)
                         .map(var -> composition.get(var) * (var + 1))
                         .sum() <= (side * side) / 3;
     }
}
