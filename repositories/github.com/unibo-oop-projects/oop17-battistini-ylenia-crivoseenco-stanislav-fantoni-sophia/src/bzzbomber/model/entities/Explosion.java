
package bzzbomber.model.entities;

import java.awt.Dimension;
import java.awt.Point;

import bzzbomber.model.Model;
import bzzbomber.view.TileImg;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Model an explosion.
 */
public final class Explosion extends EntityImpl {

    private static final int DEFAULT_TIMER = 1;

    private int timeRange;
    private boolean timefinish;
    private final Model model;
    private final TileImpl tile;

    /**
     * Constructor of Explosion.
     * 
     * @param pos
     *            The position
     * @param model
     *            The model
     */
    public Explosion(final Point pos, final Model model) {
        super(pos);
        final Dimension dim = new Dimension(super.getCollisionBox().width, super.getCollisionBox().height);
        super.getCollisionBox().setSize(dim.width * 3, dim.height * 3);
        this.timeRange = Explosion.DEFAULT_TIMER;
        this.timefinish = false;
        this.model = model;
        this.tile = new TileImpl(TileImg.EXPLOSION);

    }

    /**
     * Decrement the time range of explosion.
     */
    public void decrement() {
        for (final Block b : this.model.getAllBlock()) {
            if (b.getCollisionBox().intersects(super.getCollisionBox())) {
                b.isDestroyed();
                this.model.getCurrentLevel().getEntityManager().removeEntity(b);
            }
        }
        this.timeRange--;
        if (this.timeRange == 0) {
            this.timefinish = true;
        }
    }

    /**
     * Getter of the finish time.
     * 
     * @return true if the time is finished, false otherwise.
     */
    public boolean isTimefinish() {
        return this.timefinish;
    }

    @Override
    public TileImpl getTile() {
        return this.tile;
    }

}
