package it.unibo.pacman.controller.entities;

import java.util.Optional;

import it.unibo.pacman.model.utilities.Direction;
/**
 * An abstract class which allows to decorate a Ghost.
 */
public abstract class GhostControllerDecorator extends MovableControllerDecorator implements GhostController {
    private final GhostController decorated;
    /**
     * Create a {@link GhostControllerDecorator}.
     * @param decorated the Ghost to decorated.
     */
    protected GhostControllerDecorator(final GhostController decorated) {
        super(decorated);
        this.decorated = decorated;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void fear() {
        decorated.fear();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void generateDirection() {
        decorated.generateDirection();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPreferredDirection(final Direction dir) {
        decorated.setPreferredDirection(dir);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void unsetPreferredDirection() {
        decorated.unsetPreferredDirection();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Direction> getPreferredDirection() {
        return decorated.getPreferredDirection();
    }
}
