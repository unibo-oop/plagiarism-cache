package it.unibo.pacman.controller.entities;

import java.util.Optional;
import java.util.Set;

import it.unibo.pacman.controller.utilities.MyTimer;
import it.unibo.pacman.model.entities.Movable;
import it.unibo.pacman.model.utilities.Direction;
import it.unibo.pacman.view.entities.MovableView;

/**
 * 
 * An implementation of {@link GhostController}.
 *
 */
public class GhostControllerImpl extends MovableControllerDecorator implements GhostController {

    private final Movable entity;
    private final Set<EntityController> wallSet;
    private Optional<Direction> preferred = Optional.empty();
    private MyTimer th;
    private static final int MAXATTEMPTDIRECTION = 10;
    /**
     * Create a {@link GhostControllerImpl}.
     * @param view a view for {@link GhostControllerImpl}.
     * @param entity the model of the ghost.
     * @param wallSet a wall set for check the collision.
     */
    public GhostControllerImpl(final MovableView view, final Movable entity, final Set<EntityController> wallSet) {
        super(new SimpleMovableController(entity, view));
        this.entity = entity;
        this.wallSet = wallSet;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void move() {
        if (!this.wallSet.stream()
                        .anyMatch(w -> w.getCollision(this.entity.getBoundsAt(this.entity.nextPosition())).isPresent())) {
            this.entity.setPosition(this.entity.nextPosition());
        } else {
            this.generateDirection();
            this.entity.setPosition(this.entity.nextPosition());
        }
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void generateDirection() {
        int counter = 0;
        Direction preferred;
        final Direction dir = Direction.getOppositeDirection(this.entity.getDirection());
        do {
            if (counter == MAXATTEMPTDIRECTION) {
                break;
            }
            counter++;
            preferred = Direction.getRandomDirection();
            this.entity.setDirection(preferred);
        } while (this.wallSet.stream().anyMatch(w -> w.getCollision(this.entity.getBoundsAt(this.entity.nextPosition())).isPresent()) 
                || dir.equals(preferred));
        //now there's a if more because, if the ghost he gets stuck in a position, 
        //whose only exit is to go back, after a MAXATTEMPTDIRECTION times that he has tried 
        //to create a new direction, he understands that PROBABLY the only one is to go back.
        if (counter == MAXATTEMPTDIRECTION) {
            do { 
                counter = 0;
                preferred = Direction.getRandomDirection();
                this.entity.setDirection(preferred);
            } while (this.wallSet.stream().anyMatch(w -> w.getCollision(this.entity.getBoundsAt(this.entity.nextPosition())).isPresent()));
        } else {
            counter = 0;
        }

    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void fear() {
        if (this.th != null) {
        if (this.th.isRunning()) {
            this.th.stopMyTimer();
            this.th = new MyTimer(this.entity);
            } else {
                th = new MyTimer(entity);
            }
        } else {
            th = new MyTimer(entity);
            }
        this.th.startMyTimer();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setPreferredDirection(final Direction dir) {
        preferred = Optional.of(dir);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void unsetPreferredDirection() {
        preferred = Optional.empty();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Direction> getPreferredDirection() {
       return this.preferred;
    }
}
