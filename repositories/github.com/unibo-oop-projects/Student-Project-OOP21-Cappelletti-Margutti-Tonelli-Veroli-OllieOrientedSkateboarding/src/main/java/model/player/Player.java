package model.player;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;

/**
 * 
 * Class identifying the Player.
 *
 */
public interface Player {

    /**
     * Makes the jump start.
     */
    void jump();

    /**
     * Update the coordinate of the player during the jump.
     */
    void updateJump();

    /**
     * @return the actual jump state of the player
     */
    JumpState getJumpState();

    /**
     * @return the number of lives of the player
     */
    int getLives();

    /**
     * @return the number of jumps the player has done
     */
    int getJumpCounter();

    /**
     * @return the bounds of the Player
     */
    Rectangle2D getBounds();

    /**
     * @return the Image of the Player
     */
    Image getImage();

    /**
     * @return true if the shield is active
     */
    boolean isShieldActive();

    /**
     * Sets the double jump.
     * @param set true if doubleJump is active
     */
    void setDoubleJump(boolean set);

    /**
     * Sets the height of the land.
     * @param h the new height of the land
     */
    void setLandHeight(double h);

    /**
     * Sets the number of lives of the player.
     * @param lives the new number of lives to add
     */
    void setNumberOfLives(int lives);

    /**
     * Sets the shield.
     * @param active true if the shield is active
     */
    void setShield(boolean active);

}
