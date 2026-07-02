package it.unibo.model.tiles.loader.factory.gameobjects;

import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.tiles.loader.gameobjects.TileLoaderGameObjects;
import it.unibo.model.tiles.loader.gameobjects.TileLoaderGameObjectsImpl;
import it.unibo.model.tiles.loader.manager.TileLoaderManager;

/**
 * The TileLoaderGameObjectsFactoryImpl class is an implementation of the TileLoaderGameObjectsFactory interface.
 * It provides functionality to create TileLoaderGameObjects objects based on the provided TileLoaderManager
 * and DocumentExtractor.
 */
public class TileLoaderGameObjectsFactoryImpl implements TileLoaderGameObjectsFactory {

    @Override
    public final TileLoaderGameObjects createTileLoaderGameObjects(
            final TileLoaderManager tileLoaderManager, final DocumentExtractor documentExtractor) {
        return new TileLoaderGameObjectsImpl(tileLoaderManager, documentExtractor);
    }

}
