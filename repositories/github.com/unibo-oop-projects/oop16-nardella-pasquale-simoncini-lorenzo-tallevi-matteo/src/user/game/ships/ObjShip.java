package user.game.ships;

import user.enums.GameSprites;
import user.enums.Objects;
import user.game.bullets.ObjBullet;
import user.game.effects.ObjEffectEmitter;
import user.game.effects.ObjFadingElement;
import zengine.core.GameObject;

/**
 * This class represents a generic ship.
 */
public abstract class ObjShip extends GameObject {

    /**
     * This field represents the friction that applies to the ship.
     */
    protected static final double FRICTION = 0.45;

    private static final double BULLET_ANGLE = 30;

    private static final double TRAIL_ALPHA = 0.2;
    private static final double TRAIL_DISAPPEAR_SPEED = 0.015;

    // horizontal and vertical speed of the ship
    private double hSpeed;
    private double vSpeed;
    // rotation speed of the ship
    private double rotationSpeed;
    // stats of the ship
    private double health;
    private double power;
    private double attackCooldownCounter;
    private double attackCooldown;
    private double maxSpeed;
    private double currentSpeed;
    private double accelerationForce;
    // this field checks initialization of the ship
    private boolean initialized;
    // this fields check if the ship is rotating left or right
    private boolean rotatingLeft;
    private boolean rotatingRight;
    // standard hitbox value of the ship
    private static final double HITBOX_VALUE = 40;
    // current target of the ship
    private GameObject target;
    // target cooldown of the ship
    private static final double TARGET_COOLDOWN = 30;
    // target cooldown counter of the ship
    private double targetCooldownCounter;
    // trail's color
    private int trailColorIndex;

    @Override
    public void update() {
        this.setTargetCooldownCounter(this.getTargetCooldownCounter() - 1);
        this.setAttackCooldownCounter(this.getAttackCooldownCounter() - 1);
        this.handleTargetAcquisition();
        this.move();
        this.shoot();
        this.confineToRoomBoundaries();
        this.moveHitboxToCurrentPosition();
    }

    @Override
    public void destroy() {
        final ObjEffectEmitter ef = (ObjEffectEmitter) z().instanceCreate(this.getX(), this.getY(),
                Objects.EFFECT_EMITTER.getValue());
        ef.createMediumExplosion();
    }

    @Override
    public void draw() {
        drawSelf();
    }

    /**
     * This method returns the power of the ship.
     * 
     * @return the power
     */
    public double getPower() {
        return power;
    }

    /**
     * This method returns the target of the ship.
     * 
     * @return the target
     */
    public GameObject getTarget() {
        return target;
    }

    /**
     * This method calculates the speed at which the ship moves.
     */
    protected void calculateSpeed() {
        if (this.canAccelerate()) {
            this.setCurrentSpeed(this.getCurrentSpeed() + this.getAccelerationForce());
            if (this.getCurrentSpeed() >= this.getMaxSpeed()) {
                this.setCurrentSpeed(this.getMaxSpeed());
            }
        } else {
            this.setCurrentSpeed(this.getCurrentSpeed() - FRICTION);
            if (this.getCurrentSpeed() <= 0) {
                this.setCurrentSpeed(0);
            }
        }
    }

    /**
     * This method determines if the ship can accelerate.
     * 
     * @return true if the ship can accelerate
     */
    protected abstract boolean canAccelerate();

    /**
     * This method calculate which object is nearest to the caller and sets it
     * as the target.
     * 
     */
    protected abstract void chooseTarget();

    /**
     * This method confine the ship to the room boundaries.
     */
    protected void confineToRoomBoundaries() {
        if (this.getX() < 0) {
            this.setX(0);
        }
        if (this.getY() < 0) {
            this.setY(0);
        }
        if (this.getX() > z().roomWidth()) {
            this.setX(z().roomWidth());
        }
        if (this.getY() > z().roomWidth()) {
            this.setY(z().roomWidth());
        }
    }

