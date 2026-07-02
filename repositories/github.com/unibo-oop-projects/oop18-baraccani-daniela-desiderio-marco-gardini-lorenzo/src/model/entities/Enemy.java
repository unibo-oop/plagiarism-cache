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
import model.physics.CharacterMovement;
import model.physics.CollisionDirection;
import model.physics.EnemyAIImpl;
import model.physics.MovementManager;

/**
 * The {@link Entity} the {@link Hero} should kill.
 */
public class Enemy extends EntityMovable {

    private static final int SCORE = 1000;
    private static final int BUBBLED_FLOATING_TICKS = 10 * GameController.FPS; // Duration (s) * FPS
    private final EnemyAIImpl ai;
    private final MovementManager movementManager;
    private EnemyState state;
    private boolean isJumping;
    private boolean isFalling;
    private boolean touchedBottomWall;
    private boolean touchedLeftWall;
    private boolean touchedRightWall;
    private int touchedBottomWallY;
    private boolean isFloating;
    private int auxTickContainer;

    /**
     * The {@link Entity} the {@link Hero} should kill.
     * 
     * @param model     is the {@link Model}
     * @param x         is the left x coordinate
     * @param y         is the top y coordinate
     * @param width     of the entity
     * @param height    if the entity
     * @param direction of the entity
     */
    public Enemy(final Model model, final int x, final int y, final int width, final int height,
            final EntityDirection direction) {
        super(Objects.requireNonNull(model), x, y, width, height,
                (Objects.requireNonNull(direction) == EntityDirection.LEFT ? EntityDirection.DOWN_LEFT
                        : EntityDirection.DOWN_RIGHT));
        this.ai = new EnemyAIImpl(this, this.getModel());
        this.movementManager = new CharacterMovement(this.body);
        this.state = EnemyState.NORMAL;
        this.isJumping = false;
        this.isFalling = true;
        this.touchedBottomWall = false;
        this.touchedLeftWall = false;
        this.touchedRightWall = false;
        this.touchedBottomWallY = 0;
        this.auxTickContainer = 0;
        this.isFloating = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isTouchedBy(final Entity entity, final CollisionDirection collisionDirection) {
        if (this.state == EnemyState.NORMAL) {
            if (entity.getType().equals("Wall") && collisionDirection == CollisionDirection.LEFT) {
                this.touchedLeftWall = true;
            } else if (entity.getType().equals("Wall") && collisionDirection == CollisionDirection.RIGHT) {
                this.touchedRightWall = true;
            } else if (entity.getType().equals("Wall") && collisionDirection == CollisionDirection.BOTTOM) {
                this.touchedBottomWall = true;
                this.touchedBottomWallY = entity.getY();
            } else if (entity.getType().equals("Bubble")
                    && ((Bubble) entity).getState() == BubbleState.MOVING_HORIZONTALLY) {
                this.state = EnemyState.BUBBLED;
                this.isFloating = false;
                this.setDirection(this.getDirection() == EntityDirection.DOWN_LEFT ? EntityDirection.UP_LEFT
                        : EntityDirection.UP_RIGHT);
            }
        } else { // state: BUBBLED
            if (entity.getType().equals("Hero")) {
                this.hide();
                this.getModel().addPoints(SCORE);
                this.getModel().enemyDied();
            } else if (!this.isFloating && entity.getType().equals("Wall")) {
                this.isFloating = true;
                this.auxTickContainer = BUBBLED_FLOATING_TICKS;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void doStep() {
        if (this.state == EnemyState.NORMAL) {
            this.ai.nextMove();
            if (this.isJumping && !this.isFalling) {
                this.isJumping = this.movementManager.characterJump();
                if (!this.isJumping) {
                    this.setDirection(this.getDirection() == EntityDirection.UP_LEFT ? EntityDirection.DOWN_LEFT
                            : EntityDirection.DOWN_RIGHT);
                }
            }
            this.getModel().getLevel().addToRecheckEntity(this);
        } else { // state: BUBBLED
            if (this.isFloating) {
                if (auxTickContainer > 0) {
                    this.movementManager.floatUpDown();
                    auxTickContainer--;
                    if (auxTickContainer == 0) {
                        this.state = EnemyState.NORMAL;
                        this.setDirection(this.getDirection() == EntityDirection.UP_LEFT ? EntityDirection.DOWN_LEFT
                                : EntityDirection.DOWN_RIGHT);
                    }
                    this.getModel().getLevel().addToRecheckEntity(this);
                }
            } else {
                this.movementManager.characterMoveUp();
                this.getModel().getLevel().addToRecheckEntity(this);
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAfterCollisionStep() {
        if (this.state == EnemyState.NORMAL) {
            if (this.touchedLeftWall && !this.touchedBottomWall) {
                this.movementManager.characterMoveRight();
            }
            if (this.touchedRightWall && !this.touchedBottomWall) {
                this.movementManager.characterMoveLeft();
            }
            if (this.touchedBottomWall && this.isFalling) {
                this.isFalling = false;
                this.movementManager.fallUndo(this.touchedBottomWallY);
                this.getModel().getLevel().addToRecheckEntity(this);
            }
            this.touchedLeftWall = false;
            this.touchedRightWall = false;
            this.touchedBottomWall = false;
            // Falling check
            if (!this.isJumping) {
                this.movementManager.fall();
                this.getModel().getLevel().getCollisionManager().collision(this);
                if (this.touchedBottomWall) {
                    this.isFalling = false;
                    this.movementManager.fallUndo(this.touchedBottomWallY);
                } else {
                    this.isFalling = true;
                }
                this.touchedLeftWall = false;
                this.touchedRightWall = false;
                this.touchedBottomWall = false;
            }
        }
    }

    /**
     * Receive commands to be executed from AI.
     * 
     * @param movementType movement that the AI calculated
     */
    public void move(final MovementValue movementType) {
        switch (movementType) {
            case CHARACTER_JUMP:
                if (!this.isJumping && !this.isFalling) {
                    this.isJumping = this.movementManager.characterJump();
                    this.setDirection(this.getDirection() == EntityDirection.DOWN_LEFT ? EntityDirection.UP_LEFT
                            : EntityDirection.UP_RIGHT);
                    this.getModel().getLevel().addToRecheckEntity(this);
                }
                break;
            case CHARACTER_MOVE_LEFT:
                this.movementManager.characterMoveLeft();
                this.setDirection(this.getDirection() == EntityDirection.UP_RIGHT
                        || this.getDirection() == EntityDirection.UP_LEFT ? EntityDirection.UP_LEFT
                                : EntityDirection.DOWN_LEFT);
                this.getModel().getLevel().addToRecheckEntity(this);
                break;
            case CHARACTER_MOVE_RIGHT:
                this.movementManager.characterMoveRight();
                this.setDirection(this.getDirection() == EntityDirection.UP_RIGHT
                        || this.getDirection() == EntityDirection.UP_LEFT ? EntityDirection.UP_RIGHT
                                : EntityDirection.DOWN_RIGHT);
                this.getModel().getLevel().addToRecheckEntity(this);
                break;
            default:
                break;
        }
    }

    /**
     * @return the {@link EnemyState} of the {@link Enemy} (NORMAL or BUBBLED)
     */
    public EnemyState getState() {
        return this.state;
    }
}
