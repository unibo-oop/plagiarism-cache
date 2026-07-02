package it.unibo.pacman.controller.entities;

import it.unibo.pacman.model.utilities.Direction;
/**
 * Interface that extends {@link MovableController}. This interface is a
 * controller for a single Pacman.
 */
public interface PacmanController extends MovableController {
    /**
     * Used to resolve the collisions with pills.
     */
    void eatPill();
    /**
     * Used to resolve the collisions with power pills.
     */
    void eatPowerPill();
    /**
     * Used to resolve the collisions with ghost.
     */
    void eatGhost();
    /**
     * Used before the method move {@link MovableController} it makes the method move 
     * try to move in the preferred direction if possible.
     * @param dir the preferred {@link Direction}
     */
    void setPreferredDirection(Direction dir);
    /**
     * Used to unset the preferred direction option.
     */
    void unsetPreferredDirection();
}
