package it.unibo.modularcheckers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.unibo.modularcheckers.checkers.model.piece.Man;
import it.unibo.modularcheckers.model.Block;
import it.unibo.modularcheckers.model.BlockImpl;
import it.unibo.modularcheckers.model.Chessboard;
import it.unibo.modularcheckers.model.ChessboardImpl;
import it.unibo.modularcheckers.model.Color;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.Pair;

/**
 * Test class for chessboard.
 * CHECKSTYLE: MagicNumber OFF
 */
public class ChessboardTest {

    private static Chessboard initChessboard(final int x, final int y) {
        return new ChessboardImpl(x, y);
    }

    /**
     * test the size and the initialization of the chessboard and related blocks.
     */
    @Test
    public void testBasic() {
        final Chessboard chessboard = initChessboard(10, 10);
        assertEquals("chessboard size must be the size passed in constructor", chessboard.getSize(), new Pair<Integer, Integer>(10, 10));
        assertEquals("map size must be x*y", chessboard.getBlocks().size(), 10 * 10);
        assertNotNull("blocks must be initialized", chessboard.getBlock(new Coordinate(1, 1)));
        final Block firstBlock = chessboard.getBlock(new Coordinate(0, 0));
        assertNotNull("Coordinates must start with 0", firstBlock);
        assertFalse("blocks must be empty", firstBlock.pieceExists());
    }

    /**
     * test the unmodifiable map returned by getBlocks methods.
     */
    @Test
    public void testUnmodifiableMap() {
        final Chessboard chessboard = initChessboard(8, 8);
        try {
            chessboard.getBlocks().put(new Coordinate(1, 2), new BlockImpl());
            fail("getBlocks must return an Unmodifiable Map");
        } catch (UnsupportedOperationException e) {
            System.out.println("Exception thrown correctly: " + e.toString());
        }
    }

    /**
     * test the reset method.
     */
    @Test
    public void testReset() {
        final Chessboard chessboard = initChessboard(8, 8);
        final Coordinate c = new Coordinate(1, 1);
        chessboard.getBlock(c).setPiece(new Man(Color.WHITE));
        chessboard.reset();
        assertFalse("reset must remove all pieces from the blocks!", chessboard.getBlock(c).pieceExists());
    }

}
