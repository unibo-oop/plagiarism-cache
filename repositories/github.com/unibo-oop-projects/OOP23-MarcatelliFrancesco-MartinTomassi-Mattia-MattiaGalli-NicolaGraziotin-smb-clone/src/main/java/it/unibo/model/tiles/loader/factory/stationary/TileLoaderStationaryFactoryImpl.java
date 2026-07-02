package it.unibo.model.tiles.loader.factory.stationary;

import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.tiles.loader.manager.TileLoaderManager;
import it.unibo.model.tiles.loader.stationary.TileLoaderStationary;
import it.unibo.model.tiles.loader.stationary.TileLoaderStationaryImpl;

/**
 * The TileLoaderStationaryFactoryImpl class is an implementation of the TileLoaderStationaryFactory interface.
 * It provides functionality to create TileLoaderStationary objects based on the provided TileLoaderManager
 * and DocumentExtractor.
 */
public class TileLoaderStationaryFactoryImpl implements TileLoaderStationaryFactory {

    @Override
    public final TileLoaderStationary createTileLoaderStationary(
            final TileLoaderManager tileLoaderManager, final DocumentExtractor documentExtractor) {
        return new TileLoaderStationaryImpl(tileLoaderManager, documentExtractor);
    }

}
