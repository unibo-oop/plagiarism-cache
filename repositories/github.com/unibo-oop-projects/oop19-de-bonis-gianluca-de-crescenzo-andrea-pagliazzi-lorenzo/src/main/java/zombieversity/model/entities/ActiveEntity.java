package zombieversity.model.entities;

import javafx.geometry.Point2D;

/**
 * 
 * Used to manage an entity with velocity and direction.
 *
 */
public interface ActiveEntity extends Entity {

    /**
     * Used to set entity's velocity.
     * 
     * @param vel new entity's velocity.
     */
    void setVelocity(Point2D vel);

    /**
     * 
     * @return entity's velocity.
     */
    Point2D getVelocity();

    /**
     * Used to update entity's position.
     */
    void update();

    /**
     * 
     * @return entity's face direction.
     */
    double getDirection();

    /**
     * Used to set entity's face direction.
     * @param angle
     *          the rotatin of entity's face.
     */
    void setDirection(double angle);
}
