package it.unibo.javajump.model.entities.character;

import it.unibo.javajump.model.entities.GameObject;
import it.unibo.javajump.model.entities.GameObjectImpl;
import it.unibo.javajump.model.entities.character.states.InAirState;
import it.unibo.javajump.model.entities.character.states.OnPlatformState;

import static it.unibo.javajump.utility.Constants.VELOCITY_INIT;

/**
 * Implementation of the Character interface.
 */
public final class CharacterImpl extends GameObjectImpl implements Character {
    /**
     * The character's velocity in the X axis.
     */
    private float velocityX;
    /**
     * The character's velocity in the Y axis.
     */
    private float velocityY;
    /**
     * The character's jump force, which is the force applied to the character when it jumps from a platform.
     */
    private float jumpForce;
    /**
     * Field to store the character's previous position in the X axis.
     */
    private float oldX;
    /**
     * Field to store the character's previous position in the Y axis.
     */
    private float oldY;
    /**
     * Flag to represent whether the character is facing right or not.
     */
    private boolean facingRight;

    /**
     * Field to store the current state of the character, referencing an instance of CharacterState (STATE PATTERN).
     */
    private CharacterState currentState;

    /**
     * Constructor for the CharacterImpl class.
     *
     * @param x         the x position of the character
     * @param y         the y position of the character
     * @param width     the width of the character
     * @param height    the height of the character
     * @param jumpForce the jump force of the character
     */
    public CharacterImpl(final float x, final float y, final float width, final float height, final float jumpForce) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.jumpForce = jumpForce;
        this.velocityX = VELOCITY_INIT;
        this.velocityY = VELOCITY_INIT;
        this.oldX = x;
        this.oldY = y;
        this.currentState = new InAirState();
    }

    /**
     * {@inheritDoc}
     * In this method, the current state of the character is updated.
     */
    @Override
    public void update(final float deltaTime) {
        currentState.updateCharacter(this, deltaTime);
    }

    @Override
    public void onCollision(final GameObject other) {
    }

    @Override
    public void changeState(final CharacterState newState) {
        currentState.onExit(this);
        this.currentState = newState;
        currentState.onEnter(this);
    }

    @Override
    public void landOnPlatform() {
        changeState(new OnPlatformState());
    }

    @Override
    public void goInAir() {
        changeState(new InAirState());
    }

    @Override
    public boolean isOnPlatform() {
        return currentState.isOnPlatform();
    }

    @Override
    public float getVelocityX() {
        return velocityX;
    }

    @Override
    public void setVelocityX(final float velocityX) {
        this.velocityX = velocityX;
    }

    @Override
    public float getVelocityY() {
        return velocityY;
    }

    @Override
    public void setVelocityY(final float velocityY) {
        this.velocityY = velocityY;
    }

    @Override
    public float getJumpForce() {
        return jumpForce;
    }

    @Override
    public void setJumpForce(final float jumpForce) {
        this.jumpForce = jumpForce;
    }

    @Override
    public float getOldX() {
        return oldX;
    }

    @Override
    public void setOldX(final float oldX) {
        this.oldX = oldX;
    }

    @Override
    public float getOldY() {
        return oldY;
    }

    @Override
    public void setOldY(final float oldY) {
        this.oldY = oldY;
    }

    @Override
    public boolean isFacingRight() {
        return facingRight;
    }

    @Override
    public void setFacingRight(final boolean facingRight) {
        this.facingRight = facingRight;
    }
}
