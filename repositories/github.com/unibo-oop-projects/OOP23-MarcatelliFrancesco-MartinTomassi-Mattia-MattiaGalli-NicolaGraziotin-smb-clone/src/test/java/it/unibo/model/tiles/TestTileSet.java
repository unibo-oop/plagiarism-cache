package it.unibo.model.tiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.commons.Constants;
import it.unibo.model.tiles.tileset.TileSet;
import it.unibo.model.tiles.tileset.TileSetImpl;
import it.unibo.model.tiles.tileset.factory.TileSetFactoryImpl;

/**
 * JUnit tests for the TileSet class.
 */
class TestTileSet {

    private static final int NUM_TILES = 902;
    private TileSet tileSet;

    /**
     * Initializes the test environment before each test method is executed.
     */
    @BeforeEach
    void init() {
        this.tileSet = new TileSetFactoryImpl()
            .createTileSet(Constants.SOURCE_MAP);
        assertNotNull(this.tileSet);
    }

    /**
     * Tests the {@link TileSetImpl#read()} method.
     */
    @Test
    void testRead() {
        final List<Tile> tiles = this.tileSet.read();
        assertNotNull(tiles);
        assertFalse(tiles.isEmpty());
        assertEquals(NUM_TILES, tiles.size());
    }

}

