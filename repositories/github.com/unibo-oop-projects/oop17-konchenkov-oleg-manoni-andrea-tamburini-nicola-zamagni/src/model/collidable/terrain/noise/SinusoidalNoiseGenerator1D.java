package model.collidable.terrain.noise;

/**
 * Sinusoidal noise generator.
 * 
 * @author Nicola Tamburini
 *
 */
public final class SinusoidalNoiseGenerator1D implements NoiseGenerator1D {

    private final double amplitude;
    private final double waveLength;

    /**
     *
     * @param amplitude
     *            amplitude
     * @param waveLength
     *            wave length
     * @throws IllegalArgumentException
     *             {@code waveLenght} must be positive
     */
    public SinusoidalNoiseGenerator1D(final double amplitude, final double waveLength) throws IllegalArgumentException {
        if (waveLength < 0) {
            throw new IllegalArgumentException();
        }
        this.amplitude = amplitude;
        this.waveLength = waveLength;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public double noise(final double x) {
        return (amplitude / 2.0 * Math.sin(2.0 * Math.PI * x / waveLength)) + amplitude / 2.0;
    }

}
