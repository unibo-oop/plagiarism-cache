package model.physics.particle.shape;

/**
 *
 * Represents a conic particle shape.
 *
 * Instances of this class are guaranteed to be immutable.
 *
 * @author Nicola Zamagni
 *
 */
public final class Cone implements ParticleShape {

    private static final double DRAG_COEFFICIENT = 0.50;
    private final double radius;
    private final double height;

    /**
     *
     * Constructor.
     *
     * @param radius
     *            radius, in metre
     * @param height
     *            height, in metre
     * @throws IllegalArgumentException
     *             radius and height must be both nonnegative
     */
    public Cone(final double radius, final double height) throws IllegalArgumentException {
        super();
        this.radius = radius;
        this.height = height;
        if (this.radius <= 0 || this.height <= 0) {
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
        return 1.0 / 3.0 * Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public ParticleShape getShapeWithVolumeScaleFactor(final double volumeScaleFactor) {
        return new Cone(Math.pow(volumeScaleFactor, 1.0 / 2.0) * radius, volumeScaleFactor * height);
    }

}
