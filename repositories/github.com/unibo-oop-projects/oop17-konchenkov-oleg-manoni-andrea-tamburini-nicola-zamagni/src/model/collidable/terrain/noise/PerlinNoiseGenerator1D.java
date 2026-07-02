/**
 * 
 */
package model.collidable.terrain.noise;

import java.util.Random;

/**
 * Perlin Noise generator.
 * 
 * @author Nicola Tamburini
 *
 */
public final class PerlinNoiseGenerator1D implements NoiseGenerator1D {

    private static final double EPSILON = 10.0 * Double.MIN_VALUE;
    private final Random generator;
    private double a;
    private double b;
    private final double amplitude;
    private final double waveLength;

    /**
     *
     * Constructor.
     *
     * @param seed
     *            seed
     * @param amplitude
     *            amplitude
     * @param waveLength
     *            wave length
     * @throws IllegalArgumentException
     *             {@code waveLenght} must be positive
     */
    public PerlinNoiseGenerator1D(final long seed, final double amplitude, final double waveLength)
            throws IllegalArgumentException {
        super();
        if (waveLength < 0) {
            throw new IllegalArgumentException();
        }

        generator = new Random(seed);
        a = generator.nextDouble();
        b = generator.nextDouble();
        this.amplitude = amplitude;
        this.waveLength = waveLength;
    }

    private double cosineInterpolate(final double y1, final double y2, final double mu) {
        final double mu2 = (1 - Math.cos(mu * Math.PI)) * 0.5;
        return y1 * (1 - mu2) + y2 * mu2;
    }

    @Override
    public double noise(final double x) {
        if (Math.abs(x % waveLength) < EPSILON) {
            a = b;
            b = generator.nextDouble();
            return a * amplitude;
        } else {
            return cosineInterpolate(a, b, x % waveLength / waveLength) * amplitude;
        }
    }

}
