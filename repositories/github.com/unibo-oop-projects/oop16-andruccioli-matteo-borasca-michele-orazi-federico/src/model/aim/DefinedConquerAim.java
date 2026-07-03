package model.aim;

import java.util.Set;

import model.player.PlayerInfo;
import model.region.Region;
/**
 * 
 *This Class models the concept of a DefinedConquerAim. 
 *  This aim consists in conquering all the states of specified regions.
 *
 */
public class DefinedConquerAim extends AbstractRegionAim {

    private static final long serialVersionUID = -8401677739426115333L;

    /**
     * @param regions
     *          regions the player must own to win the game.
     * 
     * @param player
     *          player this aim has been assigned to.
     * 
     */
    public DefinedConquerAim(final Set<Region> regions, final PlayerInfo player) {
        super(regions, player, AimLabel.DCA);
    }

    @Override
    public boolean evaluateFeaturesAimAchieved() {
        return true;
    }

    @Override
    public String toString() {
        String s = "Conquer entirely";
        for (final Region r : super.getRegionList()) {
            s = s + " " + r.getName() + " ";
        }
        return s;
    }
}
