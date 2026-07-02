package it.unibo.model.tiles.loader.factory.manager;

import it.unibo.model.tiles.loader.manager.TileLoaderManager;
import it.unibo.model.tiles.manager.TileManager;

/**
 * The TileLoaderManagerFactory interface represents a factory for creating TileLoaderManager objects.
 * Implementations of this interface are responsible for creating instances of TileLoaderManager
 * based on the provided TileManager and TMX (Tile Map XML).
 */
public interface TileLoaderManagerFactory {

    /**
     * Creates a TileLoaderManager object based on the provided TileManager and TMX (Tile Map XML).
     *
     * @param tileManager the TileManager responsible for managing tiles.
     * @param tmx the Tile Map XML data representing the tile map.
     * @return a TileLoaderManager object created with the provided TileManager and TMX data.
     */
    TileLoaderManager createTileLoaderManager(TileManager tileManager, String tmx);

}
