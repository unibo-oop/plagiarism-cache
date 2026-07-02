package tmw.controller.entities;

import java.util.Optional;

import tmw.common.Dim2D;
import tmw.common.EntityDirection;
import tmw.common.P2d;
import tmw.common.V2d;
import tmw.controller.world.WorldController;
import tmw.model.entities.Enemy;

/**
 * This abstract class is the controller for an enemy and already implements
 * those which are the same for every enemy controller; the methods left to
 * implements are the one that determinate the movement direction of the enemy
 * and those relative to the attack action of the enemy.
 *
 * @param <T> - The entity class that this controller can handle. It must
 *        extends Enemy.
 */
public abstract class AbstractEnemyController<T extends Enemy> implements EntityController<T> {

    private final WorldController worldController;
    private final T enemy;
    private V2d movementDirection;
    private final IntersectChecker checker;

    private static final int GOING_DOWN = 0;
    private static final int GOING_LEFT = 1;
    private static final int GOING_UP = 2;
    private static final int GOING_RIGHT = 3;

    /**
     * Construct an enemy controller.
     * 
     * @param worldController - The WorldController that is used by the controller
     *                        to communicate with the rest of the game
     * @param enemy           - The enemy that this controller has to handle
     */
    public AbstractEnemyController(final WorldController worldController, final T enemy) {
        this.worldController = worldController;
        this.enemy = enemy;
        this.checker = new IntersectChecker(this.enemy.getCurrentPos(), this.enemy.getDimension());
        this.movementDirection = new V2d(0, 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
        if (this.enemy.isAlive()) {
            this.movementDirection = this.getNewMovementDirection().getNormalized();
            this.enemy.setVel(this.movementDirection);
            final P2d newPosition = this.enemy.getCurrentPos()
                    .sum(this.enemy.getCurrentVel().mul(this.enemy.getSpeed()));

            final Optional<P2d> updatePosition = this.checker.isPositionClear(newPosition, this.worldController) ? Optional.of(newPosition)
                    : Optional.empty();

            this.enemy.update(updatePosition);

            if (this.enemy.intersect(this.getWorldController().getPlayer().getEntity())) {
                this.getWorldController().getPlayer().getEntity().takeDamage(this.enemy.getContactDamage());
            }

            if (this.readyToAttack()) {
                this.attack();
            }

        } else {
            this.delete();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        final double vx = this.movementDirection.getX();
        final double vy = this.movementDirection.getY();
        int dir = 0;

        if (vx > 0) {
            if (vx >= Math.abs(vy)) {
                dir = GOING_RIGHT;
            } else if (vy > 0) {
                dir = GOING_DOWN;
            } else {
                dir = GOING_UP;
            }
        } else {
            if (Math.abs(vx) > Math.abs(vy)) {
                dir = GOING_LEFT;
            } else if (vy >= 0) {
                dir = GOING_DOWN;
            } else {
                dir = GOING_UP;
            }
        }

        this.getWorldController().getView().render(this.enemy, EntityDirection.getDirection(dir),
                this.enemy.getBoundary());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getEntity() {
        return this.enemy;
    }

    /**
     * @return the worldController
     */
    public WorldController getWorldController() {
        return worldController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete() {
        this.getWorldController().removeEntityController(this);
        this.getWorldController().incrementScore(this.enemy.getScore());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resizeEntity(final Dim2D dimension) {
        this.getEntity().resetDefaultDimension(dimension);
        this.checker.resetDefaultDimension(this.getEntity().getDimension());
    }

    /**
     * This method has to be implemented and it's used to understand if the enemy is
     * ready to attack.
     * 
     * @return true if the enemy is ready to perform an attack action, false
     *         otherwise
     */
    protected abstract boolean readyToAttack();

    /**
     * This method is used to perform the attack action.
     */
    protected abstract void attack();

    /**
     * This method is used the movement direction of the entity.
     * 
     * @return the new movement direction as a {@link V2d}
     */
    protected abstract V2d getNewMovementDirection();

}
