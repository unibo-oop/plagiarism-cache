package controller.setting;

import java.util.Set;
import utilities.Pair;


/**
 * this object must be instantiate when in the setting game the possible moves must be shown
 * @author Alex Ravaglia
 *
 */
public class MovesActive extends PossibleMoves{

    /**
     * update the view to show the possible moves of a selected pawn
     */
    public void checkPossibleMoves(final Set<Pair<Integer,Integer>> possibleMoves) {
        VIEW.showAllowedMoves(possibleMoves); 
    }

}
