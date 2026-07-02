package it.unibo.aknightstale.models.entity;

import it.unibo.aknightstale.utils.Borders;
import it.unibo.aknightstale.utils.Point2D;

public interface Entity {
    /**
     * Gets the entity position.
     * 
     * @return the entity position.
     */
    Point2D getPosition();

    /**
     * Sets the entity position.
     * 
     * @param p the new position.
     */
    void setPosition(Point2D p);

    /**
     * Gets the entity borders.
     * 
     * @return the entity borders.
     */
    Borders getBorders();

    /**
     * Sets the entity borders.
     * @param b The new borders of entity
     */
    void setBorders(Borders b);

    /**
     * Gets the entity type.
     * 
     * @return the entity type.
     */
    EntityType getType();

    /**
     * Checks if the entity can have collisions.
     * 
     * @return true if entity can have collisions, false otherwise.
     */
    Boolean isCollidable();
}
