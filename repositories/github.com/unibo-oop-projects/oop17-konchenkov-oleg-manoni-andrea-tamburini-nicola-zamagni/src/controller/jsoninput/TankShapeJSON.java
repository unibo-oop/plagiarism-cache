package controller.jsoninput;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

/**
 * Interface for the class TankJSONImpl.
 *
 * @author Andrea Manoni
 *
 */
public interface TankShapeJSON {
    /**
     *
     * Returns the outline points of the tank.
     *
     * @return outline points of the tank
     */
    List<Vector2D> getOutlinePoints();

    /**
     *
     * Returns the points of the turret.
     *
     * @return points of the turret
     */
    List<Vector2D> getTurretPoints();

    /**
     *
     * Returns the explosion radius of the tank.
     *
     * @return explosion radius
     */
    double getExplosionRadius();

    /**
     * Load all the data from the JSON file.
     * 
     * @throws UnsupportedEncodingException
     *             if the encoding is not supported.
     * @throws FileNotFoundException
     *             if the json file is not found.
     * @throws IOException
     *             if the json file is not found.
     */
    void load() throws UnsupportedEncodingException, FileNotFoundException, IOException;
}
