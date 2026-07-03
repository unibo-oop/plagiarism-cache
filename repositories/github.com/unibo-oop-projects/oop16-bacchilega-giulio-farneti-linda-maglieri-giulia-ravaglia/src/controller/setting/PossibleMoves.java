package controller.setting;

import java.util.Set;
import utilities.Pair;
import view.Chess;

/**
 * abstract class for the possible moves. it will be extended by 2 classes
 * that will implement in 2 different way in fuction of the game setting.
 * 
 * @author Alex Ravaglia
 *
 */
public abstract class PossibleMoves {

    protected static final Chess VIEW = Chess.getLog();

    /**
     * abstract method that will be implemented in different way in function of the Game setting
     * @param possibleMoves: possible moves for the selected pawns
     */
    public abstract void checkPossibleMoves(final Set<Pair<Integer,Integer>> possibleMoves);
}
