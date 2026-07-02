package model.physics.particle.shape;

/**
 *
 * Represents an spherical particle shape.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class Sphere implements ParticleShape {

    private static final double DRAG_COEFFICIENT = 0.47;
    private final double radius;

    /**
     *
     * Constructor.
     *
     * @param radius
     *            radius, in metre
     * @throws IllegalArgumentException
     *             radius must be non-negative
     */
    public Sphere(final double radius) throws IllegalArgumentException {
        super();
        this.radius = radius;
        if (this.radius <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getDragCoeffigent() {
        return DRAG_COEFFICIENT;
    }

    @Override
    public double getReferenceArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public double getVolume() {
        return 4.0 / 3.0 * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public ParticleShape getShapeWithVolumeScaleFactor(final double volumeScaleFactor) {
        return new Sphere(Math.pow(volumeScaleFactor, 1.0 / 3.0) * radius);
    }

}
