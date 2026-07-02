package it.unibo.pacman.view.entities;

import java.awt.Graphics;

import it.unibo.pacman.model.utilities.EntityType;
import it.unibo.pacman.model.utilities.Position;
/**
 * An interface which represents a view for an {@link Entity}.
 */
public interface EntityView {
    /**
     * Used to get the type of the entity that needs this view.
     * 
     * @return the type
     */
    EntityType getType();
    /**
     * Used to render the entity.
     * 
     * @param g the graphics where the method draws
     */
    void render(Graphics g);
    /**
     * Used to update the position of the entity in the graphic view, but you have
     * to call the method render {@link render} to visualize the change graphically.
     * 
     * @param position the position of the entity
     */
    void updatePosition(Position position);
}
