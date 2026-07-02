package it.unibo.model.tiles.tileset.factory;

import it.unibo.model.tiles.tileset.TileSet;
import it.unibo.model.tiles.tileset.TileSetImpl;

/**
 * The TileSetFactoryImpl class is an implementation of the TileSetFactory interface.
 * It provides functionality to create TileSet objects based on the provided TMX (Tile Map XML).
 */
public class TileSetFactoryImpl implements TileSetFactory {

    @Override
    public final TileSet createTileSet(final String tmx) {
        return new TileSetImpl(tmx);
    }

}
