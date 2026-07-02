package it.unibo.model.tiles.manager;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Point2D;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.target.BandageGirl;
import it.unibo.model.tiles.Tile;

/**
 * The TileManager interface represents a manager for handling tiles in a game.
 * It provides methods to load and manage a tile map from a specified TMX file.
 */
public interface TileManager {

    /**
     * Returns the List of Platforms parsed from the TMX file.
     *
     * @return The List of Platforms parsed from the TMX file.
     */
    List<Platform> getPlatforms();

    /**
     * Returns the List of CircularSaws parsed from the TMX file.
     *
     * @return The List of CircularSaws parsed from the TMX file.
     */
    List<CircularSaw> getSaws();

    /**
     * Returns the number of columns in the TileMap.
     *
     * @return The number of columns in the TileMap.
     */
    int getNumCols();

    /**
     * Returns the number of rows in the TileMap.
     *
     * @return The number of rows in the TileMap.
     */
    int getNumRows();

    /**
     * Returns the MeatBoy object parsed from the TMX file.
     *
     * @return The MeatBoy object parsed from the TMX file.
     */
    MeatBoy getMeatBoy();

    /**
     * Returns the BandageGirl object parsed from the TMX file.
     *
     * @return The BandageGirl object parsed from the TMX file.
     */
    BandageGirl getBandageGirl();

    /**
     * Retrieves a two-dimensional list representing stationary tiles in the game.
     * Each inner list corresponds to a row of stationary tiles in the game level.
     *
     * @return A two-dimensional list of stationary tiles.
     */
    List<List<Optional<Tile>>> getStationary();

    /**
     * Retrieves a list of all tiles in the game.
     *
     * @return A list containing all tiles in the game.
     */
    List<Tile> getTiles();

    /**
     * Sets a CircularSaw in the TileMap.
     *
     * @param circularSaw The CircularSaw to set.
     */
    void setSaw(CircularSaw circularSaw);

    /**
     * Sets a Platform in the TileMap.
     *
     * @param platform The Platform to set.
     */
    void setPlatform(Platform platform);

    /**
     * Sets the coordinates of MeatBoy in the TileMap.
     *
     * @param coord The coordinates to set for MeatBoy.
     */
    void setMeatBoyCoord(Point2D<Double, Double> coord);

    /**
     * Sets the coordinates of BandageGirl in the TileMap.
     *
     * @param coord The coordinates to set for BandageGirl.
     */
    void setBandageGirlCoord(Point2D<Double, Double> coord);

}
