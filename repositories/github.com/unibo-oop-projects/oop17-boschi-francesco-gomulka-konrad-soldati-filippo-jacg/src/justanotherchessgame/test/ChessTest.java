package justanotherchessgame.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import justanotherchessgame.model.ChessboardModel;
import justanotherchessgame.model.ChessboardModelImpl;
import justanotherchessgame.model.MoveInfoImpl;
import justanotherchessgame.model.MovesChecker;
import justanotherchessgame.util.Point;

/**
 * Class used to test the application.
 */
public class ChessTest {
    private ChessboardModel chessboard;
    /**
     * Function used to initialize the chessboard.
     */
    @Before
    public void startTest() {
        chessboard = new ChessboardModelImpl(true);
    }
    /**
     * Function used to test the initial positions and available moves for each piece.
     */
    @Test
    public void initialStateCheck() {
        //CHECK FOR WHITE PIECES

        //left white rook position and moves
        assertEquals("Position 0-0 is white rook",  chessboard.getSquare(new Point(0,  0)).toString(),  "Rook white");
        assertTrue("No moves are available for rook",  MovesChecker.possibleMoves(chessboard, new Point(0, 0)).size() == 0);
        //left white knight position and moves
        assertEquals("Position 1-0 is white knight",  chessboard.getSquare(new Point(1,  0)).toString(),  "Knight white");
        assertTrue("Two moves are available for knight",  MovesChecker.possibleMoves(chessboard, new Point(1, 0)).size() == 2);
        //left white bishop position and moves
        assertEquals("Position 2-0 is white bishop",  chessboard.getSquare(new Point(2,  0)).toString(),  "Bishop white");
        assertTrue("No moves are available for bishop",  MovesChecker.possibleMoves(chessboard, new Point(2, 0)).size() == 0);
        //white queen position and moves
        assertEquals("Position 3-0 is white queen",  chessboard.getSquare(new Point(3,  0)).toString(),  "Queen white");
        assertTrue("No moves are available for queen",  MovesChecker.possibleMoves(chessboard, new Point(3, 0)).size() == 0);
        //white king position and moves
        assertEquals("Position 4-0 is white king",  chessboard.getSquare(new Point(4,  0)).toString(),  "King white");
        assertTrue("No moves are available for king",  MovesChecker.possibleMoves(chessboard, new Point(4, 0)).size() == 0);
        //right white bishop position and moves
        assertEquals("Position 5-0 is white bishop",  chessboard.getSquare(new Point(5,  0)).toString(),  "Bishop white");
        assertTrue("No moves are available for bishop",  MovesChecker.possibleMoves(chessboard, new Point(5, 0)).size() == 0);
        //right white knight position and moves
        assertEquals("Position 6-0 is white knight",  chessboard.getSquare(new Point(6,  0)).toString(),  "Knight white");
        assertTrue("Two moves are available for knight",  MovesChecker.possibleMoves(chessboard, new Point(6, 0)).size() == 2);
        //right white rook position and moves
        assertEquals("Position 7-0 is white rook",  chessboard.getSquare(new Point(7,  0)).toString(),  "Rook white");
        assertTrue("No moves are available for rook",  MovesChecker.possibleMoves(chessboard, new Point(7, 0)).size() == 0);
        //white pawns position and moves
        for (int i = 0; i < 8; i++) {
            assertEquals("Position " + i + "-1 is white pawn",  chessboard.getSquare(new Point(i,  1)).toString(),  "Pawn white");
            assertTrue("Two moves are available for pawn",  MovesChecker.possibleMoves(chessboard, new Point(i,  1)).size() == 2);
        }
        //CHECK FOR BLACK PIECES
        //left white rook position and moves
        assertEquals("Position 0-7 is black rook",  chessboard.getSquare(new Point(0,  7)).toString(),  "Rook black");
        assertTrue("No moves are available for rook",  MovesChecker.possibleMoves(chessboard, new Point(0,  7)).size() == 0);
        //left white knight position and moves
        assertEquals("Position 1-7 is black knight",  chessboard.getSquare(new Point(1, 7)).toString(),  "Knight black");
        assertTrue("Two moves are available for knight",  MovesChecker.possibleMoves(chessboard, new Point(1,  7)).size() == 2);
        //left white bishop position and moves
        assertEquals("Position 2-7 is black bishop",  chessboard.getSquare(new Point(2, 7)).toString(),  "Bishop black");
        assertTrue("No moves are available for bishop",  MovesChecker.possibleMoves(chessboard, new Point(2,  7)).size() == 0);
        //white queen position and moves
        assertEquals("Position 3-7 is black queen",  chessboard.getSquare(new Point(3, 7)).toString(),  "Queen black");
        assertTrue("No moves are available for queen",  MovesChecker.possibleMoves(chessboard, new Point(3,  7)).size() == 0);
        //white king position and moves
        assertEquals("Position 4-7 is black king",  chessboard.getSquare(new Point(4, 7)).toString(),  "King black");
        assertTrue("No moves are available for king",  MovesChecker.possibleMoves(chessboard, new Point(4,  7)).size() == 0);
        //right white bishop position and moves
        assertEquals("Position 5-7 is black bishop",  chessboard.getSquare(new Point(5, 7)).toString(),  "Bishop black");
        assertTrue("No moves are available for bishop",  MovesChecker.possibleMoves(chessboard, new Point(5,  7)).size() == 0);
        //right white knight position and moves
        assertEquals("Position 6-7 is black knight",  chessboard.getSquare(new Point(6, 7)).toString(),  "Knight black");
        assertTrue("Two moves are available for knight",  MovesChecker.possibleMoves(chessboard, new Point(6,  7)).size() == 2);
        //right white rook position and moves
        assertEquals("Position 7-7 is black rook",  chessboard.getSquare(new Point(7, 7)).toString(),  "Rook black");
        assertTrue("No moves are available for rook",  MovesChecker.possibleMoves(chessboard, new Point(7,  7)).size() == 0);
        //white pawns position and moves
        for (int i = 0; i < 8; i++) {
            assertEquals("Position " + i + "-6 is black rook",  chessboard.getSquare(new Point(i, 6)).toString(),  "Pawn black");
            assertTrue("Two moves are available for pawn",  MovesChecker.possibleMoves(chessboard, new Point(i,  6)).size() == 2);
        }
    }
    /**
     * Function used to check the state of a chessboard created by loading a list of moves.
     */
    @Test
    public void loadedChessboardStateCheck() { 
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(0,  1),  new Point(0, 3)));
        moves.add(new MoveInfoImpl(new Point(3,  6),  new Point(3, 4)));
        moves.add(new MoveInfoImpl(new Point(1,  0),  new Point(2, 2)));
        moves.add(new MoveInfoImpl(new Point(1,  7),  new Point(0, 5)));
        moves.add(new MoveInfoImpl(new Point(3,  1),  new Point(3, 2)));
        moves.add(new MoveInfoImpl(new Point(2,  7),  new Point(7, 2)));
        moves.add(new MoveInfoImpl(new Point(6,  1),  new Point(7, 2)));
        moves.add(new MoveInfoImpl(new Point(0,  5),  new Point(1, 7)));
        moves.add(new MoveInfoImpl(new Point(6,  0),  new Point(5, 2)));
        moves.add(new MoveInfoImpl(new Point(1, 7),  new Point(2, 5)));
        moves.add(new MoveInfoImpl(new Point(5, 2),  new Point(6, 0)));
        moves.add(new MoveInfoImpl(new Point(3, 7),  new Point(3, 5)));
        moves.add(new MoveInfoImpl(new Point(6, 0),  new Point(5, 2)));
        moves.add(new MoveInfoImpl(new Point(6, 7),  new Point(5, 5)));
        moves.add(new MoveInfoImpl(new Point(5, 2),  new Point(6, 0)));
        moves.add(new MoveInfoImpl(new Point(5, 5),  new Point(6, 3)));
        moves.add(new MoveInfoImpl(new Point(7, 2),  new Point(6, 3)));
        moves.add(new MoveInfoImpl(new Point(4, 7),  new Point(3, 7)));
        moves.add(new MoveInfoImpl(new Point(6, 0),  new Point(5, 2)));
        moves.add(new MoveInfoImpl(new Point(3, 7),  new Point(2, 7)));
        moves.add(new MoveInfoImpl(new Point(5, 2),  new Point(6, 0)));
        moves.add(new MoveInfoImpl(new Point(2, 7),  new Point(1, 7)));
        moves.add(new MoveInfoImpl(new Point(2, 2),  new Point(3, 4)));
        moves.add(new MoveInfoImpl(new Point(3, 5),  new Point(3, 4)));
        moves.add(new MoveInfoImpl(new Point(3, 2),  new Point(3, 3)));
        moves.add(new MoveInfoImpl(new Point(3, 4),  new Point(6, 4)));
        moves.add(new MoveInfoImpl(new Point(2, 0),  new Point(6, 4)));
        moves.add(new MoveInfoImpl(new Point(2, 5),  new Point(3, 3)));
        chessboard = new ChessboardModelImpl(moves);
        //CHECK THE POSITION OF ALL PIECES ON BOARD
        assertEquals("Position 0-0 is white rook",  chessboard.getSquare(new Point(0, 0)).toString(),  "Rook white");
        assertEquals("Position 3-0 is white queen",  chessboard.getSquare(new Point(3, 0)).toString(),  "Queen white");
        assertEquals("Position 4-0 is white king",  chessboard.getSquare(new Point(4, 0)).toString(),  "King white");
        assertEquals("Position 5-0 is white bishop",  chessboard.getSquare(new Point(5, 0)).toString(),  "Bishop white");
        assertEquals("Position 6-0 is white knight",  chessboard.getSquare(new Point(6, 0)).toString(),  "Knight white");
        assertEquals("Position 7-0 is white rook",  chessboard.getSquare(new Point(7, 0)).toString(),  "Rook white");
        assertEquals("Position 1-1 is white pawn",  chessboard.getSquare(new Point(1, 1)).toString(),  "Pawn white");
        assertEquals("Position 2-1 is white pawn",  chessboard.getSquare(new Point(2, 1)).toString(),  "Pawn white");
        assertEquals("Position 4-1 is white pawn",  chessboard.getSquare(new Point(4, 1)).toString(),  "Pawn white");
        assertEquals("Position 5-1 is white pawn",  chessboard.getSquare(new Point(5, 1)).toString(),  "Pawn white");
        assertEquals("Position 7-1 is white pawn",  chessboard.getSquare(new Point(7, 1)).toString(),  "Pawn white");
        assertEquals("Position 0-3 is white pawn",  chessboard.getSquare(new Point(0, 3)).toString(),  "Pawn white");
        assertEquals("Position 3-3 is black knight",  chessboard.getSquare(new Point(3, 3)).toString(),  "Knight black");
        assertEquals("Position 6-3 is white pawn",  chessboard.getSquare(new Point(6, 3)).toString(),  "Pawn white");
        assertEquals("Position 6-4 is white bishop",  chessboard.getSquare(new Point(6, 4)).toString(),  "Bishop white");
        assertEquals("Position 0-6 is black pawn",  chessboard.getSquare(new Point(0, 6)).toString(),  "Pawn black");
        assertEquals("Position 1-6 is black pawn",  chessboard.getSquare(new Point(1, 6)).toString(),  "Pawn black");
        assertEquals("Position 2-6 is black pawn",  chessboard.getSquare(new Point(2, 6)).toString(),  "Pawn black");
        assertEquals("Position 4-6 is black pawn",  chessboard.getSquare(new Point(4, 6)).toString(),  "Pawn black");
        assertEquals("Position 5-6 is black pawn",  chessboard.getSquare(new Point(5, 6)).toString(),  "Pawn black");
        assertEquals("Position 6-6 is black pawn",  chessboard.getSquare(new Point(6, 6)).toString(),  "Pawn black");
        assertEquals("Position 7-6 is black pawn",  chessboard.getSquare(new Point(7, 6)).toString(),  "Pawn black");
        assertEquals("Position 0-7 is black rook",  chessboard.getSquare(new Point(0, 7)).toString(),  "Rook black");
        assertEquals("Position 1-7 is black king",  chessboard.getSquare(new Point(1, 7)).toString(),  "King black");
        assertEquals("Position 5-7 is black bishop",  chessboard.getSquare(new Point(5, 7)).toString(),  "Bishop black");
        assertEquals("Position 7-7 is black rook",  chessboard.getSquare(new Point(7, 7)).toString(),  "Rook black");
    }
    /**
     * Function used to check the short Castling.
     */
    @Test
    public void shortcastlingCheck() {
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(6, 0),  new Point(7, 2)));
        moves.add(new MoveInfoImpl(new Point(6, 1),  new Point(6, 2)));
        moves.add(new MoveInfoImpl(new Point(5, 0),  new Point(6, 1)));
        moves.add(new MoveInfoImpl(new Point(4, 0),  new Point(6, 0)));
        chessboard = new ChessboardModelImpl(moves);
        //CHECK THE POSITION OF KING AND ROOK
        assertEquals("Position 6-0 is white king",  chessboard.getSquare(new Point(6, 0)).toString(),  "King white");
        assertEquals("Position 5-0 is white rook",  chessboard.getSquare(new Point(5, 0)).toString(),  "Rook white"); 
    }
    /**
     * Function used to check the long Castling.
     */
    @Test
    public void longcastlingCheck() { 
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(1, 0),  new Point(0, 2)));
        moves.add(new MoveInfoImpl(new Point(1, 1),  new Point(1, 2)));
        moves.add(new MoveInfoImpl(new Point(2, 1),  new Point(2, 2)));
        moves.add(new MoveInfoImpl(new Point(2, 0),  new Point(1, 1)));
        moves.add(new MoveInfoImpl(new Point(3, 0),  new Point(2, 1)));
        moves.add(new MoveInfoImpl(new Point(4, 0),  new Point(2, 0)));
        chessboard = new ChessboardModelImpl(moves);
        //CHECK THE POSITION OF KING AND ROOK
        assertEquals("Position 2-0 is white king",  chessboard.getSquare(new Point(2, 0)).toString(),  "King white");
        assertEquals("Position 3-0 is white rook",  chessboard.getSquare(new Point(3, 0)).toString(),  "Rook white"); 
    }
    /**
     * Function used to check the En Passant.
     */
    @Test
    public void enpassantCheck() { 
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(1, 1),  new Point(1, 3)));
        moves.add(new MoveInfoImpl(new Point(1, 3),  new Point(1, 4)));
        moves.add(new MoveInfoImpl(new Point(2, 6),  new Point(2, 4)));
        moves.add(new MoveInfoImpl(new Point(1, 4),  new Point(2, 5)));
        chessboard = new ChessboardModelImpl(moves);
        //CHECK THE POSITION OF PAWN AND IF THE OTHER PAWN HAS BEEN EATEN
        assertEquals("Position 2-5 is white pawn",  chessboard.getSquare(new Point(2, 5)).toString(),  "Pawn white");
        assertNull("Position 2-4 is empty",  chessboard.getSquare(new Point(2, 4))); 
    }
    /**
     * Function used to check if the short Castling is not legal since the tower has moved.
     */
    @Test
    public void castlingNotPossibleCheck() { 
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(6, 0),  new Point(7, 2)));
        moves.add(new MoveInfoImpl(new Point(6, 1),  new Point(6, 2)));
        moves.add(new MoveInfoImpl(new Point(5, 0),  new Point(6, 1)));
        //the rook moves
        moves.add(new MoveInfoImpl(new Point(7, 0),  new Point(6, 0)));
        moves.add(new MoveInfoImpl(new Point(6, 0),  new Point(7, 0)));
        //illegal move
        moves.add(new MoveInfoImpl(new Point(4, 0),  new Point(6, 0)));
        chessboard = new ChessboardModelImpl(moves);
        //CHECK THE POSITION OF KING AND ROOK
        assertEquals("Position 4-0 is white king",  chessboard.getSquare(new Point(4, 0)).toString(),  "King white");
        assertEquals("Position 7-0 is white rook",  chessboard.getSquare(new Point(7, 0)).toString(),  "Rook white"); 
    }
    /**
     * Function used to check if the En Passant is not legal when the pawn hasn't performed a double move.
     */
    @Test
    public void enpassantNotPossibleCheck() { 
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(1, 1),  new Point(1, 3)));
        moves.add(new MoveInfoImpl(new Point(1, 3),  new Point(1, 4)));
        moves.add(new MoveInfoImpl(new Point(2, 6),  new Point(2, 5)));
        moves.add(new MoveInfoImpl(new Point(2, 5),  new Point(2, 4)));
        //illegal move
        moves.add(new MoveInfoImpl(new Point(1, 4),  new Point(2, 5)));
        chessboard = new ChessboardModelImpl(moves);
        //CHECK THE POSITION OF PAWN AND IF THE OTHER PAWN HAS BEEN EATEN
        assertEquals("Position 1-4 is white pawn",  chessboard.getSquare(new Point(1, 4)).toString(),  "Pawn white");
        assertEquals("Position 2-4 is black pawn",  chessboard.getSquare(new Point(2, 4)).toString(),  "Pawn black");
    }
    /**
     * Function used to check if the method to avoid the king moving into a position that would put him under check works.
     */
    @Test
    public void kingCheck() { 
        List<MoveInfoImpl> moves = new ArrayList<MoveInfoImpl>();
        //generate a list of valid moves
        moves.add(new MoveInfoImpl(new Point(5, 1),  new Point(5, 2)));
        moves.add(new MoveInfoImpl(new Point(6, 1),  new Point(6, 3)));
        moves.add(new MoveInfoImpl(new Point(4, 6),  new Point(4, 5)));
        moves.add(new MoveInfoImpl(new Point(4, 1),  new Point(4, 2)));
        moves.add(new MoveInfoImpl(new Point(3, 7),  new Point(7, 3)));
        //illegal move
        moves.add(new MoveInfoImpl(new Point(4, 0),  new Point(5, 1)));
        //legal move
        moves.add(new MoveInfoImpl(new Point(4, 0),  new Point(4, 1)));
        chessboard = new ChessboardModelImpl(moves);
        //check if the king has moved into the right square
        assertEquals("Position 4-1 is white king",  chessboard.getSquare(new Point(4, 1)).toString(),  "King white");
    }
}
