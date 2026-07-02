package utils;

import org.jbox2d.common.Vec2;

import controller.entities.Platform;

/**
 * Utility class used to help the creation of an entity.
 */
public final class EntityCreationUtils {

    private EntityCreationUtils() {
    }

    /**
     * @param platform     the platform on which place the entity
     * @param entityWidth  the entity physic width dimension
     * @param entityHeight the entity physic height dimension
     * @return the right position at the center of the platform where to place the
     *         entity
     */
    public static Vec2 getPositionOnPlatform(final Platform platform, final int entityWidth, final int entityHeight) {
        final float pWidth = platform.getDimension().x / 2;
        final float eWidth = entityWidth / 2;
        return platform.getPhysicPosition().add(new Vec2((pWidth - eWidth), (float) -entityHeight));
    }

    /**
     * @param platform the platform on which to place the enemy
     * @return the platform width where the enemy can walk on
     */
    public static double calculateSurfaceToWalkOn(final Platform platform) {
        return platform.getDimension().x;
    }
}
