package model.collidable.terrain;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.collidable.Collidable;
import model.explosion.Explosion;

/**
 * Represents Terrain.
 *
 * @author Nicola Tamburini
 *
 * 
 */
public interface Terrain extends Collidable {

    /**
     *
     * @return true if terrain is stationary.
     */
    boolean isStationary();

    /**
     * update terrain.
     *
     * @param timestep
     *            timestep to update terrain particle
     */
    void update(double timestep);

    /**
     * 
     * @param explosions
     *            explosions
     */
    void explode(List<Explosion> explosions);

    /**
     * 
     * @return all terrain's chunks
     */
    List<List<Vector2D>> getOutlinesPoints();
}
