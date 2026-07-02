package model.entities;

import java.util.Objects;

import controller.gameloop.GameController;
import model.Model;
import model.entitiesutil.EnemyState;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.entitiesutil.HeroState;
import model.entitiesutil.MovementValue;
import model.physics.CharacterMovement;
import model.physics.CollisionDirection;
import model.physics.MovementManager;

/**
 * The {@link Entity} of the playing user.
 */
public class Hero extends EntityMovable {
    private static final double BUBBLE_SIZE_FACTOR = 0.6;
    private static final int BUBBLE_SPAWNING_DISTANCE = 2;
    private static final double SHOOT_BLOCK_SECONDS = 0.25; // Duration (s) * FPS
    private static final int SHOOT_BLOCK_TICKS = new Double(SHOOT_BLOCK_SECONDS * GameController.FPS).intValue(); 

    private final MovementManager movementManager;
    private HeroState state;
    private boolean isJumping;
    private boolean isFalling;
    private int shootBlockTicks;
    private boolean touchedBottomWall;
    private boolean touchedLeftWall;
    private boolean touchedRightWall;
    private int touchedBottomWallY;
    private boolean isDead;

    /**
     * The {@link Entity} of the playing user.
     * 
     * @param model     is the {@link Model}
     * @param x         is the left x coordinate
     * @param y         is the top y coordinate
     * @param width     of the entity
     * @param height    if the entity
     * @param direction of the entity
     */
    public Hero(final Model model, final int x, final int y, final int width, final int height,
            final EntityDirection direction) {
        super(Objects.requireNonNull(model), x, y, width, height,
                (Objects.requireNonNull(direction) == EntityDirection.LEFT ? EntityDirection.DOWN_LEFT
                        : EntityDirection.DOWN_RIGHT));
        this.state = HeroState.NORMAL;
        this.movementManager = new CharacterMovement(this.body);
        this.isJumping = false;
        this.isFalling = true;
        this.shootBlockTicks = 0;
        this.touchedBottomWall = false;
        this.touchedLeftWall = false;
        this.touchedRightWall = false;
        this.touchedBottomWallY = 0;
        this.isDead = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void isTouchedBy(final Entity entity, final CollisionDirection collisionDirection) {
        if (entity.getType().equals("Wall") && collisionDirection == CollisionDirection.LEFT) {
            this.touchedLeftWall = true;
        } else if (entity.getType().equals("Wall") && collisionDirection == CollisionDirection.RIGHT) {
            this.touchedRightWall = true;
        } else if (entity.getType().equals("Wall") && collisionDirection == CollisionDirection.BOTTOM) {
            this.touchedBottomWall = true;
            this.touchedBottomWallY = entity.getY();
        } else if (entity.getType().equals("Enemy") && ((Enemy) entity).getState() == EnemyState.NORMAL) {
            this.isDead = true;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doStep() {
        if (this.isDead) {
            this.getModel().processDeath();
            this.isDead = false;
        } else {
            if (this.isJumping && !this.isFalling) {
                this.isJumping = this.movementManager.characterJump();
                if (!this.isJumping) {
                    this.setDirection(this.getDirection() == EntityDirection.UP_LEFT ? EntityDirection.DOWN_LEFT
                            : EntityDirection.DOWN_RIGHT);
                }
            }
            if (this.shootBlockTicks > 0) {
                this.shootBlockTicks--;
                if (this.shootBlockTicks == 0) {
                    this.state = HeroState.NORMAL;
                }
            }
            this.getModel().getLevel().addToRecheckEntity(this);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doAfterCollisionStep() {
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

    /**
     * Call {@link CharacterManager} to execute movement given in input.
     * 
     * @param movementType movement that the character manager has to execute
     */
    public void move(final MovementValue movementType) {
        switch (movementType) {
            case CHARACTER_JUMP:
                if (!this.isJumping && !this.isFalling) {
                    this.isJumping = this.movementManager.characterJump();
                    this.state = HeroState.NORMAL;
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
            case SHOOT:
                this.shoot();
                break;
            default:
                break;
        }
    }

    /**
     * @return the {@link HeroState} of the {@link Entity}
     */
    public HeroState getState() {
        return this.state;
    }

    /**
     * The player shoots a new bubble.
     */
    public void shoot() {
        if (this.shootBlockTicks == 0) {
            this.state = HeroState.SHOOTING;
            this.shootBlockTicks = SHOOT_BLOCK_TICKS;
            EntityDirection bubbleDirection = null;
            final int bubbleSize = new Double(this.getWidth() * BUBBLE_SIZE_FACTOR).intValue();
            int bubbleX = 0;
            final int bubbleY = this.getY() + this.getWidth() / 2 - bubbleSize / 2;
            if (this.getDirection() == EntityDirection.LEFT || this.getDirection() == EntityDirection.UP_LEFT
                    || this.getDirection() == EntityDirection.DOWN_LEFT) {
                bubbleDirection = EntityDirection.LEFT;
                bubbleX = this.getX() - BUBBLE_SPAWNING_DISTANCE - bubbleSize;
            } else {
                bubbleDirection = EntityDirection.RIGHT;
                bubbleX = this.getX() + BUBBLE_SPAWNING_DISTANCE + this.getWidth();
            }
            this.getModel().getLevel()
                    .addEntity(new Bubble(this.getModel(), bubbleX, bubbleY, bubbleSize, bubbleSize, bubbleDirection));
        }
    }

}
