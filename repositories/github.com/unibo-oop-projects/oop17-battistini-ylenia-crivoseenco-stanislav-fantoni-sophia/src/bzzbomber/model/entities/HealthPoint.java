package bzzbomber.model.entities;

import java.awt.Point;
import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Class to model a HealthPoint.
 * 
 * 
 */
public final class HealthPoint extends EntityImpl {

    private final TileImpl tile;
    private boolean taken;

    /**
     * Constructor of HealthPoint.
     * 
     * @param pos
     *            The position.
     */
    public HealthPoint(final Point pos) {
        super(pos);
        this.taken = false;
        this.tile = new TileImpl(TileImg.HEALTH);
    }

    @Override
    public TileImpl getTile() {
        return this.tile;
    }

    /**
     * Getter for know if heart was taken.
     * 
     * @return true if was taken, false otherwise.
     */
    public boolean isTaken() {
        return this.taken;
    }

    /**
     * Setter for is taken.
     * 
     * @param taken
     *            A boolean state.
     */
    public void setTaken(final boolean taken) {
        this.taken = taken;
    }

}
