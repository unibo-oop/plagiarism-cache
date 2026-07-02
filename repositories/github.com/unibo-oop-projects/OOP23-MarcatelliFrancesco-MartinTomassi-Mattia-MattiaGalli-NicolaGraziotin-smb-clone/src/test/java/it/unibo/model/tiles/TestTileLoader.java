package it.unibo.model.tiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.commons.Constants;
import it.unibo.model.documentextractor.DocumentExtractor;
import it.unibo.model.documentextractor.factory.DocumentExtractorFactoryImpl;
import it.unibo.model.tiles.loader.factory.gameobjects.TileLoaderGameObjectsFactoryImpl;
import it.unibo.model.tiles.loader.factory.manager.TileLoaderManagerFactoryImpl;
import it.unibo.model.tiles.loader.factory.stationary.TileLoaderStationaryFactoryImpl;
import it.unibo.model.tiles.loader.gameobjects.TileLoaderGameObjects;
import it.unibo.model.tiles.loader.manager.TileLoaderManager;
import it.unibo.model.tiles.loader.stationary.TileLoaderStationary;
import it.unibo.model.tiles.manager.TileManager;
import it.unibo.model.tiles.manager.factory.TileManagerFactoryImpl;

/**
 * JUnit tests for the TileLoader class.
 */
class TestTileLoader {

    private static final int NUM_ROWS = 30;
    private static final int NUM_COLUMNS = 36;
    private TileManager tileManager;
    private TileLoaderManager tileLoaderManager;

    /**
     * Initializes the test environment before each test method is executed.
     */
    @BeforeEach
    void init() {
        this.tileManager = new TileManagerFactoryImpl()
            .createTileManager(Constants.SOURCE_MAP);
        assertNotNull(this.tileManager);
        this.tileLoaderManager = new TileLoaderManagerFactoryImpl()
            .createTileLoaderManager(this.tileManager, Constants.SOURCE_MAP);
        assertNotNull(this.tileLoaderManager);
        final DocumentExtractor documentExtractor = new DocumentExtractorFactoryImpl()
            .createDocumentExtractor(Constants.SOURCE_MAP);
        assertNotNull(documentExtractor);
        final TileLoaderGameObjects tileLoaderGameObjects = new TileLoaderGameObjectsFactoryImpl()
            .createTileLoaderGameObjects(this.tileLoaderManager, documentExtractor);
        assertNotNull(tileLoaderGameObjects);
        final TileLoaderStationary tileLoaderStationary = new TileLoaderStationaryFactoryImpl()
            .createTileLoaderStationary(this.tileLoaderManager, documentExtractor);
        assertNotNull(tileLoaderStationary);
    }

    /**
     * Tests the {@link TileLoaderManager#load()} method.
     */
    @Test
    void testLoad() {
        this.tileLoaderManager.load();
        assertNotNull(this.tileLoaderManager.getStationary());
        assertFalse(this.tileLoaderManager.getStationary().isEmpty());
        assertEquals(NUM_ROWS, this.tileLoaderManager.getStationary().size());
        assertEquals(NUM_COLUMNS, this.tileLoaderManager.getStationary().get(0).size());
        assertNotNull(this.tileManager.getSaws());
        assertFalse(this.tileManager.getSaws().isEmpty());
        assertNotNull(this.tileManager.getPlatforms());
        assertFalse(this.tileManager.getPlatforms().isEmpty());
    }

}
