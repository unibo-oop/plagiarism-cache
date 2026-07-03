package view.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Shape;
import model.entities.properties.Position;

/**
 * Represent the entity's information necessary to draw it in the view.
 */
public interface ViewEntity {

    /**
     * 
     * @return entity's position
     */
    Position getPosition();

    /**
     * 
     * @return the entity's shape
     */
    Shape getShape();

    /**
     * 
     * @return the entity's picture
     */
    Image getPicture();
}
