package model.test;


import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

import model.chessboard.Chessboard;
import model.logic.GameLogic;
import model.utilities.pawns.King;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnTypes;
import model.utilities.pawns.SimplePawn;
import utilities.Pair;
import utilities.Players;

/**
 * 
 * @author : Giulio Bacchilega
 *
 */
public class TestPawns {

    /**
     * 
     */
    @Test
    public void testMovement() {
        Pawn king = new King(PawnTypes.King, Players.playerBlack, new Pair<Integer, Integer>(0, 0));
        assertTrue(king.getPossibleMoves().containsAll(Arrays.asList(new Pair<Integer, Integer>(1, 0), 
                                                            new Pair<Integer, Integer>(1, 1),
                                                            new Pair<Integer, Integer>(0, 1))));
        king.setNewPosition(new Pair<Integer, Integer>(1, 1));
        assertTrue(king.isMoved());

        Pawn sp = new SimplePawn(PawnTypes.SimplePawn, Players.playerWhite, new Pair<>(0, 1));
        sp.setAfterCreation();
        assertTrue(sp.getPossibleMoves().containsAll(Arrays.asList(new Pair<>(0, 2), 
                                                                   new Pair<>(0, 3))));

        SimplePawn sp2 = new SimplePawn(PawnTypes.SimplePawn, Players.playerBlack, new Pair<>(0, 0));
        sp2.setAfterCreation();
        assertTrue(sp2.checkChange());

        Chessboard.getLog().putPawn(sp2, new Pair<>(0, 0), Players.playerWhite);
        assertTrue(Chessboard.getLog().getPawnByCoordinate(new Pair<>(0, 0)).compare(PawnTypes.SimplePawn));
        GameLogic.getLog().setPawnInLimit(sp2, PawnTypes.Bishop, sp2.getActualPosition());
        assertTrue(Chessboard.getLog().getPawnByCoordinate(new Pair<>(0, 0)).compare(PawnTypes.Bishop));
    }

}
