package it.unibo.superpeach.gameentities.blocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Rectangle;
import it.unibo.superpeach.game.Game;

/**
 * Test class for Blocks.
 * 
 * @author Eraldo Tabaku
 */
final class BlocksTest {

    private static final int X = 1;
    private static final int Y = 1;
    private static final int WIDTH = 16;
    private static final int HEIGHT = 16;
    private static final int SCALE1 = 1;
    private static final int SCALE2 = 2;

    private final Game game = new Game();

    private MapFixedBlock terrainBlock;

    /**
     * game intialization and new terrain block creation before every test.
     */
    @BeforeEach
    public void initGameTest() {
        game.init();
    }

    @Test
    void testCreation() {

        terrainBlock = new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE2, BlockType.TERRAIN);
        assertNotNull(terrainBlock);

        assertNotEquals(X * SCALE1, terrainBlock.getX());
        assertEquals(X * SCALE2, terrainBlock.getX());
        assertEquals(Y * SCALE2, terrainBlock.getY());
        assertNotEquals(WIDTH, terrainBlock.getWidth());
        assertEquals(HEIGHT * SCALE2, terrainBlock.getHeight());
        assertEquals(SCALE2, terrainBlock.getScale());
        assertNotEquals(SCALE1, terrainBlock.getScale());
        assertNotEquals(BlockType.BRICK, terrainBlock.getType());
        assertEquals(BlockType.TERRAIN, terrainBlock.getType());

    }

    @Test
    void testBoundingBox() {

        terrainBlock = new MapFixedBlock(X, Y, WIDTH, HEIGHT, SCALE2, BlockType.TERRAIN);
        assertNotEquals(new Rectangle(X, Y, WIDTH, HEIGHT), terrainBlock.getBoundingBox());
        assertEquals(new Rectangle(X * SCALE2, Y * SCALE2, WIDTH * SCALE2, HEIGHT * SCALE2),
                terrainBlock.getBoundingBox());

    }

}
