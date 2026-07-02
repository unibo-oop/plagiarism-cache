package model.navy;

import java.util.List;
import java.util.Set;

import model.basic_component.StaticPoint2D;
import model.ship.Ship;

/**
 * A builder for the {@link Navy}.
 */
public interface NavyBuilder {

    /**
     * Adds a {@link Ship} to the {@link Navy}.
     * @param ship the {@link Ship} to add.
     * @throws IllegalArgumentException if the {@link Ship}
     *         can't be added to the {@link Navy}
     * @throws IllegalStateException if a {@link Ship}
     *         is added after the build
     */
     void addShip(Ship ship) throws IllegalArgumentException, IllegalStateException;

     /**
      * 
      * @param ship the {@link Ship} to remove from the {@link Navy}
      * @throws IllegalStateException if the {@link Ship} is removed after the build.
      * @throws IllegalArgumentException if the {@link Ship} is not present into the
      */
     void removeShip(Ship ship) throws IllegalStateException, IllegalArgumentException;

     /**
      * @return the {@link Set} of the available {@link StaticPoint2D}
      */
     Set<StaticPoint2D> getAvailablePositions();

     /**
      * @param size the size of the ship
      * @return the set representing where a ship of a given size can be positioned
      */
     Set<StaticPoint2D> getAvaiablePositions(int size);
     /**
      * @return the {@link Set} of the available {@link StaticPoint2D}
      *         for placing a ship of the given 
      * size begging from the given {@link StaticPoint2D}
      * @param firstPoint is the first {@link StaticPoint2D} of the ship 
      * @param size is the size of the {@link Ship}
      * @throws IllegalArgumentException if the size is not
      *         compatible with a {@link Ship}
      * @throws IllegalArgumentException if the provided {@link StaticPoint2D}
      *         is not a valid position
      */
     Set<StaticPoint2D> getAvailablePositionsSecondCord(StaticPoint2D firstPoint, int size) throws IllegalArgumentException;

     /**
      * @return the {@link Set} of the available {@link StaticPoint2D}
      *         for placing a ship starting 
      * from the given {@link StaticPoint2D} without knowing its size
      * @param firstPoint is the first {@link StaticPoint2D} of the ship 
      * @throws IllegalArgumentException if the size is not
      *         compatible with a {@link Ship}
      * @throws IllegalArgumentException if the provided {@link StaticPoint2D}
      *         is not a valid position
      */
     Set<StaticPoint2D> getAvailablePositionsSecondCord(StaticPoint2D firstPoint);

     /**
     * @return the builded {@link Navy}
     * @throws IllegalStateException if the {@link Navy} can't be builded
     */
     Navy buildNavy() throws IllegalStateException;

     /**
      * getter for the missing {@link Ship} number.
      * @return the {@link List} of missing {@link Ship}, divided into sizes.
      */
     List<Integer> getMissingShips();

     /**
      * 
      * @return a set composed by the point occupied by the navy till this point
      */
     Set<StaticPoint2D> getAllOccupiedPosition();

     /**
      * Getter for the grid side.
      * @return the grid size.
      */
     int getGridSize();

     /**
      * @return if the {@link Navy} can be builded.
      */
     boolean canBuild();

     /**
      * complete reset of the internal status.
      */
     void reset();
}
