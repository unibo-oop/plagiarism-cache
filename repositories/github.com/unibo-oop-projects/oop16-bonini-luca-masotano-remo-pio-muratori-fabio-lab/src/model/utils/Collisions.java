package model.utils;

import java.util.ArrayList;
import java.util.Collection;

import model.entities.Movable;
import model.hitbox.HitboxCircle;
import model.hitbox.HitboxRectangle;
import model.map.StandardRoom;

/**
 * 
 * An utility class to handle collisions.
 *
 */
public final class Collisions {

    private Collisions() {

    }

    /**
     * Check the if an HitboxCircle has collided with the walls.
     * 
     * @param c
     *            The Hitbox of the entity.
     * @return A boolean. True if the the HitboxCircle has collided with the
     *         walls, false otherwise.
     */
    public static boolean checkBoundaryCollision(final HitboxCircle c) {
        final HitboxRectangle room = StandardRoom.getRoomSpace();
        boolean result = false;

        // North
        if (c.getY() + c.getRadius() > room.getX() + room.getHeight()) {
            result = true;
            c.changePosition(c.getX(), room.getX() + room.getHeight() - c.getRadius());
        }

        if (c.getY() - c.getRadius() < room.getY()) {
            result = true;
            c.changePosition(c.getX(), room.getY() + c.getRadius());
        }

        if (c.getX() + c.getRadius() > room.getX() + room.getWidth()) {
            result = true;
            c.changePosition(room.getX() + room.getWidth() - c.getRadius(), c.getY());
        }

        if (c.getX() - c.getRadius() < room.getX()) {
            result = true;
            c.changePosition(room.getX() + c.getRadius(), c.getY());
        }

        return result;
    }

    /**
     * Check if the second Movable provided has collided with the first Mpvable provided as parameter.
     * @param a
     *            The first Movable entity.
     * @param b
     *            The second Movable entity to check if it has collided with the first.
     * @return A collection of directions blocked by collisions with other
     *         entities.
     */
    public static Collection<Direction> entityCollision(final Movable a, final Movable b) {
        return entityCollision(a.getHitbox(), b.getHitbox());
    }

    /**
     * Check if the second HitboxCircle provided has collided with the first HitboxCircle provided as parameter.
     * @param c1
     *            The first HitboxCircle.
     * @param c2
     *            The second HitboxCircle.
     * @return A collection of directions blocked by collisions with other
     *         entities.
     */
    public static Collection<Direction> entityCollision(final HitboxCircle c1, final HitboxCircle c2) {
        final Collection<Direction> c = new ArrayList<>();

        final double dx = c2.getX() - c1.getX();
        final double dy = c2.getY() - c1.getY();
        final double distance = Math.sqrt(dx * dx + dy * dy);

        if (distance < c1.getRadius() + c2.getRadius()) {
            if (dx > 0) {
                c.add(Direction.LEFT);
            } else {
                c.add(Direction.RIGHT);
            }

            if (dy < 0) {
                c.add(Direction.DOWN);
            } else {
                c.add(Direction.UP);
            }
        }
        return c;
    }
}
