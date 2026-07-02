package model.settings;

import java.io.Serializable;

import model.utilities.Difficulty;
/**
 * This interface represent the settings of the game.
 * */
public interface Settings extends Serializable {

    /**
     * Return true if the game allows Sound FX.
     * @return true if the game allows Sound FX.
     */
    boolean isEnableSoundFx();

    /**
     * Return true if the game allows Music.
     * @return true if the game allows Music.
     */
    boolean isEnableMusic();

    /**
     * Return true if the user wants to play whit Left and Right Keyboard buttons.
     * @return true if the user wants to play whit Left and Right Keyboard buttons.
     */
    boolean useLeftAndRight();

    /**
     * Return true if the user wants to play whit Up and Down Keyboard buttons.
     * @return true if the user wants to play whit Up and Down Keyboard buttons.
     */
    boolean useUpAndDown();

    /**
     * Return the difficulty of the game selected by the player.
     * @return the difficulty of the game selected by the player.
     */
    Difficulty getDifficulty();
}
