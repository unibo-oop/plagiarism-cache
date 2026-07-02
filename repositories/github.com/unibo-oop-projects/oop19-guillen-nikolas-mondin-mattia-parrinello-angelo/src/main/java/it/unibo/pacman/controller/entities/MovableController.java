package it.unibo.pacman.controller.entities;

/**
 * This is a specialization of {@link EntityController} that allows to manage a
 * {@link Movable} easily.
 */
public interface MovableController extends EntityController {
    /**
     * Used to perform a single movement of the Movable.
     */
    void move();
    /**
     * Used to spawn the Movable in his very first position.
     */
    void respawn();
}
