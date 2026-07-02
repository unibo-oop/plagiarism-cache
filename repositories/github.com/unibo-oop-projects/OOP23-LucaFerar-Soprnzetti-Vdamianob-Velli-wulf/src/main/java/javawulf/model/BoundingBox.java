package javawulf.model;

import java.awt.Rectangle;

/**
 * BoundingBox is the area of collision an element of the game has
 * alongside its current type.
 */
public interface BoundingBox {

    /**
     * The type of collision of an element of the game.
     */
    enum CollisionType {
        /**
         * The type of collision Player normally has.
         */
        PLAYER,
        /**
         * The type of collision Enemy normally has.
         */
        ENEMY,
        /**
         * The type of collision Collectable normally has.
         */
        COLLECTABLE,
        /**
         * The type of collision Sword as it is active.
         */
        SWORD,
        /**
         * The type of collision an Entity has when it gets stunned.
         */
        STUNNED,
        /**
         * The type of collision a GameElement has when it is not active.
         */
        INACTIVE;
    }

    /**
     * Checks whether a GameElement is colliding with another.
     * 
     * @param box The collsionArea of the other element
     * @return true if it does collide with box, false if it doesn't
     */
    boolean isCollidingWith(Rectangle box);

    /**
     * @return The collision area of the GameElement
     */
    Rectangle getCollisionArea();

    /**
     * @param x The central position on the x-axis of the GameElement
     * @param y The central position on the y-axis of the GameElement
     * @param width The width of the area the element must have
     * @param height The height of the area the element must have
     */
    void setCollisionArea(int x, int y, int width, int height);

    /**
     * @param area The new area of the CollisionArea.
     */
    void setCollisionArea(Rectangle area);

    /**
     * @return The current collisionType of the game element
     */
    CollisionType getCollisionType();

    /**
     * @param type The type of collision the element must have
     */
    void changeCollisionType(CollisionType type);
}
