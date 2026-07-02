package it.unibo.model.tiles.tileset.factory;

import it.unibo.model.tiles.tileset.TileSet;

/**
 * The TileSetFactory interface represents a factory for creating TileSet objects.
 * Implementations of this interface are responsible for creating instances of TileSet
 * based on the provided TMX (Tile Map XML).
 */
public interface TileSetFactory {

    /**
     * Creates a TileSet object based on the provided TMX (Tile Map XML).
     *
     * @param tmx the Tile Map XML data representing the tile map.
     * @return a TileSet object created with the provided TMX data.
     */
    TileSet createTileSet(String tmx);

}
