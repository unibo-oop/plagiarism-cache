package model.collidable.terrain;

import java.util.List;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import model.physics.collider.Polycollider2D;

/**
 * Represents the behavior of a falling terrain chunk.
 * 
 * @author Nicola Tamburini
 *
 */
public interface FallingChunk {

    /**
     * 
     * @return outline points of terrainn's chunk
     */
    List<Vector2D> getOutlinePoints();

    /**
     * update chunk.
     * 
     * @param timeStep
     *            timestep
     */
    void update(double timeStep);

    /**
     * 
     * @param poly
     *            polycollider2D
     * @param timeStep
     *            timestep
     * @param coefficentRestitution
     *            coefficent restituton of collision
     */
    void collide(Polycollider2D poly, double timeStep, double coefficentRestitution);

    /**
     * 
     * @return true if chunk is stationary, false if not
     */
    boolean isStationary();

    /**
     * 
     * @return all chunk points
     */
    List<Vector2D> getAllPoints();

    /**
     * 
     * @return initial point of the lower points of chunk
     * 
     */
    Vector2D getInitialPoint();

    /**
     * 
     * @return final point of the lower points of chunk
     */
    Vector2D getFinalPoint();
}
