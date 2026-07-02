package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;

/**
 * The Greatsword strengthens the player's sword for a limited use.
 */
public final class GreatSword extends AbstractCollectable {

    private static final int POINTS = 800;

    /**
     * Creates a new greatsword.
     * 
     * @param position the position of the greatsword
     */
    public GreatSword(final Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(final Player p) {
        p.getSword().changeSwordType();
    }
}
