import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.tetris.PlayManager;
import it.unibo.tetris.mino.Mino_L2;
import it.unibo.tetris.mino.api.Block;
import it.unibo.tetris.mino.api.Mino;

/**
 * JUnitTest for the {@link Mino_L2} class.
 */
public class Mino_L2_Test {
    private Mino_L2 mino;

    /**
     * BeforeEach test create a new {@link Mino} and a {@link PlayManager}.
     */
    @BeforeEach
    public void setUp() {
        mino = new Mino_L2();
        PlayManager playManager = new PlayManager();
    }

    /**
     * Test starting positionf of the {@link Mino}.
     */
    @Test 
    public void testSetXY() {
        /*
         * Set initial position.
         */
        mino.setXY(PlayManager.MINO_START_X, PlayManager.MINO_START_Y);

        /*
         * Verify the position of all blocks.
         */
        assertEquals(mino.b[0].x, PlayManager.MINO_START_X);
        assertEquals(mino.b[0].y, PlayManager.MINO_START_Y);
        assertEquals(mino.b[1].x, mino.b[0].x);
        assertEquals(mino.b[1].y, mino.b[0].y - Block.SIZE);
        assertEquals(mino.b[2].x, mino.b[0].x);
        assertEquals(mino.b[2].y, mino.b[0].y + Block.SIZE);
        assertEquals(mino.b[3].x, mino.b[0].x - Block.SIZE);
        assertEquals(mino.b[3].y, mino.b[0].y + Block.SIZE);
    }

    /**
     * Test when {@link Mino} go in direction 1.
     */
    @Test
    public void testDirection1() {
        /*
         * Set initial position.
         */
        mino.setXY(PlayManager.MINO_START_X, PlayManager.MINO_START_Y);
        
        /*
         *  Call getNExtDirection with starting direction 1.
         */ 
        mino.getNextDirection(1);

        /*
         * Verify the position of all blocks.
         */
        assertEquals(mino.b[0].x, PlayManager.MINO_START_X);
        assertEquals(mino.b[0].y, PlayManager.MINO_START_Y);
        assertEquals(mino.b[1].x, mino.b[0].x + Block.SIZE);
        assertEquals(mino.b[1].y, mino.b[0].y);
        assertEquals(mino.b[2].x, mino.b[0].x - Block.SIZE);
        assertEquals(mino.b[2].y, mino.b[0].y);
        assertEquals(mino.b[3].x, mino.b[0].x - Block.SIZE);
        assertEquals(mino.b[3].y, mino.b[0].y - Block.SIZE);
    }

    /**
     * Test when {@link Mino} go in direction 2.
     */
    @Test
    public void testDirection2() {
        /*
         * Set initial position.
         */
        mino.setXY(PlayManager.MINO_START_X, PlayManager.MINO_START_Y);
        
        /*
         * Call getNExtDirection with starting direction 2.
         */ 
        mino.getNextDirection(2);

        /*
         * Verify the position of all blocks.
         */
        assertEquals(mino.b[0].x, PlayManager.MINO_START_X);
        assertEquals(mino.b[0].y, PlayManager.MINO_START_Y);
        assertEquals(mino.b[1].x, mino.b[0].x);
        assertEquals(mino.b[1].y, mino.b[0].y + Block.SIZE);
        assertEquals(mino.b[2].x, mino.b[0].x);
        assertEquals(mino.b[2].y, mino.b[0].y - Block.SIZE);
        assertEquals(mino.b[3].x, mino.b[0].x + Block.SIZE);
        assertEquals(mino.b[3].y, mino.b[0].y - Block.SIZE);
    }

    /**
     * Test when {@link Mino} go in direction 3.
     */
    @Test
    public void testDirection3() {
        /*
         * Set initial position.
         */
        mino.setXY(PlayManager.MINO_START_X, PlayManager.MINO_START_Y);
        
        /*
         * Call getNExtDirection with starting direction 3.
         */ 
        mino.getNextDirection(3);

        /*
         * Verify the position of all blocks.
         */
        assertEquals(mino.b[0].x, PlayManager.MINO_START_X);
        assertEquals(mino.b[0].y, PlayManager.MINO_START_Y);
        assertEquals(mino.b[1].x, mino.b[0].x - Block.SIZE);
        assertEquals(mino.b[1].y, mino.b[0].y);
        assertEquals(mino.b[2].x, mino.b[0].x + Block.SIZE);
        assertEquals(mino.b[2].y, mino.b[0].y);
        assertEquals(mino.b[3].x, mino.b[0].x + Block.SIZE);
        assertEquals(mino.b[3].y, mino.b[0].y + Block.SIZE);
    }

    /**
     * Test when {@link Mino} go in direction 4.
     */
    @Test
    public void testDirection4() {
        /*
         * Set initial position.
         */
        mino.setXY(PlayManager.MINO_START_X, PlayManager.MINO_START_Y);
        
        /*
         * Call getNExtDirection with starting direction 4.
         */
        mino.getNextDirection(4);

        /*
         * Verify the position of all blocks.
         */
        assertEquals(mino.b[0].x, PlayManager.MINO_START_X);
        assertEquals(mino.b[0].y, PlayManager.MINO_START_Y);
        assertEquals(mino.b[1].x, mino.b[0].x);
        assertEquals(mino.b[1].y, mino.b[0].y - Block.SIZE);
        assertEquals(mino.b[2].x, mino.b[0].x);
        assertEquals(mino.b[2].y, mino.b[0].y + Block.SIZE);
        assertEquals(mino.b[3].x, mino.b[0].x - Block.SIZE);
        assertEquals(mino.b[3].y, mino.b[0].y + Block.SIZE);
    }
}
