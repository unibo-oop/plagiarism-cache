package zombieversity.model.world;

import javafx.geometry.Point2D;
import zombieversity.model.entities.Entity;
/**
 * Camera interface representing user visible region of world.
 *
 */
public interface Camera {

    /**
     * Method used to update the values saved in camera, it requires a Point2D.
     * @param step - it represents the value to be added at the previous.
     */
    void move(Point2D step);
    /**
     * Method used to update the values saved in camera, requires the x - y offset for the new value.
     * @param x - offset it will be added to the previous value.
     * @param y - offset it will be added to the previous value.
     */
    void move(double x, double y);
    /**
     * Method used to get the point relative to the center of the camera.
     * @return Point2D 
     */
    Point2D getCenter();
    /**
     * Method to replace values of the camera, with new ones having in the center the position of the entity.
     * @param e - entity that i want to have in the center.
     */
    void centerOnEntity(Entity e);
    /**
     * Method to replace values of the camera, given X - Y it will represent the new center of the camera, if possible.
     * @param x - axis of camera ( external value are not be accepted).
     * @param y - axis of camera ( external value are not be accepted).
     */
    void centerOn(double x, double y);
    /**
     * Represents the offset of Y - axis used to translate from absolute to relative the axis and viceversa.
     * @return double - given current Y - offset of the camera.
     */
    double getOffsetY();
    /**
     * Represents the offset of X - axis used to translate from absolute to relative the axis and viceversa.
     * @return double - given current X - offset of the camera.
     */
    double getOffsetX();
    /**
     * Represent the vector (x, y) representing the camera offset used to translate from absolute to relative and viceversa.
     * @return double
     */
    Point2D getOffset();
    /**
     * This method can be used for a resizing of the camera, means set a k - value as multiplicator 
     * for the offset.
     * @param scale - (1x , 1.5x , 2x) 
     */
    void setScale(double scale);
    /**
     * Method to update camera size.
     * @param w - new width value
     * @param h - new height value
     */
    void resize(double w, double h);
    /**
     * Method to get the center of the camera as Point2D.
     * @return Point2D
     */
    Point2D midpoint();
    /**
     * Method to get the beginning region of the camera.
     * @return Point2D
     */
    Point2D start();
    /**
     * Method to get the ending region of the camera.
     * @return Point2D
     */
    Point2D end();
}
