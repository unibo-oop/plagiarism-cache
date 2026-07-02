package it.unibo.javajump.model.physics;

import it.unibo.javajump.model.entities.character.Character;


/**
 * The implementation of PhysicsManager interface.
 */
public final class PhysicsManagerImpl implements PhysicsManager {
    private final float acceleration;
    private final float maxSpeed;
    private final float deceleration;
    private final float gravity;


    /**
     * Instantiates a new Physics manager.
     *
     * @param gravity      the desired gravity applied on the character
     * @param acceleration the acceleration of movements
     * @param maxSpeed     the max speed reachable by the player
     * @param deceleration the deceleration when the player stops moving or changes direction
     */
    public PhysicsManagerImpl(final float gravity, final float acceleration, final float maxSpeed, final float deceleration) {
        this.gravity = gravity;
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.deceleration = deceleration;
    }

    /**
     * {@inheritDoc} The character moves in the desired direction, based on MovementDirection parameter
     */
    @Override
    public void updateCharacterMovement(final Character character, final float deltaTime, final MovementDirection direction) {
        float vx = character.getVelocityX();

        // CHECKSTYLE: MissingSwitchDefault OFF
        // the switch is exhaustive even without default case
        switch (direction) {
            case RIGHT:
                vx = PhysicsUtilsImpl.accelerateToRight(vx, deltaTime, acceleration, maxSpeed);
                character.setFacingRight(true);
                break;
            case LEFT:
                vx = PhysicsUtilsImpl.accelerateToLeft(vx, deltaTime, acceleration, maxSpeed);
                character.setFacingRight(false);
                break;
            case NONE:vx = PhysicsUtilsImpl.decelerate(vx, deltaTime, deceleration);
                break;
        }
        // CHECKSTYLE: MissingSwitchDefault ON
        character.setVelocityX(vx);
        updateCharacterPosition(character, deltaTime);
    }

    /**
     * Private method to update character position, applying velocity and gravity.
     *
     * @param character to update
     * @param deltaTime the time passed from the last update
     */
    private void updateCharacterPosition(final Character character, final float deltaTime) {
        character.setOldX(character.getX());
        character.setOldY(character.getY());

        final float newX = character.getX() + character.getVelocityX() * deltaTime;
        final float newY = character.getY() + character.getVelocityY() * deltaTime;
        final float newVy = character.getVelocityY() + gravity * deltaTime;

        character.setX(newX);
        character.setY(newY);
        character.setVelocityY(newVy);
    }
}
