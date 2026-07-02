package model.collidable.tank.shape;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;


/**
 * Represents a tank shape.
 * 
 * @author Nicola Tamburini
 *
 */
public final class TankShapeImpl implements TankShape {

    private final List<Vector2D> outlinePoints;
    private final List<Vector2D> turretPoints;
    private final double explosionRadius;

    /**
     * Constructor.
     * 
     * @param outlinePoints
     *            outline points of tank, in metre
     * @param turretPoints
     *            turret points, in metre
     * @param explosionRadius
     *            explosion radius when tank expode, in metre
     */
    public TankShapeImpl(final List<Vector2D> outlinePoints, final List<Vector2D> turretPoints,
            final double explosionRadius) {
        super();
        this.outlinePoints = new ArrayList<>(outlinePoints);
        this.turretPoints = new ArrayList<>(turretPoints);
        this.explosionRadius = explosionRadius;
    }

    @Override
    public List<Vector2D> getOutlinePoints() {
        return Collections.unmodifiableList(outlinePoints);
    }

    @Override
    public List<Vector2D> getTurretPoints() {
        return Collections.unmodifiableList(turretPoints);
    }

    @Override
    public double getExplosionRadius() {
        return this.explosionRadius;
    }
}
