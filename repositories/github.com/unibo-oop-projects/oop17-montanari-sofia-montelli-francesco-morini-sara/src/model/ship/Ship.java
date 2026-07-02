package model.ship;
import java.util.Set;

import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;

/**
 * This interfaces models a Ship, an aggregation of adjacent {@link Cell}.
 */
public interface Ship {

    /**
     * The possible status of a ship.
     * {@link #SUNK}
     * {@link #HIT}
     * {@link #ALIVE}
     */
   enum Status {
       /**
        * All the {@link Cell} of the {@link Ship} have been hit.
        */
        SUNK,
        /**
         * Some of the {@link Cell} of the {@link Ship} have been hit.
         */
        HIT,
        /**
         * None of the {@link Cell} of the {@link Ship} have been hit.
         */
        ALIVE;
    }

   /**
    *The possible orientation of the ship.
    *{@link #VERTICAL}
    *{@link #HORIZZONTAL}
    *{@link #INDEFINABLE}
    */
   enum Orientation {
       VERTICAL,
       HORIZZONTAL,
       /**
        * if the {@link Ship} has a size of 1 we can't define an {@link Orientation}.
        */
       INDEFINABLE;
   }

   /**
    * The maximum size for a ship.
    */
   int MAX_SHIP_SIZE = 4;

   /**
    * Getter for the {@link Ship} size.
    * @return the size of the {@link Ship}, i.e: the number of the @link {@link Cell}
    */
   int getSize();

   /**
    * Getter for the {@link Ship} {@link Orientation}.
    * @return the {@link Orientation} of the {@link Ship}
    */
   Orientation getOrientation();

   /**
    * Getter for the {@link Set} of {@link Cell} of the {@link Ship}.
    * @return the {@link Set} of {@link Cell} that make up the {@link Ship}
    */
   Set<Cell> getCellSet();

   /**
    * Getter for the {@link Status} of the {@link Ship}.
    * @return the {@link Status} of the {@link Ship}
    */
   Status getStatus();

   /**
    * Check if the provided {@link StaticPoint2D} is adjacent to the {@link Ship}.
    * @param point is the {@link StaticPoint2D} we want to check the adjacency
    * @return if the provided {@link StaticPoint2D} is adjacent to the {@link Ship}
    */
   boolean adjacent(StaticPoint2D point);

   /**
    * Check if the provided {@link Ship} is adjacent to the {@link Ship}.
    * @param ship is the {@link Ship} we want to check the adjacency
    * @return if the provided {@link Ship} is adjacent to the {@link Ship}
    */
   boolean adjacent(Ship ship);

   /**
    * Manage the interaction with the ship.
    * @param point is the {@link StaticPoint2D} with which we want to interact
    * @return the result of the interaction
    * @throws IllegalStateException if the specified {@link Cell} of the {@link Ship}
    *         has already undergo an interaction
    */
   boolean interact(StaticPoint2D point) throws IllegalStateException;

}
