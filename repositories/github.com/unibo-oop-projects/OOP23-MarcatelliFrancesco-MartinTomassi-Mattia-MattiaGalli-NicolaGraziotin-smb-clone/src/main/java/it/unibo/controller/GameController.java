package it.unibo.controller;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Point2D;
import it.unibo.model.GameModel;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.tiles.Tile;
import it.unibo.view.window.GameWindow;

/**
 * Interface representing a Controller for Managing the SuperMeatBoy Game.
 */
public interface GameController {

    /**
     * Starts the GameWindow.
     */
    void startWindow();

    /**
     * Starts the game loop.
     */
    void start();

    /**
     * Retrieves the grid of tiles representing the stationary object.
     *
     * @return A list of lists of tiles, where each list represents a row of tiles.
     */
    List<List<Optional<Tile>>> getStationary();

    /**
     * Retrieves a list of CircularSaw present in the game.
     *
     * @return A list of CircularSaw.
     */
    List<CircularSaw> getSaws();

    /**
     * Retrieves a list of Platform present in the game.
     *
     * @return A list of Platform.
     */
    List<Platform> getPlatforms();

    /**
     * Returns the BandageGirl parsed from the TMX file.
     *
     * @return The BandageGirl parsed from the TMX file.
     */
    BandageGirl getBandageGirl();

    /**
     * Returns the MeatBoy parsed from the TMX file.
     *
     * @return The MeatBoy parsed from the TMX file.
     */
    MeatBoy getMeatBoy();

    /**
     * Returns the starting coordinates of the MeatBoy entity in the game.
     *
     * @return The starting coordinates of the MeatBoy entity.
     */
    Point2D<Double, Double> getMeatBoyStartCoord();

    /**
     * Retrieves the number of Map rows.
     *
     * @return The number of rows.
     */
    int getNumRows();

    /**
     * Retrieves the number of Map columns.
     *
     * @return The number of columns.
     */
    int getNumCols();

    /**
     * Retrieves the GameModel.
     *
     * @return The GameModel.
     */
    GameModel getGameModel();

    /**
     * Signals a victory event.
     * This method is called to indicate that the game has been won.
     */
    void victory();

    /**
     * This method is called to indicate that the player collides with an obstacles or fall from the map and died.
     */
    void isDead();

    /**
     * Retrieves the number of deaths of the player.
     * 
     * @return the number of deaths.
     */
    int getDeaths();

    /**
     * Retrieves the time record of the level.
     * 
     * @return the time record of the level.
     */
    int getTimeRecord();

    /**
     * Retrieves the GameWindow associated with this object.
     * This method returns the GameWindow instance associated with the current object.
     *
     * @return The GameWindow instance associated with this object.
     */
    GameWindow getGameWindow();

    /**
     * Handles the escape action.
     * This method is called when the escape key is pressed or when an escape action is triggered.
     * It typically performs actions related to pausing the game, showing menus, or handling other escape-related functionality.
     */
    void esc();

    /**
     * Moves the MeatBoy entity in the specified direction.
     * This method is called when the corresponding key for the specified direction is pressed.
     *
     * @param keyCode The key code indicating the direction of movement.
     */
    void moveMeatBoy(int keyCode);

    /**
     * Stops the movement of the MeatBoy entity in the specified direction.
     * This method is called when the corresponding key for the specified direction is released.
     *
     * @param keyCode The key code indicating the direction of movement to stop.
     */
    void stopMovingMeatBoy(int keyCode);
}
