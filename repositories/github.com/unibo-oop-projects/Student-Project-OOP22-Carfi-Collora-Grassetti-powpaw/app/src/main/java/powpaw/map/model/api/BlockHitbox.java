package powpaw.map.model.api;

import javafx.scene.shape.Shape;

/**
 * Interface for BlockHitboxImpl representing the hitbox entity of a Block.
 * 
 * @author Giacomo Grassetti
 */

public interface BlockHitbox {

    /**
     * Getter for the hitbox shape.
     * 
     * @return Shape of the hitbox.
     */
    Shape getShape();

    /**
     * This function checks if the hitbox of one shape intersects with the hitbox of
     * another shape.
     * 
     * @param otherHitbox Shape representing the hitbox of
     *                    another object that we want to check for collision with
     *                    the hitbox of the current hitbox.
     * @return True if hitbox intersect the other hitbox, false otherwise.
     */
    boolean checkCollision(Shape otherHitbox);

}
