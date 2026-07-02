package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * An extra heart item that increases the player's max health by one.
 */
public final class ExtraHeart extends AbstractCollectable {

    private static final int POINTS = 400;

    /**
     * Creates a new extra heart item.
     * 
     * @param position the position of the extra heart item
     */
    public ExtraHeart(final Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(final Player p) {
        p.getPlayerHealth().increaseMaxHealth(1);
    }

}
