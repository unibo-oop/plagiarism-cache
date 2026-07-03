package controller.setting;

import java.util.Set;

import utilities.Pair;


/**
 * this object must be instantiate when in the setting game the threat pawns must be shown
 * @author Alex Ravaglia
 *
 */
public class ThreatActive extends ThreatPawns{

    /**
     * update the view to show the threat pawns by the opposite 
     */
    public void viewThreatPawn(final Set<Pair<Integer, Integer>> threatPawns){
        VIEW.showThreatenedPawns(threatPawns);
    }

}
