
package bzzbomber.model.entities;

import java.awt.Point;

import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Model Bomb's entity.
 * 
 */
public final class Bomb extends EntityImpl {

    private static final int INITIAL_TIMER = 3;

    private int bombRange;
    private boolean exploded;
    private final TileImpl tile;

    /**
     * Constructor of @Bomb .
     * 
     * @param pos
     *            The position of bomb.
     */
    public Bomb(final Point pos) {
        super(pos);
        this.exploded = false;
        this.bombRange = INITIAL_TIMER;
        this.tile = new TileImpl(TileImg.BOMB);
    }

    /**
     * Method to decrement range of the explosion.
     */
    public void decrement() {
        if (this.bombRange >= 0) {
            this.bombRange--;
            if (this.bombRange == 0) {
                this.exploded = true;
            }
        }
    }

    /**
     * Getter of exploded.
     * 
     * @return true if is exploded, false otherwise
     */
    public boolean isExploded() {
        return this.exploded;
    }

    @Override
    public TileImpl getTile() {
        return this.tile;
    }

}
