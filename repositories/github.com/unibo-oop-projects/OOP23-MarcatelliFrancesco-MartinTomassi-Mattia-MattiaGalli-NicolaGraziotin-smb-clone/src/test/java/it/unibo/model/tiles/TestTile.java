package it.unibo.model.tiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * JUnit tests for the TileImpl class.
 */
class TestTile {

    private Tile tile;

    /**
     * Initializes the test environment before each test method is executed.
     */
    @BeforeEach
    void init() {
        tile = new TileImpl(1, 2, "image.png");
    }

    /**
     * Tests the {@link TileImpl#getX()} method.
     */
    @Test
    void testGetX() {
        assertEquals(1, tile.getX());
    }

    /**
     * Tests the {@link TileImpl#getY()} method.
     */
    @Test
    void testGetY() {
        assertEquals(2, tile.getY());
    }

    /**
     * Tests the {@link TileImpl#getSrcImage()} method.
     */
    @Test
    void testGetSrcImage() {
        assertEquals("image.png", tile.getSrcImage());
    }

}
