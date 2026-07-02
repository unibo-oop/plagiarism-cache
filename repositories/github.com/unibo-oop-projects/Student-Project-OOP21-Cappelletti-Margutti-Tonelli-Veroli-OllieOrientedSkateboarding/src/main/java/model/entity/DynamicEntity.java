package model.entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import model.Model;

/**
 * 
 * Interface that describe an entity moving on the screen.
 *
 */
public interface DynamicEntity {

    /**
     * Update the entity position on the screen.
     * @param speedX entity's speedX.
     * */
    void updatePosition(double speedX);

    /**
     * Returns the entity Bounding Box.
     * @return a new Rectangle2D representing the coordinates and the dimension of the entity.
     */
    Rectangle2D getBounds();

    /**
     * Check if the entity is out of the game screen.
     * @return true if the entity is out of the screen, false otherwise.
     */
    boolean isOutofScreen();

    /**
     * Get the image that identifies the entity.
     * @return the image corresponding to the entity.
     */
    Image getImage();

    /**
     * Get the level on which the entity spawn.
     * @return the level on which the entity spawn. 
     */
    SpawnLevel getLevel();

    /**
     * Get the type that identifies the entity.
     * @return the type identifying the entity.
     */
    EntityType getType();

    /**
     * Get the distance after that next entity should spawn.
     * @return the distance after that next entity should spawn.
     */
    double getDistance();

    /**
     * Set the hit field.
     * @param hit true if the entity collided with the player, false otherwise.
     */
    void hit(boolean hit);

    /**
     * Return state of the hit field.
     * @return true if the entity collided with the player, false otherwise.
     */
    boolean wasHit();

    /**
     * Method called when an entity collide with the player.
     * @param model the actual state of the game.
     */
    void onCollision(Model model);

}
