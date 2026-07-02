package zombieversity.view.entities;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import zombieversity.model.entities.EntityType;

/**
 * Interface to model view aspects of player view.
 *
 */
public interface PlayerView {
    /**
     * Used to know which {@link EntityType} the entity is.
     * 
     * @return {@link EntityType}
     */
    EntityType getType();

    /**
     * Set the position of entity view.
     * 
     * @param position the point in the world
     */
    void setPosition(Point2D position);

    /**
     * Used to know where is the entity view.
     * 
     * @return the position
     */
    Point2D getPosition();

    /**
     * Used to change sprites according to direction.
     * 
     * @param direction the direction in which the entity is moving
     */
    void setDirection(double direction);

    /**
     * Used to know in which direction the entity is moving.
     * 
     * @return {@link Direction}
     */
    double getDirection();

    /**
     * the method to update the graphics of entity.
     * 
     */
    void render();

    /**
     * Used to get the image associated to the entity.
     * 
     * @return the imageView.
     */
    ImageView getImageView();

    /**
     * 
     * @return the image.
     */
    Image getImage();
}
