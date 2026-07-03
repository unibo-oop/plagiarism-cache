package controller.setting;

import java.util.Set;

import utilities.Pair;

/**
 * this object must be instantiate when in the setting game the threat pawns must not be shown
 * @author Alex Ravaglia
 *
 */
public class ThreatDisabled extends ThreatPawns{

    /**
     * nothing happen because the threat pawns not must be shown
     */
    public void viewThreatPawn(final Set<Pair<Integer, Integer>> threatPanws) {}



}
