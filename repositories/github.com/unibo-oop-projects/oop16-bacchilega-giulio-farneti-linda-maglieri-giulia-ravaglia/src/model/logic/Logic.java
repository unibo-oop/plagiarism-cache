package model.logic;

import java.util.Map;



import java.util.Set;

import utilities.Castling;
import model.utilities.pawns.King;
import utilities.Pair;
import model.utilities.pawns.Pawn;
import model.utilities.pawns.PawnTypes;
import utilities.Players;
import model.utilities.pawns.Rook;

/**
 * Interface Logic.
 * @author: Giulio Bacchilega
 */
public interface Logic {

    /**
     * @return Player , the actual player
     */
    Players getActualPlayer();

    /**
     * Changing player.
     */
    void changePlayer();

    /**
     * Initialize the king observers.
     */
    void setKingObservers();

    /**
     * Return a set containing the possible moves for a generic pawn.
     * @param pair , the actual position of the pawn to move
     * @return Set<Pair<Integer,Integer>>
     */
    Set<Pair<Integer, Integer>> getPossibleMoves(final Pair<Integer, Integer> pair);

    /**
     * Set the movement of the given pawn in the next coordinate.
     * @param pawn , the pawn that has to be moved
     * @param next , the next position of the pawn
     */
    void setNextMove(final Pawn pawn, final Pair<Integer, Integer> next);

    /**
     * @param k , the given King
     * @param r , the given Rook
     * @param type , the given type of Castling
     * @return true if this type of Castling is allowed for King k and Rook r, false otherwise
     */
    boolean checkCastlingMoves(final King k, final Rook r, final Castling type);

    /**
     * @param set the set to control
     * @return true if the coordinates of the given set are free from pawns, false otherwise
     */
    boolean checkFreePath(final Set<Pair<Integer, Integer>> set);

    /**
     * @param set the given set
     * @return true if the coordinates of the given set are threatened by enemy pawns, false otherwise
     */
    boolean checkChessPath(final Set<Pair<Integer, Integer>> set);

    /**
     * @param p the given player
     * @return a map that associate each final coordinate of movement to the relative type of Castling
     */
    Map<Pair<Integer, Integer>, Castling> getCastlingMoves(final Players p);

    /**
     * Set the Castling of the given King k, following the type of Castling.
     * @param type , the type of Castling
     * @param k , the given King
     */
    void setCastling(final Castling type, final King k);

    /**
     * @return true if the opposite player is under check, false otherwise
     */
    boolean isOppositePlayerUnderCheck();

    /**
     * @return true if the opposite player is under Check Mate, false otherwise
     */
    boolean isCheckMate();

    /**
     * Notify both king observer for the movement othe pawn p, from the starting position first, to the final position second.
     * @param p , the given pawn
     * @param first , the starting position
     * @param second . the final position
     */
    void notifyObservers(final Pawn p, final Pair<Integer, Integer> first, final Pair<Integer, Integer> second);

    /**
     * Replacement of the given pawn with another pawn of the given type.
     * @param pawn the given pawn
     * @param type the given type
     * @param next the next position
     */
    void setPawnInLimit(final Pawn pawn, final PawnTypes type, final Pair<Integer, Integer> next);

    /**
     * Put a removed pawn in the chessboard, updating KingObservers.
     * @param removed the pawn that was removed
     * @param pair the last position of the pawn
     */
    void putRemovedPawn(final Pawn removed, final Pair<Integer, Integer> pair);

    /**
     * Remove a pawn added in the last turn.
     * @param added the pawn that was put in thee chessboard
     * @param pair the position of the pawn
     */
    void removeAddedPawn(Pawn added, Pair<Integer, Integer> pair);

    /**
     * Decrement the counter moves of the pawn.
     * @param pawn the given pawn
     * @param next the last position of the pawn
     */
    void restoreMoves(Pawn pawn, final Pair<Integer, Integer> next);

    /**
     * Start another match.
     */
    void resetGame();
}
