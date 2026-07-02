package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * A cure item that restores the player's health by one.
 */
public final class Cure extends AbstractCollectable {

    private static final int POINTS = 100;

    /**
     * Creates a new cure potion.
     * 
     * @param position the position of the cure item
     */
    public Cure(final Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(final Player p) {
        p.getPlayerHealth().setHealth(1);
    }

}
