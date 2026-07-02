package it.unibo.model.tiles.loader.factory.manager;

import it.unibo.model.tiles.loader.manager.TileLoaderManager;
import it.unibo.model.tiles.loader.manager.TileLoaderManagerImpl;
import it.unibo.model.tiles.manager.TileManager;

/**
 * The TileLoaderManagerFactoryImpl class is an implementation of the TileLoaderManagerFactory interface.
 * It provides functionality to create TileLoaderManager objects based on the provided TileManager
 * and TMX (Tile Map XML).
 */
public class TileLoaderManagerFactoryImpl implements TileLoaderManagerFactory {

    @Override
    public final TileLoaderManager createTileLoaderManager(final TileManager tileManager, final String tmx) {
        return new TileLoaderManagerImpl(tileManager, tmx);
    }

}
