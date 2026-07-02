package model.physics.particle.shape;

/**
 *
 * Represent a particle shape.
 *
 * @author Nicola Zamagni
 *
 */
public interface ParticleShape {
    /**
     *
     * @return drag coefficient of the particle
     */
    double getDragCoeffigent();

    /**
     *
     * @return reference area of the particle, in square metres
     */
    double getReferenceArea();

    /**
     *
     * @return volume of the particle, in cubic metre
     */
    double getVolume();

    /**
     *
     * @param volumeScaleFactor
     *            volume scale factor
     * @return a shape similar to the original but with a specified volume scale
     *         factor
     */
    ParticleShape getShapeWithVolumeScaleFactor(double volumeScaleFactor);

}
