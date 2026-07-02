package model.explosion;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 *
 * @author Oleg
 *
 */
public final class ExplosionImpl implements Explosion {
    private final Vector2D position;
    private final double blastRadius;
    private final double explosionTime;
    private boolean exploded;
    private double elapsedTime;

    /**
     *
     * Constructor.
     *
     * @param position
     *            position, in metre
     * @param blastRadius
     *            blast radius, in metre
     * @param explosionTime
     *            explosion time, in second
     */
    public ExplosionImpl(final Vector2D position, final double blastRadius, final double explosionTime) {
        super();
        this.position = position;
        this.blastRadius = blastRadius;
        this.explosionTime = explosionTime;
        exploded = false;
    }

    /**
     * Copy constructor.
     *
     * @param explosion
     *            explosion
     */
    public ExplosionImpl(final Explosion explosion) {
        this(explosion.getPosition(), explosion.getBlastRadius(), explosion.getExplosionTime());
    }

    @Override
    public void update(final double timeStep) throws IllegalArgumentException {
        if (timeStep >= explosionTime) {
            throw new IllegalArgumentException();
        }
        if (elapsedTime <= explosionTime - timeStep) {
            elapsedTime += timeStep;
        } else {
            exploded = true;
        }
    }

    @Override
    public double getCurrentBlastRadius() {
        return Math.sin(elapsedTime / explosionTime * Math.PI) * blastRadius;
    }

    @Override
    public Vector2D getPosition() {
        return position;
    }

    @Override
    public double getBlastRadius() {
        return blastRadius;
    }

    @Override
    public double getExplosionTime() {
        return explosionTime;
    }

    @Override
    public boolean isExploded() {
        return exploded;
    }

    @Override
    public boolean isApexReached() {
        return elapsedTime >= explosionTime / 2.0;
    }

    @Override
    public boolean isApexTime(final double timeStep) {
        return Math.abs(elapsedTime - explosionTime / 2.0) < timeStep;
    }
}
