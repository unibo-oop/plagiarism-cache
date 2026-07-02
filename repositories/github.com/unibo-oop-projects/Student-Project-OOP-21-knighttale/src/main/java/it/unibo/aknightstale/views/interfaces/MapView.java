package it.unibo.aknightstale.views.interfaces;

import it.unibo.aknightstale.controllers.interfaces.MapController;
import it.unibo.aknightstale.views.entity.EntityView;
import it.unibo.aknightstale.views.map.Tile;

import java.util.List;

/**
 * The interface Map view.
 */
public interface MapView extends View<MapController> {

    /**
     * Gets tile width.
     *
     * @return the tile width
     */
    double getTileWidth();

    /**
     * Gets tile height.
     *
     * @return the tile height
     */
    double getTileHeight();

    /**
     * Gets screen width.
     *
     * @return the current width of the game window
     */
    double getScreenWidth();

    /**
     * Gets screen height.
     *
     * @return the current width of the game window
     */
    double getScreenHeight();

    /**
     * Gets floor.
     *
     * @return the tile that represent the floor of the game world.
     */
    Tile getFloor();

    /**
     * Gets tree.
     *
     * @return the tile that represent the tree of the game world.
     */
    Tile getTree();

    /**
     * Gets tiles.
     *
     * @return a list that contains all the tiles used in the map
     */
    List<Tile> getTiles();

    /**
     * Delete all the tiles and entities present in the game world.
     */
    void clearMap();

    /**
     * draw tiles or entities in the game world. @param tile the tile
     *
     * @param x the x
     * @param y the y
     */
    void draw(EntityView tile, double x, double y);

    /**
     * Resize the size of all tiles of the game world.
     *
     * @param tileWidth  the new tile's width
     * @param tileHeight the tile height
     */
    void resizeTiles(double tileWidth, double tileHeight);

    /**
     * Initialize the game world.
     */
    void init();

    /**
     * Stop the game when it's finished.
     */
    void stopGame();
}
