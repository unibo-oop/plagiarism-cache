package thatlevelagain.character;

import thatlevelagain.view.sprite.Sprite;

/**
 * 
 */
public interface CharacterInterface extends Sprite {

    /**
     * Method to refresh continuously the information to show on the screen.
     */
    void update();

    /**
     * The method to make the character jump.
     */
    void jump();

    /**
     * Method to restart the level.
     */
    void restartLevel();

}
