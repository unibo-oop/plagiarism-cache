package it.unibo.bmbman.model.entities;

import java.util.Optional;

import it.unibo.bmbman.model.TerrainFactoryImpl;
import it.unibo.bmbman.model.collision.Collision;
import it.unibo.bmbman.model.utilities.BombState;
import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;
import it.unibo.bmbman.view.utilities.ScreenToolUtils;
/**
 * Implementation of {@link Bomb}.
 */
public class BombImpl extends AbstractEntity implements Bomb {
    private static final int MAX_TIMER = 3;
    private static final long MILLIS = 1000;
    private BombState state;
    private long timer;
    private Optional<Explosion> explosion;
    private final int range;
    /**
     * Create a bomb. 
     * @param position 
     * @param range 
     */
    public BombImpl(final Position position, final int range) {
        super(new Position(position.getX() / ScreenToolUtils.SCALE, 
                position.getY() / ScreenToolUtils.SCALE), EntityType.BOMB, 
                new Dimension(TerrainFactoryImpl.CELL_DIMENSION, TerrainFactoryImpl.CELL_DIMENSION));
        this.state = BombState.PLANTED;
        this.timer = 0;
        this.explosion = Optional.empty();
        this.range = range;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Explosion> getExplosion() {
        return this.explosion;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BombState getState() {
        return this.state;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setBombExploded() {
        this.state = BombState.EXPLODED;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void startTimer() {
        this.timer = System.currentTimeMillis() / MILLIS;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (this.timer > 0) {
            final long now = System.currentTimeMillis() / MILLIS;
            if (now - this.timer >= MAX_TIMER) {
                this.timer = 0;
                this.state = BombState.IN_EXPLOSION;
                this.explosion = Optional.of(new Explosion(this.getPosition(), range));
            }
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove() {
        return getState() == BombState.EXPLODED;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCollision(final Collision c) {
    }
}
