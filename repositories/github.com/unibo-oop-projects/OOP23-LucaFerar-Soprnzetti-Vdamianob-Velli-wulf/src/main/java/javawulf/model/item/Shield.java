package javawulf.model.item;

import javawulf.model.AbstractCollectable;
import javawulf.model.Coordinate;
import javawulf.model.player.Player;
import javawulf.model.player.PlayerHealth.ShieldStatus;

/**
 * The shield lets the player take two additional hits.
 */
public final class Shield extends AbstractCollectable {

    private static final int POINTS = 800;

    /**
     * Creates a new shield.
     * 
     * @param position the position of the shield
     */
    public Shield(final Coordinate position) {
        super(position, POINTS);
    }

    @Override
    public void applyEffect(final Player p) {
        p.getPlayerHealth().setShieldStatus(ShieldStatus.FULL);
    }

}
