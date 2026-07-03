package model.utilities.pawns;

import java.util.Set;

import utilities.Pair;
import utilities.Players;

/**
 *@author : Giulio Bacchilega
 */
public interface PawnInterface {

    /**
     * Change the actual position of this pawn.
     * @param next the next coordinate of this pawn
     */
    void setNewPosition(final Pair<Integer, Integer> next);

    /**
     * Return the type of this pawn.
     * @return PawnType
     */
    PawnTypes getType();

    /**
     * Return the player owner of this pawn.
     * @return Players
     */
    Players getPlayer();

    /**
     * Return the current position of this pawn.
     * @return Pair<Integer,Integer>
     */
    Pair<Integer, Integer> getActualPosition();

    /**
     * Decrement the number of moves of the pawn, after the reset-move command by Controller.
     */
    void decrementMoves();

    /**
     * Return the actual value of the variable 'isMoved'.
     * @return Boolean
     */
    boolean isMoved();

    /**
     * Return the set containing the possible moves of this pawn.
     * @return Set<Pair<Integer,Integer>>
     */
    Set<Pair<Integer, Integer>> getPossibleMoves();

    /**
     * 
     */
    void setAfterCreation();

    /**
     * Return true if the invocation of the relative getter method return true, comparing matching parameters.
     * @param toCompare the parameter to compare.
     * @param <X> the type of the given parameter
     * @return Boolean
     */
    <X> boolean compare(X toCompare);

    /**
     * @return String
     */
    String toString();
}