    /**
     * This method generates a bullet.
     * 
     * @param bulletType
     *            the type of the bullet that has to be generated.
     * @param x
     *            coordinate x of the point in which the bullet generates.
     * @param y
     *            coordinate y of the point in which the bullet generates.
     * @param angle
     *            angle at which the bullet generates.
     * @return the instance of the ObjBullet just created
     */
    protected ObjBullet generateBullet(final String bulletType, final double x, final double y, final double angle) {
        final ObjBullet bullet = (ObjBullet) z().instanceCreate(x, y, bulletType);
        if (this.isRotatingLeft()) {
            bullet.setImageAngle(angle + BULLET_ANGLE);
        } else if (this.isRotatingRight()) {
            bullet.setImageAngle(angle - BULLET_ANGLE);
        } else {
            bullet.setImageAngle(angle);
        }
        bullet.setDamage(this.getPower());
        bullet.setSpeed(bullet.getSpeed() + getCurrentSpeed());
        this.setAttackCooldownCounter(this.getAttackCooldown());
        return bullet;
    }

    /**
     * This method generates the trail of the ship.
     */
    protected void generateTrail() {
        if (this.canAccelerate()) {
            final ObjFadingElement trail = (ObjFadingElement) z().instanceCreate(getX(), getY(),
                    Objects.FADING_ELEMENT.getValue());
            if (z().instanceExists(trail)) {
                trail.setSpriteIndex(GameSprites.TRAIL.getValue());
                trail.setImageIndex(this.getTrailColorIndex());
                trail.setImageXscale(4);
                trail.setImageYscale(4);
                trail.setImageAlpha(TRAIL_ALPHA);
                trail.setImageAngle(this.getImageAngle());
                trail.setDisappearSpeed(TRAIL_DISAPPEAR_SPEED);
            }
        }
    }

    /**
     * This method returns the acceleration force that applies to the ship.
     * 
     * @return the acceleration
     */
    protected double getAccelerationForce() {
        return accelerationForce;
    }

    /**
     * This method returns the attack cooldown of the ship.
     * 
     * @return the attackCooldown
     */
    protected double getAttackCooldown() {
        return attackCooldown;
    }

    /**
     * This method returns the attack cooldown counter of the ship.
     * 
     * @return the attackCooldownCounter
     */
    protected double getAttackCooldownCounter() {
        return attackCooldownCounter;
    }

    /**
     * This method returns the current speed of the ship.
     * 
     * @return the currentSpeed
     */
    protected double getCurrentSpeed() {
        return currentSpeed;
    }

    /**
     * This method returns the health of the ship.
     * 
     * @return the health
     */
    protected double getHealth() {
        return health;
    }

    /**
     * This method returns the value of the ship's hitbox.
     * 
     * @return the hitbox's value
     */
    protected double getHitboxValue() {
        return HITBOX_VALUE;
    }

    /**
     * This method returns the horizontal speed of the ship.
     * 
     * @return the hSpeed
     */
    protected double getHSpeed() {
        return hSpeed;
    }

    /**
     * This method returns the max speed of the ship.
     * 
     * @return the maxSpeed
     */
    protected double getMaxSpeed() {
        return maxSpeed;
    }

    /**
     * This method returns the rotation speed of the ship.
     * 
     * @return the rotationSpeed
     */
    protected double getRotationSpeed() {
        return rotationSpeed;
    }

    /**
     * This method returns the target cooldown of the ship.
     * 
     * @return the TARGET_COOLDOWN
     */
    protected double getTargetCooldown() {
        return TARGET_COOLDOWN;
    }

    /**
     * This method returns the target cooldown counter of the ship.
     * 
     * @return the targetCooldownCounter
     */
    protected double getTargetCooldownCounter() {
        return targetCooldownCounter;
    }

    /**
     * This method returns the trail color index.
     * 
     * @return the trail color index
     */
    public int getTrailColorIndex() {
        return trailColorIndex;
    }

    /**
     * This method returns the vertical speed of the ship.
     * 
     * @return the vSpeed
     */
    protected double getVSpeed() {
        return vSpeed;
    }

    /**
     * This method checks if the ship is initialized.
     * 
     * @return true if the ship is initialized
     */
    protected boolean isInitialized() {
        return initialized;
    }

    /**
     * This method checks if the ship is rotating left.
     * 
     * @return true if the ship is rotating left
     */
    protected boolean isRotatingLeft() {
        return rotatingLeft;
    }

