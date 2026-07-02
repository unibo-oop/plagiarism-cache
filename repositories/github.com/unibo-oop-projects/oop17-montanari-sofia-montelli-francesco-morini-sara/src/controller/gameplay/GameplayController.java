package controller.gameplay;

import java.util.List;

import controller.SpecificController;
import model.basic_component.Cell;
import model.basic_component.StaticPoint2D;
import model.navy.Navy;
import model.ship.Ship;

/**
 * Mini-controller for managing a game play session.
 */
public interface GameplayController extends SpecificController {

    /**
     * Getter for the current player name.
     * @return the name of the player.
     */
    String getPlayerName();

    /**
     * Getter for the enemy name.
     * @return the name of the enemy.
     */
    String getEnemyName();

    /**
     * Getter for the grid size.
     * @return the size of the grid.
     */
    int getGridSize();

    /**
     * Getter for the player navy.
     * @return the player's navy.
     */
    Navy getPlayerNavy();

    /**
     * Getter for the player hit spot list.
     * @return a @link List of {@link Cell} representing the shot take.
     */
    List<Cell> getPlayerHittedSpotSet();

    /**
     * Getter for the enemy hit spot list.
     * @return a @link List of {@link Cell} representing the shot take.
     */
    List<Cell> getEnemyHittedSpotSet();

    /**
     * Method for interact with the enemy.
     * @param coordinate 
     *          the {@link StaticPoint2D} representing the position on the grid
     * @throws IllegalArgumentException 
     *          if the provided position is not coherent with the grid 
     *          (ex: out of border)
     * @throws IllegalStateException
     *           if the specified position can't undergo an interaction
     */
    void shootEnemy(StaticPoint2D coordinate) throws IllegalArgumentException, IllegalStateException;

    /**
     * Getter for the player's password.
     * @return the player's password.
     */
    String getPlayerPassword();
    /**
     * @return the @link List of the sunken ship of the enemy
     */
    List<Ship> getEnemeySunkenShip();
}
