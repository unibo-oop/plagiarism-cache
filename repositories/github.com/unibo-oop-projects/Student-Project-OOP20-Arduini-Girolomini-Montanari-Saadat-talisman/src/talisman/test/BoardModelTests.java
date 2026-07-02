package talisman.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import talisman.test.util.BoardTestUtils;

import talisman.model.board.TalismanBoard;

/**
 * Tests the talisman board's model.
 * 
 * @author Alberto Arduini
 *
 */
public class BoardModelTests {
    /**
     * Tests the board's capability of moving a pawn.
     */
    @Test
    public void testMovePawn() {
        final TalismanBoard board = BoardTestUtils.createBoard(2, 4, 1);
        // Move to cell of index two
        board.movePawnTo(0, 2);
        Assertions.assertEquals(2, board.getPawnCellIndex(0));
        // Move to cell if index one
        board.movePawnTo(0, 1);
        Assertions.assertEquals(1, board.getPawnCellIndex(0));
        // Move to first cell of section of index 1
        board.changePawnSection(0, 1);
        Assertions.assertEquals(0, board.getPawnCellIndex(0));
        Assertions.assertEquals(1, board.getPawnSectionIndex(0));
        // Move to cell of index 3 of section of index 0
        board.changePawnSection(0, 0, 3);
        Assertions.assertEquals(3, board.getPawnCellIndex(0));
        Assertions.assertEquals(0, board.getPawnSectionIndex(0));
    }
}
