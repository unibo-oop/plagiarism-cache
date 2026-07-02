package it.unibo.pacman.view.entities;

import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.model.utilities.Status;
/**
 * An interface which represents the view for a {@link Movable} entity.
 */
public interface MovableView extends EntityView {
    /**
     * Used to update the position of the entity in the graphic view.
     * 
     * @param status the new status of entity.
     */
    void updateStatus(Status status);
    /**
     * Used to update the direction of the entity in the graphic view.
     * 
     * @param newDir the new direction of entity.
     */
    void updateDirection(Direction newDir);
}
