package model.entities;

import java.util.Objects;

import controller.gameloop.GameController;
import model.Model;
import model.entitiesutil.BubbleState;
import model.entitiesutil.EnemyState;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.entitiesutil.MovementValue;
import model.physics.BubbleMovement;
import model.physics.CollisionDirection;
import model.physics.MovementManager;

/**
 * Object thrown by the {@link Hero} against {@link Enemy}s.
 */
public class Bubble extends EntityMovable {
    private static final int SCORE = 5;
    private static final int HORIZONTALMOVEMENT_FACTOR = 4; // * this width
    private static final int FLOATING_TICKS = 5 * GameController.FPS; // Duration (s) * FPS
    private final MovementManager movementManager;
    private BubbleState state;
    private int auxTickContainer;

    /**
     * Object thrown by the {@link Hero} against {@link Enemy}s.
     * 
     * @param model is the {@link Model}
     * @param x is the left x coordinate
     * @param y is the top y coordinate
     * @param width of the entity
     * @param height if the entity
     * @param direction of the entity
     */
    public Bubble(final Model model, final int x, final int y, final int width, final int height,
            final EntityDirection direction) {
        super(Objects.requireNonNull(model), x, y, width, height, Objects.requireNonNull(direction));
        this.movementManager = new BubbleMovement(this.body);
        this.auxTickContainer = HORIZONTALMOVEMENT_FACTOR * width / Math.abs(
                (direction == EntityDirection.LEFT ? MovementValue.BUBBLE_MOVE_LEFT : MovementValue.BUBBLE_MOVE_RIGHT)
                        .getVelocityX());
        this.state = BubbleState.MOVING_HORIZONTALLY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isTouchedBy(final Entity entity, final CollisionDirection collisionDirection) {
        if (entity.getType().equals("Enemy") && this.state == BubbleState.MOVING_HORIZONTALLY && ((Enemy) entity).getState() == EnemyState.NORMAL) {
            // Touch an enemy: burst
            this.hide();
        } else if (entity.getType().equals("Wall")
                && (this.getDirection() == EntityDirection.LEFT || this.getDirection() == EntityDirection.RIGHT)
                && (collisionDirection == CollisionDirection.LEFT || collisionDirection == CollisionDirection.RIGHT)) {
            // Touch a wall horizontally while going left or right: burst
            this.hide();
            this.getModel().addPoints(SCORE);
        } else if (this.state != BubbleState.FLOATING && entity.getType().equals("Wall")
                && (this.getDirection() == EntityDirection.UP_LEFT
                        || this.getDirection() == EntityDirection.UP_RIGHT)) {
            // Touch a wall while going up: stop and float
            this.state = BubbleState.FLOATING;
            // And after FLOATING_TICKS ticks: burst
            this.auxTickContainer = FLOATING_TICKS;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStep() {
        if (this.state == BubbleState.FLOATING) {
            if (auxTickContainer > 0) {
                this.movementManager.floatUpDown();
                auxTickContainer--;
                if (auxTickContainer == 0) {
                    this.hide();
                }
                this.getModel().getLevel().addToRecheckEntity(this);
            }
        } else {
            if (this.getDirection() == EntityDirection.UP_LEFT || this.getDirection() == EntityDirection.UP_RIGHT) {
                this.movementManager.bubbleMoveUp();
                this.getModel().getLevel().addToRecheckEntity(this);
            } else if (auxTickContainer > 0) {
                this.moveHorizontally();
                auxTickContainer--;
                if (auxTickContainer == 0) {
                    this.setDirection(this.getDirection() == EntityDirection.LEFT ? EntityDirection.UP_LEFT
                            : EntityDirection.UP_RIGHT);
                    this.state = BubbleState.MOVING_VERTICALLY;
                }
                this.getModel().getLevel().addToRecheckEntity(this);
            }
        }
    }

    /**
     * @return the {@link BubbleState} of the {@link Bubble} (MOVING_HORIZONTALLY, MOVING_VERTICALLY or FLOATING)
     */
    public BubbleState getState() {
        return this.state;
    }

    /*
     * Move to the left if {@link EntityDirection} is left or right if
     * {@link EntityDirection} if right.
     */
    private void moveHorizontally() {
        if (this.getDirection() == EntityDirection.LEFT) {
            this.movementManager.bubbleMoveLeft();
        } else if (this.getDirection() == EntityDirection.RIGHT) {
            this.movementManager.bubbleMoveRight();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAfterCollisionStep() {
    }
}
