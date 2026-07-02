package model.physics;

import org.jbox2d.common.Vec2;

/**
 * Builder for {@link IPhysicEntity}.
 */
public interface BodyBuilder {
    /**
     * @param position The position of the center of the body
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder position(Vec2 position);

    /**
     * @param size Size of the body bounding box.
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder size(Size2D size);

    /**
     * A body not subject to forces will not respond to forces nor impulses and
     * won't be pushed by other bodies. This includes the normal force so the body
     * will be able to pass through others and it's especially true for collisions
     * with other bodies of the same type and / or unmoveable ones.
     * 
     * Defaults to true.
     * 
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder subjectToForces(boolean isSubjectToForces);

    /**
     * Defaults to true.
     * 
     * @param isSolid A non-solid body can pass through other bodies and vice-versa.
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder solid(boolean isSolid);

    /**
     * Defaults to true.
     * 
     * @param moveable If false the body won't move from its position.
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder moveable(boolean moveable);

    /**
     * @param friction The friction applied during contact with other bodies.
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder friction(float friction);

    /**
     * @param density the body density to be applied
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder density(float density);

    /**
     * @param restitution the restitution value
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder restitution(float restitution);

    /**
     * @param gravity the gravity scale to apply to the body
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder gravity(float gravity);

    /**
     * @param allowedToSleep define if the physic entity is allowed to sleep
     * @return The {@link BodyBuilder} instance.
     */
    BodyBuilder allowedToSleep(boolean allowedToSleep);

    /**
     * @return the built physic entity
     */
    PhysicEntity build();

}