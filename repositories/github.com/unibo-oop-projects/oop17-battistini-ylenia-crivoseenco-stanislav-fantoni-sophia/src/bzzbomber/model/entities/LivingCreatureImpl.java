
package bzzbomber.model.entities;

import java.awt.Point;
import bzzbomber.model.utilities.Direction;
import bzzbomber.view.gamefield.TileImpl;

/**
 * Implementation of interface @LivingCreature and extend @EntityImpl .
 */
public abstract class LivingCreatureImpl extends EntityImpl implements LivingCreature {

    private int life;

    /**
     * Constructs a new LivingCreature.
     * 
     * @param pos
     *            The creature's initial position
     * @param life
     *            The life.
     */
    public LivingCreatureImpl(final Point pos, final int life) {
        super(pos);
        this.life = life;
    }

    @Override
    public final void move(final Direction dir) {
        this.setPosition(new Point(this.getNextPosition(dir.getTranslation())));
        super.setCollisionBox(this.getPosition());

    }

    @Override
    public final void addLife() {
        this.life += 1;
    }

    @Override
    public final void removeLife() {
        this.life -= 1;
    }

    @Override
    public final Point getNextPosition(final Point pos) {
        return new Point(super.getPosition().x + pos.x, super.getPosition().y + pos.y);
    }

    @Override
    public final int getRemainingLives() {
        return this.life;
    }

    @Override
    public final boolean isAlive() {
        return this.life > 0;
    }

    @Override
    public final void setLife(final int life) {
        this.life = life;
    }

    @Override
    public abstract TileImpl getTile();

}
