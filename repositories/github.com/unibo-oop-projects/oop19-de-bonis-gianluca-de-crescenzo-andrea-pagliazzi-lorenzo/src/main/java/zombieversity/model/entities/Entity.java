package zombieversity.model.entities;

import javafx.geometry.BoundingBox;
import javafx.geometry.Point2D;

/**
 * 
 * The interface to model general aspects of entities.
 *
 */
public interface Entity {

    /**
     * 
     * @return entity's position in the world.
     */
    Point2D getPosition();

    /**
     * Used to set entity's position.
     * 
     * @param position new entity's position
     */
    void setPosition(Point2D position);

    /**
     * 
     * @return entity's bounding box.
     */
    BoundingBox getBBox();

    /**
     * Used to set entity's bounding box.
     * 
     * @param w width of bounding box.
     * @param h height of bounding box.
     */
    void setBBox(double w, double h);

    /**
     * Getter for {@link EntityType}.
     * 
     * @return entity's type
     */
    EntityType getType();

    /**
     * 
     * @return width of bounding box.
     */
    double getWidth();

    /**
     * 
     * @return height of bounding box.
     */
    double getHeight();

}
