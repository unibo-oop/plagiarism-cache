package main.java.com.model;

import java.util.Set;


import main.java.com.utility.Position;

/**
 * The map of the game in which all of the game entities are placed and are able to move.
 *
 */
public interface GameMap {

    /**
     * 
     * @return a set of all of the cells of the map (all of the possible coordinates).
     */
   Set<Position> getAllCells();

   /**
    * 
    * @return a set of all the cells that are not currently occupied by one of the game's entities.
    * @param snake the snake game entity
    */
   Set<Position> getFreeCells(SnakeEntity snake);

   /**
    * 
    * @return the size of the map on the x-coordinate.
    */
   int getXMapSize();

   /**
    * 
    * @return the size of the map on the y-coordinate.
    */
   int getYMapSize();

}
