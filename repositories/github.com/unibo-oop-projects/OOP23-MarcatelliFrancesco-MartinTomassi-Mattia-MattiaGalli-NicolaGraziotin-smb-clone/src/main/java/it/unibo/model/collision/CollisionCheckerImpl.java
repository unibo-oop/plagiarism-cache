package it.unibo.model.collision;

import java.util.List;

import it.unibo.commons.Constants;
import it.unibo.model.entity.player.MeatBoy;
import it.unibo.model.entity.player.MeatBoyImpl;
import it.unibo.model.hitbox.CircularHitbox;
import it.unibo.model.hitbox.RectangleHitbox;

import java.awt.event.KeyEvent;

/**
 * Implementation of CollisionChecker interface rapresenting the 
 * checker of the interactions.
 */
public class CollisionCheckerImpl implements CollisionChecker {

    private final List<CircularHitbox> sawsHitboxs;
    private final List<RectangleHitbox> platformsHitboxs;
    private final RectangleHitbox bandageGirlHitbox;
    private final MeatBoy meatBoy;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean jump;

    private boolean leftBound;
    private boolean rightBound;
    private boolean upperBound;

    private CollisionState state;
    private int jumpHeight;

    /**
     * Construncts the collisionChecker passing the handler.
     * 
     * @param collisionHandler the handler of the collision checker
     */
    public CollisionCheckerImpl(final CollisionHandler collisionHandler) {
        this.sawsHitboxs = collisionHandler.getGameModel().getSaws().stream()
                .map(t -> t.getHitbox())
                .toList();
        this.platformsHitboxs = collisionHandler.getGameModel().getPlatforms().stream()
                .map(t -> t.getHitbox())
                .toList();
        this.bandageGirlHitbox = collisionHandler.getGameModel().getBandageGirl().getHitbox();
        this.meatBoy = collisionHandler.getGameModel().getMeatBoy();
        this.state = CollisionState.GROUND;
    }

    /**
     * Check if MeatBoy collides with any obstacols in the level map.
     */
    @Override
    public void isColliding() {
        if (this.platformsHitboxs.stream()
                .map(h -> h.getHitbox())
                .filter(h -> h.intersects(meatBoy.getHitbox().getHitbox()))
                .count() > 0) {
            state = CollisionState.GROUND;
        } else {
            state = CollisionState.AIR;
        }
        if (this.sawsHitboxs.stream()
                .map(h -> h.getHitbox())
                .filter(h -> h.intersects(meatBoy.getHitbox().getHitbox()))
                .count() > 0) {
            state = CollisionState.SAW;
        }
        if (this.bandageGirlHitbox.getHitbox().intersects(meatBoy.getHitbox().getHitbox())) {
            state = CollisionState.BANDAGE_GIRL;
        }
    }

    /**
     * Check if MeatBoy is in the game screen or if is fallen.
     * 
     * @return The state of MeatBoy related to border limit
     */
    @Override
    public CollisionState inWindow() {
        leftBound = this.meatBoy.getX() < 0;
        rightBound = this.meatBoy.getX() + Constants.TILE_SIZE > Constants.SW - Constants.TILE_SIZE;
        upperBound = this.meatBoy.getY() < 0;

        return this.meatBoy.getY() > Constants.SH ? CollisionState.FALL : state;
    }

    /**
     * Update the position of Meatboy and his Hitbox, only 
     * if the movement that he has to do is possible.
     */
    @Override
    public void updateMeatBoy() {
        this.horizontalCollision();
        this.verticalCollision();
    }

    private void horizontalCollision() {
        // left
        if (moveLeft && !moveRight && !leftBound) {
            this.collidingWall(MeatBoyImpl.SPEED * this.meatBoy.getSpeedMul());
        }
        // right
        if (!moveLeft && moveRight && !rightBound) {
            this.collidingWall(-(MeatBoyImpl.SPEED * this.meatBoy.getSpeedMul()));
        }
    }

