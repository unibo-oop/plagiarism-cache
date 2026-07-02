package it.unibo.aknightstale.views.entity;

import it.unibo.aknightstale.models.entity.Direction;
import javafx.scene.canvas.GraphicsContext;

public interface AnimatedEntityView extends EntityView {
    /**
     * Sets the entity status.
     *
     * @param s the new status.
     */
    void setStatus(Status s);

    /**
     * Draws the entity health bar on JavaFX Canvas.
     *
     * @param gc        the JavaFX Canvas.
     * @param x         the x coordinate of the top left corner of the health bar.
     * @param y         the y coordinate of the top left corner of the health bar.
     * @param health    the current health of the entity.
     * @param maxHealth the maximum health of the entity.
     */
    void drawHealthBar(GraphicsContext gc, double x, double y, double health, double maxHealth);

    /**
     * updates the image of the entity according to the direction in which it moves.
     *
     * @param d the direction.
     */
    void update(Direction d);

}
