package model.logic;

import java.util.Map;

import java.util.Optional;
import java.util.Set;

import utilities.Castling;
import utilities.Directions;
import utilities.Pair;
import model.utilities.pawns.Pawn;

/**
 * King's observer interface.
 * @author : Giulio Bacchilega.
 */
public interface Observer {

    /**
     * After the movement of a generic pawn, control which updates has to be done int the horizon map.
     * @param pawn , the pawn to control
     * @param start , the starting position of the pawn
     * @param next , the final position of the pawn
     */
    void checkUpdates(Pawn pawn, Pair<Integer, Integer> start, Pair<Integer, Integer> next);

    /**
     * Return the direction of the horizonSet mapped which contains the specyfied pair.
     * @param pair , the pair to control
     * @return Optional<Directions>, empty if the given pair is not mapped in the horizonSet
     */
    Optional<Directions> getDirectionByCoordinate(final Pair<Integer, Integer> pair);

    /**
     * Return true if the king is threatened by the given pawn.
     * @param pawn the given pawn
     * @return Boolean
     */
    boolean checkChess(final Pawn pawn);

    /**
     * Return true if the enemy given pawn is in the horizon of this king.
     * @param pawn the given pawn
     * @return Boolean
     */
    boolean isCheck(final Pawn pawn);

    /**
     * @return true if the observed King is under check, false otherwise
     */
    boolean isUnderCheck();

    /**
     * @return the set containing all coordinate between King and the pawn that caused the check
     */
    Set<Pair<Integer, Integer>> getCheckPath();

    /**
     * @return the set containing the positions of all pawns in the horizon of the king
     */
    Set<Pair<Integer, Integer>> getLastHorizonCoordinate();

    /**
     * Return the possible moves of a pawn, if this pawn in contained in the horizon of the King.
     * @param pawn the pawn to control
     * @param possibleMoves all allowed movements for the given pawn
     * @return set containing the "filtered" moves for this pawn
     */
    Set<Pair<Integer, Integer>> possibleMovesForPawn(Pawn pawn, Set<Pair<Integer, Integer>> possibleMoves);

    /**
     * Return the possible moves for the observed King.
     * @param possibleMoves , the set of all the allowed movements for the King
     * @return set , that contains the "safe" coordinate of movement of the observed king
     */
    Set<Pair<Integer, Integer>> getPossibleMovesForKing(Set<Pair<Integer, Integer>> possibleMoves);

    /**
     * @return a map that associate the allowed coordinate of castling for this King with the relative type of castling
     */
    Map<Pair<Integer, Integer>, Castling> getCastlingMoves();

    /**
     * Restore the condition of check of this king.
     */
    void checkResolved();
}

