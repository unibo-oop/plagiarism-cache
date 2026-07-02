package powpaw.map.model.api;

import javafx.geometry.Point2D;
import javafx.scene.shape.Shape;
import powpaw.player.model.api.Hitbox;

/**
 * Interface of TransitionImpl class for checking collision and implementing
 * transitions for a
 * game entity.
 * 
 * @author Giacomo Grassetti
 */
public interface Transition {

    /**
     * Method that checks collision and returns a new position if there is no
     * collision, otherwise
     * it returns the current position.
     * 
     * @param pos The current position of an object in a 2D coordinate system,
     *            represented by a Point2D
     *            object.
     * @return If there is no collision detected at the given position, returns a
     *         new
     *         position that is 2 units below the current position. If there is a
     *         collision, the method returns
     *         the current position without any changes.
     */
    Point2D fallTransition(Point2D pos);

    /**
     * This function checks if a given hitbox collides with any of the terrain
     * blocks in a map.
     * 
     * @param hitbox is Hitbox object representing the hitbox of a weapon in
     *               2D space.
     * 
     * @return True if the given position intersect the terrains, false otherwise
     */
    boolean checkPlayerCollisionByHitbox(Hitbox hitbox);

    /**
     * This function checks if a player is within the boundaries of a terrain block
     * in a game map.
     * 
     * @param hitbox A Shape object representing the hitbox of the player.
     * @return True if player intersect with terrains, false otherwise.
     */
    boolean checkPlayerInTerrain(Shape hitbox);
}
