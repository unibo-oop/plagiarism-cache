
package bzzbomber.model.entities;

import java.awt.Point;

import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Model the door to transport in the next level.
 *
 */
public final class Door extends EntityImpl {

    private final TileImpl tile;

    /**
     * Constructor of Door.
     * 
     * @param pos
     *            The position.
     */
    public Door(final Point pos) {
        super(pos);
        this.tile = new TileImpl(TileImg.DOOR);
    }

    @Override
    public TileImpl getTile() {
        return this.tile;
    }

}
