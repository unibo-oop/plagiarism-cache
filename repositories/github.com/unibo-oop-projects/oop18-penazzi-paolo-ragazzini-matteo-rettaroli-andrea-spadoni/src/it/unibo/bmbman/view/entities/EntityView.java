package it.unibo.bmbman.view.entities;

import java.awt.Graphics;
import java.awt.Image;

import it.unibo.bmbman.model.utilities.Dimension;
import it.unibo.bmbman.model.utilities.Direction;
import it.unibo.bmbman.model.utilities.EntityType;
import it.unibo.bmbman.model.utilities.Position;

/**
 * Interface to model the general view aspects of each entity in the game.
 *
 */
public interface EntityView {
    /**
     * Used to know which {@link EntityType} the entity is.
     * @return {@link EntityType}
     */
    EntityType getType();

   /**
     * Set the position of entity view.
     * @param position the point in the world
     */
    void setPosition(Position position);
    /**
     * Used to know where is the entity view.
     * @return the position
     */
    Position getPosition();
       /**
     * Set the dimension of entity view.
     * @param dimension the height and width of the entity view
     */
    void setDimension(Dimension dimension);
    /**
     * Used to know the width and height of the entityView.
     * @return  the dimension of entity view 
     */
    Dimension getDimension();
    /**
     * Used to change sprites according to direction.
     * @param direction the direction in which the entity is moving
     */
    void setDirection(Direction direction);
    /**
     * Used to know in which direction the entity is moving.
     * @return {@link Direction}
     */
    Direction getDirection();
    /**
     * the method to update the graphics of entity.
     * @param g {@link Graphics} to update
     */
    void render(Graphics g);
    /**
     * Used to remove the entity view.
     */
    void remove();
    /**
     * Used to get the image associated to the entity.
     * @return the sprite
     */
    Image getSprite();
    /**
     * Set the visibility of entity.
     * @param visible if true the entity view is visible
     */
    void setVisible(boolean visible);
    /**
     * Used to know if the entity is visible or not.
     * @return true if entity is visible, false otherwise
     */
    boolean isVisible();
}
