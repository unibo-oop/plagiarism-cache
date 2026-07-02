package it.unibo.aknightstale.controllers.entity;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.aknightstale.models.entity.Character;
import it.unibo.aknightstale.models.entity.Direction;
import it.unibo.aknightstale.utils.Borders;
import it.unibo.aknightstale.utils.BordersImpl;
import it.unibo.aknightstale.utils.EntityManager;
import it.unibo.aknightstale.views.entity.AnimatedEntityView;
import it.unibo.aknightstale.views.entity.Status;

public abstract class AbstractController<M extends Character, V extends AnimatedEntityView>
        extends EntityControllerImpl<M, V> implements CharacterController<M, V> {

    /**
     * The entity manager.
     */
    private final EntityManager manager;

    @SuppressFBWarnings("EI_EXPOSE_REP2")       //must return a reference because it can be modified later
    public AbstractController(final M model, final V view, final EntityManager manager) {
        super(model, view);
        this.manager = manager;
    }

    /**
     * Updates the direction and the view of entity.
     * 
     * @param dir the entity direction.
     */
    protected void move(final Direction dir) {
        super.getModel().setDirection(dir);
        super.getView().setStatus(Status.WALK);
        super.getView().update(super.getModel().getDirection());
    }

    private boolean canMove(final Direction d) {
        return this.manager.getCollisionManager().checkDirections(this).contains(d);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveRight() {
        move(Direction.RIGHT);
        if (canMove(Direction.RIGHT)) {
            super.getModel().goRight();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveLeft() {
        move(Direction.LEFT);
        if (canMove(Direction.LEFT)) {
            super.getModel().goLeft();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveUp() {
        move(Direction.UP);
        if (canMove(Direction.UP)) {
            super.getModel().goUp();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveDown() {
        move(Direction.DOWN);
        if (canMove(Direction.DOWN)) {
            super.getModel().goDown();
        }
    }
    private Borders createBorders() {
        final double attackRange = super.getModel().getAttackRange();
        final Borders borders;
        switch (super.getModel().getDirection()) {
        case RIGHT:
            borders = new BordersImpl(super.getModel().getPosition().getX(), super.getModel().getPosition().getY(),
                    super.getModel().getBorders().getWidth() + attackRange, super.getModel().getBorders().getHeight());
            break;
        case LEFT:
            borders = new BordersImpl(super.getModel().getPosition().getX() - attackRange,
                    super.getModel().getPosition().getY(), attackRange, super.getModel().getBorders().getHeight());
            break;
        case DOWN:
            borders = new BordersImpl(super.getModel().getPosition().getX(), super.getModel().getPosition().getY(),
                    super.getModel().getBorders().getWidth(), super.getModel().getBorders().getHeight() + attackRange);
            break;
        case UP:
            borders = new BordersImpl(super.getModel().getPosition().getX(),
                    super.getModel().getPosition().getY() - attackRange, super.getModel().getBorders().getWidth(),
                    attackRange);
            break;
        default:
            borders = super.getModel().getBorders();
            break;
        }
        return borders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void attack() {
        final var borders = this.createBorders();
        this.getManager().getEntities().stream()
                .filter(ec -> borders.intersects(ec.getModel().getBorders())
                              && ec.getModel() instanceof Character)
                .forEach(ec -> {
                    if (!ec.getModel().getType().equals(super.getModel().getType())) {
                        final var model = (Character) ec.getModel();
                        super.getModel().attack(model);
                    }
                });
        super.getView().setStatus(Status.ATTACK);
        super.getView().update(super.getModel().getDirection());
    }

    /**
     * Get the entity manager.
     * 
     * @return  The entity manager.
     */
    @SuppressFBWarnings("EI_EXPOSE_REP")        //must return a reference because it will be modified
    public EntityManager getManager() {
        return manager;
    }



}


