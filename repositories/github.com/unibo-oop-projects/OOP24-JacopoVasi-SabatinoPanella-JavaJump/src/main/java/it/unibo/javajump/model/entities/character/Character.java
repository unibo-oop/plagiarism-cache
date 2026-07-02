package it.unibo.javajump.model.entities.character;

import it.unibo.javajump.model.entities.GameObject;

/**
 * Interface that represents a character in the game, an extension of GameObject.
 */
public interface Character extends GameObject {
    /**
     * Method to change the state of the character.
     *
     * @param state the new state of the character
     */
    void changeState(CharacterState state);

    /**
     * Method to get the velocity of the character in the X axis.
     *
     * @return the velocity value in the X axis
     */
    float getVelocityX();

    /**
     * Method to set the velocity of the character in the X axis.
     *
     * @param vx the velocity value in the X axis
     */
    void setVelocityX(float vx);

    /**
     * Method to get the velocity of the character in the Y axis.
     *
     * @return the velocity value in the Y axis
     */
    float getVelocityY();

    /**
     * Method to set the velocity of the character in the X axis.
     *
     * @param vy the velocity value in the X axis
     */
    void setVelocityY(float vy);

    /**
     * Returns the old X position field of the character.
     *
     * @return the old X position
     */
    float getOldX();

    /**
     * Sets the old X position field of the character.
     *
     * @param oldX the old X position
     */
    void setOldX(float oldX);

    /**
     * Returns the old Y position field of the character.
     *
     * @return the old Y position
     */
    float getOldY();

    /**
     * Sets the old Y position field of the character.
     *
     * @param oldY the old Y position
     */
    void setOldY(float oldY);

    /**
     * Method to get the jump force of the character.
     *
     * @return the jump force
     */
    float getJumpForce();

    /**
     * Method to set the jump force of the character.
     *
     * @param jumpForce the jump force
     */
    void setJumpForce(float jumpForce);

    /**
     * Method called when the Character lands on a platform.
     */
    void landOnPlatform();

    /**
     * Method called when the Character leaves a platform and goes in the air.
     */
    void goInAir();

    /**
     * Method that returns a flag if the Character is on a platform.
     *
     * @return true if the Character is on a platform
     */
    boolean isOnPlatform();

    /**
     * Method that returns a flag if the Character is facing right.
     *
     * @return true if the Character is facing right
     */
    boolean isFacingRight();

    /**
     * Method that sets the flag if the Character is facing right.
     *
     * @param facingRight the flag to be set
     */
    void setFacingRight(boolean facingRight);


}
