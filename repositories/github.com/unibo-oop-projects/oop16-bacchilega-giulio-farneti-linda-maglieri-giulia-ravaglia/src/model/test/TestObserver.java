package model.test;



import static org.junit.Assert.assertFalse;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import model.chessboard.Chessboard;
import model.logic.GameLogic;
import model.utilities.pawns.King;
import utilities.Pair;
import utilities.Players;

/**
 * 
 * @author : Giulio Bacchilega.
 *
 */
public class TestObserver {

    private static final int X_4 = 4;
    private static final int X_5 = 5;
    private static final int X_7 = 7;
    /**
     * Test KingObserver class.
     */
    @Test
    public void test() {
        GameLogic.getLog().setKingObservers();
        final King king = (King) Chessboard.getLog().getKingByPlayer(Players.playerWhite);

        assertFalse(GameLogic.getLog().isOppositePlayerUnderCheck());
        GameLogic.getLog().setNextMove(king, new Pair<>(X_5, X_5));
        GameLogic.getLog().changePlayer();
        GameLogic.getLog().setNextMove(Chessboard.getLog().getPawnByCoordinate(new Pair<>(3, X_7)), new Pair<>(X_4, X_4));
        assertTrue(GameLogic.getLog().isOppositePlayerUnderCheck());
    }

}
