package model.physics;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Models the entity model
 */
public interface PhysicEntity {

    /**
     * @return the physic body of the entity
     */
    Body getBody();

    /**
     * @return The position
     */
    Vec2 getPosition();

    /**
     * @return The size of the body. Entity bodies are all approximated as
     *         rectangles.
     */
    Size2D getDimension();

    /**
     * @param velocity The velocity vector
     */
    void setLinearVelocity(Vec2 velocity);

    /**
     * @return The velocity vector
     */
    Vec2 getLinearVelocity();

    /**
     * @param impulse The impulse vector
     */
    void applyImpulse(Vec2 impulse);

    /**
     * @param force the force vector to be applied
     * @param point the point to move the entity.
     */
    void applyForce(Vec2 force, Vec2 point);

    /**
     * @param scale The scale factor
     */
    void setGravityScale(double scale);

    /**
     * @return true if the body is solid, rigid, cannot be passed through
     */
    boolean isSolid();

    /**
     * @param allowSleep Define is the entity can sleep and not updated by the
     *                   physic world
     */
    void setToSleep(boolean allowSleep);

    /**
     * @return true if the physic entity is actually sleeping
     */
    boolean isSleeping();

    /**
     * Remove the body from the physic world.
     * 
     * @param world
     */
    void removeBodyFromWorld(World world);
}