    /**
     * This method checks if the ship is rotating right.
     * 
     * @return true if the ship is rotating right
     */
    protected boolean isRotatingRight() {
        return rotatingRight;
    }

    /**
     * This method return true if the ship is dead.
     * 
     * @return true if the ship is dead
     */
    protected boolean isShipDead() {
        return this.getHealth() <= 0;
    }

    /**
     * This method controls movement.
     */
    protected abstract void move();

    /**
     * This method sets the acceleration force that applies to the ship.
     * 
     * @param accelerationForce
     *            the acceleration to set
     */
    protected void setAccelerationForce(final double accelerationForce) {
        this.accelerationForce = accelerationForce;
    }

    /**
     * This method sets the attack cooldown of the ship.
     * 
     * @param attackCooldown
     *            the attackCooldown to set
     */
    protected void setAttackCooldown(final double attackCooldown) {
        this.attackCooldown = attackCooldown;
    }

    /**
     * This method sets the attack cooldown counter of the ship.
     * 
     * @param attackCooldownCounter
     *            the attackCooldownCounter to set
     */
    protected void setAttackCooldownCounter(final double attackCooldownCounter) {
        this.attackCooldownCounter = attackCooldownCounter;
    }

    /**
     * This method sets the current speed of the ship.
     * 
     * @param currentSpeed
     *            the currentSpeed to set
     */
    protected void setCurrentSpeed(final double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    /**
     * This method set the health of the ship.
     * 
     * @param health
     *            the health to set
     */
    protected void setHealth(final double health) {
        this.health = health;
    }

    /**
     * This method sets the horizontal speed of the ship to hSpeed.
     * 
     * @param hSpeed
     *            the hSpeed to set
     */
    protected void setHSpeed(final double hSpeed) {
        this.hSpeed = hSpeed;
    }

    /**
     * This method sets the initialization of the ship, that means the ship has
     * been initialized.
     * 
     * @param initialized
     *            the initialized to set
     */
    protected void setInitialized(final boolean initialized) {
        this.initialized = initialized;
    }

    /**
     * This method sets the max speed of the ship.
     * 
     * @param maxSpeed
     *            the maxSpeed to set
     */
    protected void setMaxSpeed(final double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    /**
     * This method sets the power of the ship.
     * 
     * @param power
     *            the power to set
     */
    protected void setPower(final double power) {
        this.power = power;
    }

    /**
     * This method sets the field rotatingLeft of the ship.
     * 
     * @param rotatingLeft
     *            the boolean value to set
     */
    protected void setRotatingLeft(final boolean rotatingLeft) {
        this.rotatingLeft = rotatingLeft;
    }

    /**
     * This method sets the field rotatingRight of the ship.
     * 
     * @param rotatingRight
     *            the boolean value to set
     */
    protected void setRotatingRight(final boolean rotatingRight) {
        this.rotatingRight = rotatingRight;
    }

    /**
     * This method sets the rotation speed of the ship.
     * 
     * @param rotationSpeed
     *            the rotationSpeed to set
     */
    protected void setRotationSpeed(final double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    /**
     * @param target
     *            the target to set
     */
    protected void setTarget(final GameObject target) {
        this.target = target;
    }

    /**
     * @param targetCooldownCounter
     *            the targetCooldownCounter to set
     */
    protected void setTargetCooldownCounter(final double targetCooldownCounter) {
        this.targetCooldownCounter = targetCooldownCounter;
    }

    /**
     * This method sets the trail color index.
     * 
     * @param trailColorIndex
     *            the trail color index to set
     */
    public void setTrailColorIndex(final int trailColorIndex) {
        this.trailColorIndex = trailColorIndex;
    }

    /**
     * This method sets the vertical speed of the ship to vSpeed.
     * 
     * @param vSpeed
     *            the vSpeed to set
     */
    protected void setVspeed(final double vSpeed) {
        this.vSpeed = vSpeed;
    }

    /**
     * This method defines how the ship shoots.
     */
    protected abstract void shoot();

    private void handleTargetAcquisition() {
        if (this.getTargetCooldownCounter() <= 0) {
            this.chooseTarget();
            this.setTargetCooldownCounter(this.getTargetCooldown());
        }
    }

}
