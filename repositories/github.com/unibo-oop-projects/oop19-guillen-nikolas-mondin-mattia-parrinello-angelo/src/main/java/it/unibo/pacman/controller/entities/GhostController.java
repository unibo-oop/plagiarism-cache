package it.unibo.pacman.controller.entities;

import java.util.Optional;

import it.unibo.pacman.model.utilities.Direction;

/**
 * 
 * Interface that extends {@link MovableController}. This interface is a
 * controller for a single Ghost.
 *
 */
public interface GhostController extends MovableController {
    /**
     * Update the settings of a Ghost, in case is frightened.
     */
    void fear();
    /**
     * Generate random direction.
     */
    void generateDirection();
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
    /**
     * Used to get the preferred direction option.
     * @return the Direction preferred.
     */
    Optional<Direction> getPreferredDirection();
}
