package it.unibo.model.tiles.loader.manager;

import java.util.List;
import java.util.Optional;

import it.unibo.commons.Point2D;
import it.unibo.model.entity.obstacles.CircularSaw;
import it.unibo.model.entity.obstacles.Platform;
import it.unibo.model.tiles.Tile;

/**
 * The TileLoaderManager interface is responsible for managing the loading 
 * of StationaryTiles and GameObjects from a TMX file.
 */
public interface TileLoaderManager {

    /**
     * Loads stationary tiles, platforms and circular saws from an XML document.
     * This method delegates to specific loading methods based on the type of tiles to be loaded.
     */
    void load();

    /**
     * Trims any extraneous space after a period in the given string.
     *
     * @param s The String to trim
     * @return The trimmed String
     */
    String trim(String s);

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
