package bzzbomber.model.entities;

import java.awt.Point;

import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Model a block .
 *
 */
public final class Block extends EntityImpl {

    private final boolean destroyed;
    private final TileImpl tile;

    /**
     * Constructor of Block.
     * 
     * @param pos
     *            The position.
     */
    public Block(final Point pos) {
        super(pos);
        this.destroyed = false;
        this.tile = new TileImpl(TileImg.BOX);
    }

    /**
     * Getter for destroyed.
     * 
     * @return true if it's destroyed, false otherwise
     */
    public boolean isDestroyed() {
        return this.destroyed;
    }


    @Override
    public TileImpl getTile() {
        return this.tile;
    }

}
