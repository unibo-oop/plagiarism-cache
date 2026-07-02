package powpaw.player.model.api;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * This interface represents a hitbox for a player object.
 * 
 * @author Alessia Carfì, Giacomo Grassetti
 */
public interface Hitbox {

    /**
     * Returns the radius of the hitbox.
     * 
     * @return the radius of the hitbox.
     */
    double getRadius();

    /**
     * Returns the center point of the hitbox.
     * 
     * @return the center point of the hitbox.
     */
    Point2D getCenter();

    /**
     * Returns the shape of the hitbox.
     * 
     * @return the shape of the hitbox.
     */
    Shape getShape();

    /**
     * Sets the horizontal offset of the hitbox.
     * 
     * @param width value representing the width of the player object.
     */
    void setOffsetX(double width);

    /**
     * Sets the vertical offset of the hitbox.
     * 
     * @param height value representing the height of the player object.
     */
    void setOffsetY(double height);

    /**
     * Returns the horizontal offset of the hitbox.
     * 
     * @return the horizontal offset of the hitbox.
     */
    double getOffsetX();

    /**
     * Returns the vertical offset of the hitbox.
     * 
     * @return the vertical offset of the hitbox.
     */
    double getOffsetY();

    /**
     * Updates the center position of the hitbox based on the player
     * position.
     * 
     * @param position Point2D representing the position of the player.
     */
    void updateCenter(Point2D position);

    /**
     * Checks if the hitbox collides with another shape.
     * 
     * @param otherHitbox Shape representing the other hitbox to check collision
     * @return boolean value indicating whether a collision occurred.
     */
    boolean checkCollision(Shape otherHitbox);

    /**
     * Returns the shape of the feet hitbox.
     * 
     * @return the shape of the feet hitbox.
     */
    Shape getFeetShape();

    /**
     * Getter for the hitbox shape of an arm as a rectangle.
     * 
     * @return The shape of ArmHitbox.
     */
    Rectangle getArmShape();
}
