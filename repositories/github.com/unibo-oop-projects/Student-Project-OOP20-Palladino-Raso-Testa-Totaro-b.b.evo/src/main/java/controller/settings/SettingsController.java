package controller.settings;

import model.utilities.Difficulty;
/**
 * This interface define the correct controller for the settings of the game.*/
public interface SettingsController {

    /**
     * Return true if the game allows Sound FX.
     * @return true if the game allows Sound FX.
     */
    boolean isSoundFxEnable();

    /**
     * Return true if the game allows Music.
     * @return true if the game allows Music.
     */
    boolean isMusicEnable();

    /**
     * Return true if the user wants to play whit Left and Right Keyboard buttons.
     * @return true if the user wants to play whit Left and Right Keyboard buttons.
     */
    boolean isLeftAndRightEnable();

    /**
     * Return true if the user wants to play whit Up and Down Keyboard buttons.
     * @return true if the user wants to play whit Up and Down Keyboard buttons.
     */
    boolean isUpAndDownEnable();

    /**
     * Method that allows disable or enable SoundFx.
     * @param state - use to disable or enable SoundFx.
     */
    void changeSoundFxState(boolean state);

    /**
     * Method that allows disable or enable Music.
     * @param state - use to disable or enable Music.
     */
    void changeMusicState(boolean state);

    /**
     * Method that allows use the right and left keys to check the player.
     */
    void useLeftAndRightCommand();

    /**
     * Method that allows use the up and down keys to check the player.
     */
    void useUpAndDownCommand();

    /**
     * Method that allows to get the game difficulty.
     * @return the game difficulty.
     */
    Difficulty getDifficulty();

    /**
     * Method that allows to set the game difficulty.
     * @param difficulty - use to set the game difficulty.
     */
    void changeDifficulty(Difficulty difficulty);

    /**
     * Method that allows to print settings changes in settings configuration file.
     */
    void saveNewSettings();

}
