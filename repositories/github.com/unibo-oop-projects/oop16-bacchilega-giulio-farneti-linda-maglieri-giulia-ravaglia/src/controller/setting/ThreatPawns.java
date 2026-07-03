package controller.setting;

import java.util.Set;

import utilities.Pair;
import view.Chess;

/**
 * abstract class for the possibility to show the threatened pawns . it will be extended by 2 classes
 * that will implement in 2 different way in fuction of the game setting.
 * 
 * @author Alex Ravaglia
 *
 */
public abstract class ThreatPawns {

    protected static final Chess VIEW = Chess.getLog();

    /**
     * abstract method that will be implemented in different way in function of the Game setting
     * @param threatPawn: the threat pawns of the player by the opponent
     */
    public abstract void viewThreatPawn(final Set<Pair<Integer, Integer>> threatPawns);
}
