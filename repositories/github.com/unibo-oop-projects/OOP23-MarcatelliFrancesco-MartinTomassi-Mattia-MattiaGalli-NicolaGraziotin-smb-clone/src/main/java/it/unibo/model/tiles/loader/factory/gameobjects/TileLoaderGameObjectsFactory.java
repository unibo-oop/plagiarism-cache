package it.unibo.model.tiles.loader.factory.gameobjects;

import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.tiles.loader.gameobjects.TileLoaderGameObjects;
import it.unibo.model.tiles.loader.manager.TileLoaderManager;

/**
 * The TileLoaderGameObjectsFactory interface represents a factory for creating TileLoaderGameObjects objects.
 * Implementations of this interface are responsible for creating instances of TileLoaderGameObjects
 * based on the provided TileLoaderManager and DocumentExtractor.
 */
public interface TileLoaderGameObjectsFactory {

    /**
     * Creates a TileLoaderGameObjects object based on the provided TileLoaderManager and DocumentExtractor.
     *
     * @param tileLoaderManager the TileLoaderManager responsible for loading tile-related resources.
     * @param documentExtractor the DocumentExtractor responsible for extracting information from documents.
     * @return a TileLoaderGameObjects object created with the provided TileLoaderManager and DocumentExtractor.
     */
    TileLoaderGameObjects createTileLoaderGameObjects(TileLoaderManager tileLoaderManager, DocumentExtractor documentExtractor);

}
