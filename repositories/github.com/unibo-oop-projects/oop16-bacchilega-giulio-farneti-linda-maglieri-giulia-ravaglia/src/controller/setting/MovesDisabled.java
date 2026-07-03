package controller.setting;

import java.util.Set;
import utilities.Pair;

/**
 * this object must be instantiate when in the setting game the possible moves must not be shown
 * @author Alex Ravaglia
 *
 */
public class MovesDisabled extends PossibleMoves{

    /**
     * nothing happen because the possible moves not must be shown
     */
    public void checkPossibleMoves(final Set<Pair<Integer, Integer>> possibleMoves) {}

}
