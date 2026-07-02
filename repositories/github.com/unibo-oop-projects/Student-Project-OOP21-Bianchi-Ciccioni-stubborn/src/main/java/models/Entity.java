package models;

/**
 * The Entity interface gives contracts to generate different types of entities.
 * This follows Strategy Pattern to give the program more extensibility and removing redundant usage of code.
 */
public interface Entity {

    /**
     * Get the current position of an entity.
     * 
     * @return the current position of an entity
     */
    Point2D getPosition();

    /**
     * Set a new position for an entity.
     * 
     * @param position a new position for an entity
     */
    void setPosition(Point2D position);
}