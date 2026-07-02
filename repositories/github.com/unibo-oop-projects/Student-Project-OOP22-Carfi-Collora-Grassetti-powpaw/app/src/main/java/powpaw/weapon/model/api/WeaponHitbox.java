package powpaw.weapon.model.api;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;

/**
 * Interface for WeaponHitboxImpl class that implements the WeaponHitbox
 * interface and provides methods for
 * creating
 * and updating a rectangular hitbox for a weapon in a game, as well as checking
 * for collisions with
 * other hitboxes.
 * 
 * @author Giacomo Grassetti
 */
public interface WeaponHitbox {

    /**
     * Getter of the weapon hitbox shape .
     * 
     * @return The Shape of the weapon hitbox.
     */
    Shape getShape();

    /**
     * Method that updates the position of a shape to a new center point.
     * 
     * @param position A Point2D representing the new center position of the shape
     */
    void updateCenter(Point2D position);

    /**
     * Method that checks if the hitbox of one shape intersects with the weapon
     * hitbox.
     * 
     * @param otherHitbox Shape representing the hitbox of
     *                    another object to check for collision.
     * @return True if hitbox of the current shape intersects with the hitbox of the
     *         other shape passed.
     */
    boolean checkCollision(Shape otherHitbox);

}
