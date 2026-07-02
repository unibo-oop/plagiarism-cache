package zombieversity.model.collisions;

import javafx.geometry.BoundingBox;

/**
 * 
 * Used to manage collision.
 *
 */
public final class CollisionsUtils {

    private CollisionsUtils() {

    }

    /**
     * Checks if two entities are colliding.
     * @param bb1 BoundingBox of first entity.
     * @param bb2 BoundingBox of second entity.
     * @return true if entity are colliding, false otherwise.
     */
    public static boolean isColliding(final BoundingBox bb1, final BoundingBox bb2) {
        return bb1.intersects(bb2);
    }
}
