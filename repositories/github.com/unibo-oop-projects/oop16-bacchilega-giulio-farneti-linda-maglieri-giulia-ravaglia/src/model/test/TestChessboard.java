package model.test;



import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import model.utilities.pawns.PawnTypes;
import model.chessboard.Chessboard;
import model.utilities.pawns.PawnGenerator;
import utilities.Pair;
import utilities.Players;

/**
 * 
 * @author : Giulio Bacchilega
 *
 */
public class TestChessboard {
    private static final Chessboard C = Chessboard.getLog();
    private  PawnGenerator factory = new PawnGenerator();
    private static final int X_9 = 9;
    private static final int X_7 = 7;
    private static final int X_6 = 6;
    private static final int X_5 = 5;

    /**
     * Test Chessboard class.
     */
    @Test
    public void test() {
        assertFalse(C.isEmpty(new Pair<>(0, 0)));
        assertFalse(C.isEmpty(new Pair<>(0, 1)));
        assertFalse(C.isEmpty(new Pair<>(1, 0)));
        assertFalse(C.isEmpty(new Pair<>(X_7, X_7)));
        assertFalse(C.isEmpty(new Pair<>(X_6, X_6)));
        assertFalse(C.isEmpty(new Pair<>(0, X_7)));
        assertFalse(C.isEmpty(new Pair<>(1, 1)));

        assertTrue(C.isEmpty(new Pair<>(3, 3)));
        assertTrue(C.isEmpty(new Pair<>(3, 4)));
        assertTrue(C.isEmpty(new Pair<>(X_5, X_5)));
        assertTrue(C.isEmpty(new Pair<>(X_6, X_5)));
        assertTrue(C.isEmpty(new Pair<>(2, 2)));
        assertTrue(C.isEmpty(new Pair<>(4, 2)));

        assertEquals(C.getLimit(Players.playerWhite), new Integer(X_7));
        assertEquals(C.getLimit(Players.playerBlack), new Integer(0));

        assertEquals(C.getMapOfPlayers().keySet().size(), 2);
        assertTrue(C.getMapOfPlayers().keySet().containsAll(Arrays.asList(Players.playerBlack, Players.playerWhite)));

        assertTrue(C.getPawnByCoordinate(new Pair<>(4, 0)).compare(PawnTypes.King));
        assertTrue(C.getPawnByCoordinate(new Pair<>(3, 0)).compare(PawnTypes.Queen));

        assertFalse(C.isIncluded(new Pair<>(8, 0)));
        assertFalse(C.isIncluded(new Pair<>(X_9, X_9)));
        assertFalse(C.isIncluded(new Pair<>(8, 8)));
        assertFalse(C.isIncluded(new Pair<>(-1, 1)));


        assertEquals(C.getKingByPlayer(Players.playerBlack).getActualPosition(), new Pair<>(4, X_7));
        assertFalse(C.getKingByPlayer(Players.playerWhite).isMoved());

        assertTrue(C.isEmpty(new Pair<>(X_5, X_5)));
        C.putPawn(factory.build(PawnTypes.Queen, Players.playerWhite, new Pair<>(X_5, X_5)), new Pair<>(X_5, X_5), Players.playerWhite);
        assertFalse(C.isEmpty(new Pair<>(X_5, X_5)));
        C.removePawn(new Pair<>(X_5, X_5), Players.playerWhite);
        assertTrue(C.isEmpty(new Pair<>(X_5, X_5)));


    }

}
