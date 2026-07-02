package model.collidable.tank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.collidable.tank.color.TankColor;
import model.collidable.tank.color.TankColorImpl;
import model.collidable.tank.shape.TankShape;
import model.physics.collider.Polycollider2D;
import model.physics.collider.Polycollider2DImpl;
import model.physics.particle.BasicParticleCreator;
import model.physics.particle.Particle;
import model.physics.particle.environment.GravityOnlyEnvironment;
import model.physics.particle.shape.Sphere;

/**
 * Represents a tank.
 * 
 * @author Nicola Tamburini
 *
 */
public final class TankImpl implements Tank {

    private static final double MASS = 55000.0;
    private final TankColor tankColor;
    private final TankShape tankShape;
    private final double scaleFactor;
    private Particle particle;
    private double elevationAngle;
    private double initialSpeed;
    private boolean destroyed;

    /**
     * /** * Constructor.
     *
     * @param tankColor
     *            tank color
     *
     * @param tankShape
     *            tank shape
     * @param scaleFactor
     *            scale factor
     * @param position
     *            position of the tank, in metre
     * @param gravitationalAcceleration
     *            gravitational acceleration present in the battlefield
     */
    protected TankImpl(final TankColor tankColor, final TankShape tankShape, final double scaleFactor,
            final Vector2D position, final Vector2D gravitationalAcceleration) {
        super();
        this.tankColor = new TankColorImpl(tankColor.getRed(), tankColor.getGreen(), tankColor.getBlue(),
                tankColor.getOpacity());
        this.tankShape = tankShape;
        this.scaleFactor = scaleFactor;
        particle = new BasicParticleCreator().createParticle(position, new Vector2D(0.0, 0.0),
                new GravityOnlyEnvironment.Builder().setGravitationalAcceleration(gravitationalAcceleration).build(),
                MASS, new Sphere(1.0));

    }

    /**
     *
     * Copy Constructor.
     *
     * @param tank
     *            tank
     */
    public TankImpl(final Tank tank) {
        this(tank.getColor(), tank.getTankShape(), tank.getScaleFactor(), tank.getPosition(),
                tank.getGravitationalAcceleration());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TankColor getColor() {
        return new TankColorImpl(tankColor.getRed(), tankColor.getGreen(), tankColor.getBlue(), tankColor.getOpacity());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TankShape getTankShape() {
        return this.tankShape;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getScaleFactor() {
        return scaleFactor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Polycollider2D getPolycollider() {
        return new Polycollider2DImpl(tankShape.getOutlinePoints().stream()
                .map(p -> p.scalarMultiply(scaleFactor).add(particle.getPosition())).collect(Collectors.toList()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vector2D> getOutlinePoints() {
        return tankShape.getOutlinePoints().stream().map(p -> p.scalarMultiply(scaleFactor).add(particle.getPosition()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Vector2D> getTurretPoints() {
        final List<Vector2D> list = new ArrayList<>(Arrays.asList(tankShape.getTurretPoints().get(0),
                new Vector2D(Math.cos(elevationAngle), Math.sin(elevationAngle))
                        .scalarMultiply(tankShape.getTurretPoints().get(0).distance(tankShape.getTurretPoints().get(1)))
                        .add(tankShape.getTurretPoints().get(0))));

        return list.stream().map(p -> p.scalarMultiply(scaleFactor).add(particle.getPosition()))
                .collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getTurretMuzzle() {
        return getTurretPoints().get(1);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getElevationAngle() {
        return elevationAngle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setElevationAngle(final double elevationAngle) {
        this.elevationAngle = elevationAngle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getProjectileInitialSpeed() {
        return initialSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setProjectileInitialSpeed(final double initialSpeed) {
        this.initialSpeed = initialSpeed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDestroyed(final boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setFalling() {
        particle = new BasicParticleCreator().createParticle(getPosition(), new Vector2D(0.0, 0.0),
                particle.getEnvironment(), MASS, particle.getShape());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getExplosionRadius() {
        return scaleFactor * tankShape.getExplosionRadius();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPreviousPosition() {
        return particle.getPreviousPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPreviousVelocity() {
        return particle.getPreviousVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPreviousAcceleration() {
        return particle.getPreviousAcceleration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getPosition() {
        return particle.getPosition();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getVelocity() {
        return particle.getVelocity();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getAcceleration() {
        return particle.getAcceleration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isStationary() {
        return particle.isStationary();
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public double getMass() {
        return MASS;
    };

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector2D getGravitationalAcceleration() {
        return ((GravityOnlyEnvironment) this.particle.getEnvironment()).getGravitationalAcceleration();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(final double timeStep) throws IllegalArgumentException {
        particle.update(timeStep);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void collide(final Polycollider2D polycollider2D, final double timeStep,
            final double coefficientOfRestitution) throws IllegalArgumentException {
        particle.collide(polycollider2D, timeStep, coefficientOfRestitution);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double getKineticEnergy() {
        return particle.getKineticEnergy();
    }

    /**
     * Tank Builder.
     * 
     * @author Nicola Tamburini
     *
     */
    public static class Builder {
        private TankColor tankcolor;
        private TankShape tankshape;
        private double scalefactor;
        private Vector2D position;
        private Vector2D gravitationalAcceleration;

        /**
         * 
         * @param tankcolor
         *            color of tank in type {@link TankColor}
         * @return a istance of {@link Builder}
         */
        public Builder setTankColor(final TankColor tankcolor) {
            this.tankcolor = tankcolor;
            return this;
        }

        /**
         * 
         * @param tankshape
         *            shape of tank in type {@link TankShape}
         * @return a istance of {@link Builder}
         */
        public Builder setTankShape(final TankShape tankshape) {
            this.tankshape = tankshape;
            return this;
        }

        /**
         * 
         * @param scalefactory
         *            tank scale factory
         * @return a istance of {@link Builder}
         */
        public Builder setScaleFactor(final double scalefactory) {
            this.scalefactor = scalefactory;
            return this;
        }

        /**
         * 
         * @param position
         *            tank position
         * @return a istance of {@link Builder}
         */
        public Builder setPosition(final Vector2D position) {
            this.position = position;
            return this;
        }

        /**
         * 
         * @param gravitationalAcceleration
         *            gravitational acceleration where the tank is placed
         * @return a istance of {@link Builder}
         */
        public Builder setGravitationalAcceleration(final Vector2D gravitationalAcceleration) {
            this.gravitationalAcceleration = gravitationalAcceleration;
            return this;
        }

        /**
         * 
         * @return {@link Tank}
         * @throws IllegalStateException
         *             when objects are null
         * @throws IllegalArgumentException
         *             scale factor must be positive
         */
        public TankImpl build() throws IllegalStateException, IllegalArgumentException {
            if (tankcolor == null || tankshape == null || position == null || gravitationalAcceleration == null) {
                throw new IllegalStateException();
            }
            if (this.scalefactor <= 0.0) {
                throw new IllegalArgumentException();
            }
            return new TankImpl(tankcolor, tankshape, scalefactor, position, gravitationalAcceleration);
        }

    }

}
