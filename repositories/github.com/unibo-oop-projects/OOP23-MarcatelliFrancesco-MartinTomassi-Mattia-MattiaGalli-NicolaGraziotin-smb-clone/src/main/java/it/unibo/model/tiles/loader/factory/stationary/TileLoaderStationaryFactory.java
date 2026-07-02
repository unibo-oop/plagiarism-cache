package it.unibo.model.tiles.loader.factory.stationary;

import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.tiles.loader.manager.TileLoaderManager;
import it.unibo.model.tiles.loader.stationary.TileLoaderStationary;

/**
 * The TileLoaderStationaryFactory interface represents a factory for creating TileLoaderStationary objects.
 * Implementations of this interface are responsible for creating instances of TileLoaderStationary
 * based on the provided TileLoaderManager and DocumentExtractor.
 */
public interface TileLoaderStationaryFactory {

    /**
     * Creates a TileLoaderStationary object based on the provided TileLoaderManager and DocumentExtractor.
     *
     * @param tileLoaderManager the TileLoaderManager responsible for loading tile-related resources.
     * @param documentExtractor the DocumentExtractor responsible for extracting information from documents.
     * @return a TileLoaderStationary object created with the provided TileLoaderManager and DocumentExtractor.
     */
    TileLoaderStationary createTileLoaderStationary(TileLoaderManager tileLoaderManager, DocumentExtractor documentExtractor);

}
