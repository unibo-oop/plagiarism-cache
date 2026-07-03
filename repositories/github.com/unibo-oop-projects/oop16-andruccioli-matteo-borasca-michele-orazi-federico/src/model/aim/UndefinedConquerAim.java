package model.aim;

import java.util.Set;

import model.player.PlayerInfo;
import model.region.Region;

/**
 * 
 *This Class models the concept of a DefinedConquerAim. 
 *  This aim consists in conquering all the states of specified regions
 *      and all the states of a third region which can be chosen by the player.
 *
 */
public class UndefinedConquerAim extends AbstractRegionAim {

    private static final long serialVersionUID = 4684740344091889226L;
    private static final int TOTAL_NUM_REGION = 3;

    /**
     * @param regions
     *          regions the player must own to win the game.
     * 
     * @param player
     *          player this aim has been assigned to.
     * 
     */
    public UndefinedConquerAim(final Set<Region> regions, final PlayerInfo player) {
        super(regions, player, AimLabel.UCA);
    }

    private boolean checkNumRegion() {
        return super.getPlayer().getRegions().size() >= TOTAL_NUM_REGION ? true : false;
    }

    @Override
    public boolean evaluateFeaturesAimAchieved() {
        return checkNumRegion();
    }

    @Override
    public String toString() {
        String s = "Conquer entirely";
        for (final Region r : super.getRegionList()) {
            s = s + " " + r.getName() + ", ";
        }
        return s + "a third Region";
    }
}
