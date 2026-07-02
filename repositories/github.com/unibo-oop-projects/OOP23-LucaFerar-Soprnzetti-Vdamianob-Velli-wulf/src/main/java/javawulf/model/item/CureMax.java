package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * The CureMax item restores the player's health to full.
 */
public final class CureMax extends AbstractCollectable {

    private static final int POINTS = 400;

    /**
     * Creates a new cure max potion.
     * 
     * @param position the position of the cure item
     */
    public CureMax(final Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(final Player p) {
        p.getPlayerHealth().setHealth(p.getPlayerHealth().getMaxHealth());
    }

}
