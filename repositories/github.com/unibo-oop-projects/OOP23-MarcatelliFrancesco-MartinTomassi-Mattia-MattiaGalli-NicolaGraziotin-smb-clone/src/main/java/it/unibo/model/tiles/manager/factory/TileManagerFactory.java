package it.unibo.model.tiles.manager.factory;

import it.unibo.model.tiles.manager.TileManager;

/**
 * The TileManagerFactory interface represents a factory for creating TileManager objects.
 * Implementations of this interface are responsible for creating instances of TileManager
 * based on the provided TMX (Tile Map XML).
 */
public interface TileManagerFactory {

    /**
     * Creates a TileManager object based on the provided TMX (Tile Map XML).
     *
     * @param tmx the Tile Map XML data representing the tile map.
     * @return a TileManager object created with the provided TMX data.
     */
    TileManager createTileManager(String tmx);

}

