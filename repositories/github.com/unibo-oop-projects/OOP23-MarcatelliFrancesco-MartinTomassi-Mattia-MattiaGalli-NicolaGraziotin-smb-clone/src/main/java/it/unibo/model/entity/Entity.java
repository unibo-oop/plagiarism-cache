package it.unibo.model.entity;

/**
 * Represents an entity in a game or simulation context.
 *
 * @param <H> The type parameter specifying the type of hitbox associated with this entity.
 *            This allows for flexibility in defining various hitbox types.
 */
public interface Entity<H> {

    /**
     * Returns the X coordinate of the entity.
     * 
     * @return The X coordinate of the entity.
     */
    double getX();

    /**
     * Returns the Y coordinate of the entity.
     * 
     * @return The Y coordinate of the entity.
     */
    double getY();

    /**
     * Returns the hitbox of the entity.
     * 
     * @return The hitbox of the entity.
     */
    H getHitbox();

    /**
     * Sets the X coordinate of the entity.
     * 
     * @param x The new X coordinate.
     */
    void setX(double x);

    /**
     * Sets the Y coordinate of the entity.
     * 
     * @param y The new Y coordinate.
     */
    void setY(double y);
}
