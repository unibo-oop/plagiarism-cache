package it.unibo.plantsfarm.model;

import org.junit.jupiter.api.Test;

import it.unibo.plantsfarm.model.tiles.TileMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class LogicMapTest {

    private static final int SOIL_QUANTITY = 64;
    private static final int SOLID_BLOCK_QUANTITY = 384;

    private static final int NORMAL_SOIL_COLUMN = 21;
    private static final int NORMAL_SOIL_ROW = 4;
    private static final int NORMAL_SOIL_ID = 11;

    private static final int ORNAMENTAL_SOIL_COLUMN = 23;
    private static final int ORNAMENTAL_SOIL_ROW = 6;
    private static final int ORNAMENTAL_SOIL_ID = 2;

    private static final String FILEPATH = "/maps/map.txt";

    @Test
    void testMapLoading() {
        final TileMap map = new TileMap();
        map.loadMap(FILEPATH);

        assertEquals(map.getSoilList().size(), SOIL_QUANTITY);
        assertEquals(map.getSolidBlocks().size(), SOLID_BLOCK_QUANTITY);
    }

    @Test
    void testSolidBlocks() {
        final TileMap map = new TileMap();
        map.loadMap(FILEPATH);

        assertEquals(map.getTileId(0, 0), 4);
        assertTrue(map.isSolid(map.getTileId(0, 0)));
        assertEquals(map.getTileId(10, 10), 0);
        assertFalse(map.isSolid(map.getTileId(10, 10)));
    }

    @Test
    void testSoils() {
        final TileMap map = new TileMap();
        map.loadMap(FILEPATH);

        assertFalse(map.isSoil(map.getTileId(0, 0)));
        assertEquals(map.getTileId(NORMAL_SOIL_ROW, NORMAL_SOIL_COLUMN), NORMAL_SOIL_ID);
        assertTrue(map.isSoil(map.getTileId(NORMAL_SOIL_ROW, NORMAL_SOIL_COLUMN)));
        assertEquals(map.getTileId(ORNAMENTAL_SOIL_ROW, ORNAMENTAL_SOIL_COLUMN), ORNAMENTAL_SOIL_ID);
        assertTrue(map.isSoil(map.getTileId(ORNAMENTAL_SOIL_ROW, ORNAMENTAL_SOIL_COLUMN)));
    }
}
