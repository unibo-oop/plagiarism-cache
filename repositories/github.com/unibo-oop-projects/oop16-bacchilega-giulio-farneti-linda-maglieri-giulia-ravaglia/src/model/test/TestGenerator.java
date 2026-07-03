package model.test;



import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnGenerator;
import model.utilities.pawns.PawnTypes;
import utilities.Pair;
import utilities.Players;

/**
 * 
 * @author : Giulio Bacchilega
 *
 */
public class TestGenerator {

    private static final int X_3 = 3;
    private static final int X_4 = 4;
    /**
     * Test PawnFactory class.
     */
    @Test
    public void test() {
        PawnGenerator factory = new PawnGenerator();
        Pawn pawn = factory.build(PawnTypes.Queen, Players.playerWhite, new Pair<Integer, Integer>(X_4, X_4));
        assertTrue(pawn.compare(Players.playerWhite));
        assertTrue(pawn.compare(PawnTypes.Queen));
        assertTrue(pawn.compare(new Pair<Integer, Integer>(X_4, X_4)));

        Pawn rook = factory.build(PawnTypes.Rook, Players.playerBlack, new Pair<Integer, Integer>(X_3, X_3));
        assertTrue(rook.compare(Players.playerBlack));
        assertFalse(rook.compare(PawnTypes.Queen));
        assertFalse(rook.compare(new Pair<Integer, Integer>(X_4, X_4)));

        Pawn bishop = factory.build(PawnTypes.Bishop, Players.playerWhite, new Pair<Integer, Integer>(1, 1));
        assertTrue(bishop.compare(Players.playerWhite));
        assertTrue(bishop.compare(PawnTypes.Bishop));
        assertEquals(bishop.getActualPosition(), new Pair<>(1, 1));
    }

}
