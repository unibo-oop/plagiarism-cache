package it.unibo.model;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Point2D;
import it.unibo.model.collision.CollisionHandler;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.tiles.Tile;

/**
 * The GameModel interface represents the ModelManager of the Game SuperMeatBoy, providing methods to retrieve
 * information about the tiles in the game grid.
 * The game grid consists of background, saws, platforms, meatboy, bandagegirl
 * and stationary tiles, organized in a two-dimensional structure
 * where each inner list corresponds to a row, and the elements
 * within the inner lists represent tiles in that specific row.
 */
public interface GameModel {

     /**
     * Retrieves the stationary tiles represented as a two-dimensional list.
     *
     * The stationary tiles are organized in a grid structure, where each inner list
     * corresponds to a row, and the elements within the inner lists represent tiles
     * in that specific row.
     *
     * @return A two-dimensional list containing stationary tiles.
     */
    List<List<Optional<Tile>>> getStationary();

    /**
     * Returns the List of CircularSaws parsed from the TMX file.
     *
     * @return The List of CircularSaws parsed from the TMX file.
     */
    List<CircularSaw> getSaws();

    /**
     * Returns the List of Platforms parsed from the TMX file.
     *
     * @return The List of Platforms parsed from the TMX file.
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
     * Retrieves the Starting Coordinates of MeatBoy.
     *
     * @return The Starting Coordinates of MeatBoy.
     */
    Point2D<Double, Double> getMeatBoyStartCoord();

    /**
     * Retrieves the total number of rows in the grid of tiles.
     *
     * @return The number of rows in the grid.
     */
    int getNumRows();

    /**
     * Retrieves the total number of columns in the grid of tiles.
     *
     * @return The number of columns in the grid.
     */
    int getNumCols();

    /**
     * Retrieves the collision handler associated with the game model.
     *
     * @return The collision handler.
     */
    CollisionHandler getCollisionHandler();

    /**
     * Signals a victory event.
     * This method is called to indicate that the game has been won.
     */
    void victory();

    /**
     * Initializes the coordinates.
     * This method is responsible for setting up initial coordinates or resetting them to their default values.
     */
    void initializeCoords();

    /**
     * This method is called to indicate that the player collides with an obstacles or fall from the map and died.
     */
    void died();
}
