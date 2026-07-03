package model.utilities.pawns;

import utilities.Pair;
import utilities.Players;

/**
 * 
 * @author : Giulio Bacchilega
 *
 */
public interface Generator {

    /**
     * Gives a new instance of the given type of pawn, under the control of the given player in the given position. 
     * @param name , the type of the pawn
     * @param player , the pawn's owner
     * @param actualPosition , the starting position of the pawn
     * @return Pawn 
     */
    Pawn build(final PawnTypes name, final Players player, final Pair<Integer, Integer> actualPosition);
}
