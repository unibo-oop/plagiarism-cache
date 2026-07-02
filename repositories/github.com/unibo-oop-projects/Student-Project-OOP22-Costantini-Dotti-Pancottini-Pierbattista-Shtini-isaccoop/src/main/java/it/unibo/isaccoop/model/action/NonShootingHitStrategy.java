package it.unibo.isaccoop.model.action;

import java.util.Optional;

import it.unibo.isaccoop.model.common.MapElement;
import it.unibo.isaccoop.model.common.Vector2D;

/**
 * NonShootingHitStrategy class which is an HitStrategy implementation.
 * */
public final class NonShootingHitStrategy implements HitStrategy {

    private boolean canHit;
    private long lastHitTime;
    private static final int HIT_TIME = 1000;
    private static final int HIT_DURATION = 5;

    /**
     * Constructor.
     * */
    public NonShootingHitStrategy() {
        this.canHit = false;
        this.lastHitTime = System.currentTimeMillis();
    }

    @Override
    public void hit(final Optional<Vector2D> direction, final MapElement caller) {
        if (System.currentTimeMillis() - this.lastHitTime >= NonShootingHitStrategy.HIT_TIME) {
            this.canHit = true;
            this.lastHitTime = System.currentTimeMillis();
        }
        if (this.canHit && System.currentTimeMillis() - this.lastHitTime >= NonShootingHitStrategy.HIT_DURATION) {
            this.canHit = false;
            this.lastHitTime = System.currentTimeMillis();
        }
    }

    /**
     * Get if the client can hit.
     * @return true if i can hit
     * */
    public boolean canHit() {
        return this.canHit;
    }

}