    private void collidingWall(final double speed) {
        this.meatBoy.setX(this.meatBoy.getX() - speed);
        isColliding();
        if (state.equals(CollisionState.GROUND)) {
            state = CollisionState.WALL;
            this.meatBoy.setX(this.meatBoy.getX() + speed);
        }
    }

    private void verticalCollision() {
        // up
        if (jump && !upperBound && jumpHeight < MeatBoyImpl.MAX_JUMP_HEIGHT) {
            this.meatBoy.setY(this.meatBoy.getY() - MeatBoyImpl.JUMP_SPEED);
            isColliding();
            if (state.equals(CollisionState.GROUND)) {
                this.meatBoy.setY(this.meatBoy.getY() + MeatBoyImpl.JUMP_SPEED);
            }
            jumpHeight += MeatBoyImpl.JUMP_SPEED;
            // fall
        } else if (state.equals(CollisionState.AIR) || state.equals(CollisionState.WALL)) {
            if (state.equals(CollisionState.AIR)) {
                this.meatBoy.setY(this.meatBoy.getY() + MeatBoyImpl.FALLING_SPEED);
                isColliding();
                if (state.equals(CollisionState.GROUND)) {
                    this.meatBoy.setY(this.meatBoy.getY() - MeatBoyImpl.FALLING_SPEED);
                    jumpHeight = 0;
                }
            } else {
                this.meatBoy.setY(this.meatBoy.getY() + MeatBoyImpl.FALLING_SPEED);
                if (moveRight) {
                    this.meatBoy.setX(this.meatBoy.getX() + 1);
                } else if (moveLeft) {
                    this.meatBoy.setX(this.meatBoy.getX() - 1);
                }
                isColliding();
                if (state.equals(CollisionState.GROUND)) {
                    state = CollisionState.WALL;
                    if (moveRight) {
                        this.meatBoy.setX(this.meatBoy.getX() - 1);
                    } else if (moveLeft) {
                        this.meatBoy.setX(this.meatBoy.getX() + 1);
                    }
                    jumpHeight = 0;
                }
            }
        }
    }

    /**
     * Set in which direction the MeatBoy has to move.
     * 
     * @param k the key pressed in input
     */
    @Override
    public void moveMeatBoy(final int k) {
        switch (k) {
            case KeyEvent.VK_A -> this.setMoveLeft(true);
            case KeyEvent.VK_D -> this.setMoveRight(true);
            case KeyEvent.VK_SPACE -> {
                if (!state.equals(CollisionState.AIR)) {
                    this.setJump(true);
                }
            }
            case KeyEvent.VK_SHIFT -> this.meatBoy.setSpeedMul(2);
            default -> {
            }
        }
    }

    /**
     * Set in which direction the MeatBoy has to stop moving.
     * 
     * @param k the key released in input
     */
    @Override
    public void stopMovingMeatBoy(final int k) {
        switch (k) {
            case KeyEvent.VK_A -> this.setMoveLeft(false);
            case KeyEvent.VK_D -> this.setMoveRight(false);
            case KeyEvent.VK_SPACE -> this.setJump(false);
            case KeyEvent.VK_SHIFT -> this.meatBoy.setSpeedMul(1);
            default -> {
            }
        }
    }

    /**
     * Returns the state of MeatBoy.
     * 
     * @return state of MeatBoy
     */
    @Override
    public CollisionState getState() {
        return state;
    }

    /**
     * Initializes the states of the game.
     * This method is responsible for setting up or resetting the various states of the game.
     */
    @Override
    public void initializeStates() {
        this.moveLeft = false;
        this.moveRight = false;
        this.jump = false;
        this.state = CollisionState.GROUND;
    }

    private void setMoveLeft(final boolean moveLeft) {
        this.moveLeft = moveLeft;
    }

    private void setMoveRight(final boolean moveRight) {
        this.moveRight = moveRight;
    }

    private void setJump(final boolean jump) {
        this.jump = jump;
    }

}
