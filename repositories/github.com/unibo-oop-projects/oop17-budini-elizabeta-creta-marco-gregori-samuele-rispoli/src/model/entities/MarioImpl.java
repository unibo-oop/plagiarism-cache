package model.entities;

import java.awt.Dimension;
import model.ModelImpl;

/**
 * An Implementation of Mario, the main character of the game.
 *
 */
public final class MarioImpl extends DynamicEntityImpl implements Mario, DynamicEntity {

    private boolean jumping;
    private static final double JUMP_DISTANCE = -2.5;
    private static final double STEP = 1.4;
    private static final double CLIMBING_STEP = 0.9;
    private boolean moving;

    /**
     * A constructor for the main character of the game, the character cannot be
     * spawned outside the game bounds.
     * 
     * @param x
     *            The starting x Coordinate.
     * @param y
     *            The starting Y Coordinate.
     * @param dim
     *            Dimension of Mario's hitbox
     */
    public MarioImpl(final Double x, final Double y, final Dimension dim) {
        super(x, y, dim);
        if (x < 0 || x > ModelImpl.WIDTH) {
            throw new IllegalArgumentException("The character can only be spawned inside game border");
        }
    }

    @Override
    public void update() {
        super.update();
        if (this.getStatus() == EntityStatus.OnTheFloor) {
            this.jumping = false;
        }
        this.moving = checkMovement();
        this.stopMoving(this.getCurrentDirection());
    }

    @Override
    public void stopMoving(final Movement dir) {
        if (dir == Movement.LEFT || dir == Movement.RIGHT) {
            this.setDeltaX(0);
        }
        if ((dir == Movement.UP || dir == Movement.DOWN) && this.getStatus() != EntityStatus.Falling) {
            this.setDeltaY(0);
        }
    }

    @Override
    public boolean isJumping() {
        return this.jumping;
    }

    @Override
    public boolean isMoving() {
        return this.moving;
    }

    @Override
    public String toString() {
        return "DEBUG INFORMATION MARIO: Coordinates: [" + this.getX() + "," + this.getY() + "] - " + "Status : ["
                + this.getStatus() + "] - Dy : [" + this.getDeltaY() + "]" + " - Dx : [" + this.getDeltaX() + "]";
    }

    @Override
    protected void tryToMove(final Movement dir) {
        if (dir == Movement.LEFT && this.getStatus() != EntityStatus.Climbing) {
            this.setDirection(dir);
            this.setDeltaX(-STEP);
        } else if (dir == Movement.RIGHT && this.getStatus() != EntityStatus.Climbing) {
            this.setDirection(dir);
            this.setDeltaX(STEP);
        } else if ((dir == Movement.UP && (ModelImpl.canClimbUp(this) || this.getStatus() == EntityStatus.Climbing))) {
            this.setDirection(dir);
            this.setDeltaY(-CLIMBING_STEP);
            this.setStatus(EntityStatus.Climbing);
        } else if ((dir == Movement.DOWN && (ModelImpl.canClimbDown(this) || this.getStatus() == EntityStatus.Climbing))) {
            this.setDirection(dir);
            this.setDeltaY(CLIMBING_STEP);
            this.setStatus(EntityStatus.Climbing);
        }
        if (dir == Movement.JUMP && this.getStatus() == EntityStatus.OnTheFloor) {
            this.jump();
        }
        if (ModelImpl.borderCheck(this).isPresent()) {
            stopMoving(ModelImpl.borderCheck(this).get());
        }
        //System.out.println(this.toString());
    }

    private void jump() {
        jumping = true;
        this.setStatus(EntityStatus.Falling);
        this.setDeltaY(JUMP_DISTANCE);
    }

    /**
     * Checks whether or not Mario is trying to move outside game's borders.
     * 
     * @return true if Mario is still within borders, false otherwise.
     */
    private boolean isWithinBorder() {
        final double newCoord = this.getX() + this.getDeltaX();
        return newCoord >= 0 && newCoord <= ModelImpl.WIDTH;
    }

    private Boolean checkMovement() {
        return this.getDeltaX() != 0;
    }
}
