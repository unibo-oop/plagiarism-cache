package model.collidable.terrain.noise;

/**
 * noise generator.
 * 
 * @author Nicola Tamburini
 *
 */
public interface NoiseGenerator1D {

    /**
     *
     * @param x
     *            abscissa
     * @return ordinate
     */
    double noise(double x);
}
