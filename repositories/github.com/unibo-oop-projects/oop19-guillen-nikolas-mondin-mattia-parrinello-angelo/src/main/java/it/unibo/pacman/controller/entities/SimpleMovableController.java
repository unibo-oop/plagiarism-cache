package it.unibo.pacman.controller.entities;

import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.utilities.Position;
import it.unibo.pacman.view.entities.MovableView;
/**
 * The implementation of {@link MovableController}.
 */
public class SimpleMovableController extends SimpleEntityController implements MovableController {
    private final Position spawn;
    private final Movable movable;
    private final MovableView view;
    /**
     * Construct an implementation of {@link MovableController}.
     * 
     * @param entity entity to handle
     * @param ev     its view
     */
    public SimpleMovableController(final Movable entity, final MovableView ev) {
        super(entity, ev);
        this.movable = entity;
        this.view = ev;
        this.spawn = entity.getPosition();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        if (!this.isRemoved()) {
            movable.setPosition(movable.nextPosition());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void respawn() {
        if (!this.isRemoved()) {
            movable.setPosition(spawn);
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {
        super.updateView();
        view.updateDirection(movable.getDirection());
        view.updateStatus(movable.getStatus());
    }
}
