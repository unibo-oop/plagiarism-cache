package model.entities;

import javafx.scene.shape.Shape;
import model.entities.properties.Position;
import model.entities.properties.Velocity;

/**
 * An entity is a component of the game.
 */
public interface Entity {

    /**
     * 
     * @return entity's position
     */
    Position getPosition();

    /**
     * 
     * @param position
     *            the new entity's position.
     */
    void setPosition(Position position);

    /**
     * 
     * @return entity's velocity
     */
    Velocity getVelocity();

    /**
     * 
     * @return entity's shape
     */
    Shape getShape();

    /**
     * 
     * @param velocity
     *            to set
     */
    void setVelocity(Velocity velocity);

    /**
     * 
     * @param shape
     *            to set
     */
    void setShape(Shape shape);

    /**
     * Update entity's status.
     * 
     * @param time
     *            time elapsed from last update
     */
    void update(int time);

}
