package it.unibo.pacman.controller.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Optional;

import it.unibo.pacman.model.entities.Entity;
import it.unibo.pacman.model.utilities.EntityType;
/**
 * An abstract class which allows to decorate a generic MovableController in game.
 *
 */
public abstract class MovableControllerDecorator implements MovableController {
    private final MovableController decorated;
    /**
     * Create a generic {@link MovableControllerDecorator}.
     * @param decorated the controller to decorate.
     */
    protected MovableControllerDecorator(final MovableController decorated) {
        this.decorated = decorated;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        decorated.move();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void respawn() {
        decorated.respawn();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        decorated.updateView();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Entity> getCollision(final Rectangle collider) {
        return decorated.getCollision(collider);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void remove() {
        decorated.remove();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRemoved() {
        return decorated.isRemoved();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void render(final Graphics g) {
        decorated.render(g);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public EntityType getType() {
        return decorated.getType();
    }
}
