package it.unibo.pacman.controller.entities;

import java.util.Set;

import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.view.entities.MovableView;
/**
 * A simple BlinkyController create using Decorator pattern.
 */
public class BlinkyController extends GhostControllerDecorator {
    private final Movable entity;
    private final Set<EntityController> wall;
    /**
     * Construct a controller for Blinky ghost that decorate {@link GhostControllerDecorator}.
     * @param view the view of blinky.
     * @param entity the model of blinky.
     * @param wall a wall set for check the collision.
     */
    public BlinkyController(final MovableView view, final Movable entity, final Set<EntityController> wall) {
        super(new GhostControllerImpl(view, entity, wall));
        final BlinkyDirectionGenerator th = new BlinkyDirectionGenerator(this);
        this.entity = entity;
        this.wall = wall;
        th.startMyThread();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        if (super.getPreferredDirection().isPresent()) {
            final Direction dir = this.entity.getDirection();
            this.entity.setDirection(super.getPreferredDirection().get());
            if (!this.wall.stream()
                    .anyMatch(w -> w.getCollision(this.entity.getBoundsAt(this.entity.nextPosition())).isPresent())) {
                    this.entity.setPosition(this.entity.nextPosition());
            } else {
                this.entity.setDirection(dir);
                super.move();
            }
        } else {
        super.move();
        }
    }
    /**
     * Used to get the actual direction of the entity.
     * @return the actual direction.
     */
    public Direction getActualDirection() {
        return this.entity.getDirection();
    }
}
