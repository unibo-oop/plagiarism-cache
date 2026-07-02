package model.navy;

import java.util.List;
import java.util.Set;

import model.basic_component.StaticPoint2D;
import model.ship.Ship;

/**
 * Representation of an aggregation of {@link Ship}.
 */
public interface Navy {
    /**
     * @return the ships
     */
    Set<Ship> getShips();

    /**
     * Permits to interact with the {@link Navy} 
     * and the {@link Ship} contained inside.
     * @param point is the {@link StaticPoint2D} on which we want to interact
     * @return true if a ship is targeted, false otherwise
     * @throws IllegalStateException if the provided point 
     *         has already undergo an interaction
     */
    boolean interact(StaticPoint2D point) throws IllegalStateException;
    /**
     * Permits to interact with the {@link Navy}
     * and the {@link Ship} contained inside.
     * @param coordX the x coordinate
     * @param coordY the y coordinate
     * @return the result of the interaction
     * @throws IllegalStateException if the provided point 
     *         has already undergo an interaction
     */
    boolean interact(int coordX, int coordY) throws IllegalStateException;

    /**
     * Returns a copy of this {@link Navy}.
     * @return the navy's copy
     */
    Navy getNavyCopy();

    /**
     * 
     * @return a {@link List} representing the sizes 
     *         of the {@link Ship} contained in the {@link Navy}
     */
    List<Integer> getNavySpecification();
}
