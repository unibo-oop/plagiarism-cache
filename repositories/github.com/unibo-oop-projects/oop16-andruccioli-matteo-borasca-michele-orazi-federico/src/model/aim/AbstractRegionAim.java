package model.aim;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import model.player.PlayerInfo;
import model.region.Region;

/**
 * 
 * Class that implements a behavior common to those aim which need to check
 * whether or not some regions are owned by the player.
 *
 */
public abstract class AbstractRegionAim implements Aim {

    private static final long serialVersionUID = 8281632899492361468L;
    private final Set<Region> regions;
    private final PlayerInfo player;
    private final AimLabel type;

    /**
     * @param regions
     *            regions the player must own to win the game.
     * 
     * @param player
     *            player this aim has been assigned to.
     * 
     * @param type
     *            this aim type.
     * 
     */
    public AbstractRegionAim(final Set<Region> regions, final PlayerInfo player, final AimLabel type) {
        super();
        this.regions = regions;
        this.player = player;
        this.type = type;
    }

    @Override
    public boolean aimAchieved() {
        return (checkRegions() && evaluateFeaturesAimAchieved());
    }

    /**
     * 
     * @return whether or not the player have satisfied particular requirements of his aim.
     *
     */
    public abstract boolean evaluateFeaturesAimAchieved();

    /**
     * 
     * @return whether or not the regions are controlled by the player.
     *
     */
    public boolean checkRegions() {
        return this.player.getRegions().containsAll(this.regions) ? true : false;
    }

    /**
     * 
     * @return a copy of Regions assigned to player.
     *
     */
    public List<Region> getRegionList() {
        return new LinkedList<>(this.regions);

    }

    @Override
    public PlayerInfo getPlayer() {
        return player;
    }

    @Override
    public AimLabel getType() {
        return this.type;
    }

}
