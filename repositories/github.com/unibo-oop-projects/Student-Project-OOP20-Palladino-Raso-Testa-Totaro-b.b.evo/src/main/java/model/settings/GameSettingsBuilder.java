package model.settings;

import model.utilities.Difficulty;
/*
 * This interface represent the correct method to create the
 * settings of the game. */
public interface GameSettingsBuilder {

    /**
     * Used to set the soundFx.
     * @param isEnableSoundFX - use to set the sound fx parameter.
     * @return the soundFx property.
     */
    GameSettingsBuilder enableSoundFx(boolean isEnableSoundFX);

    /**
     * Used to set the Music.
     * @param isEnableMusic - use to set the music parameter.
     * @return the Music property.
     */
    GameSettingsBuilder enableMusic(boolean isEnableMusic);

    /**
     * Used to set the left and right game movement.
     * @param useLeftAndRight - use to set the left and right parameter.
     * @return the left and right game movement property.
     */
    GameSettingsBuilder leftAndRight(boolean useLeftAndRight);
    /**
     * Used to set the up and down game movement.
     * @param useUpAndDown - use to set the up and down parameter.
     * @return the up and down game movement property.
     */
    GameSettingsBuilder upAndDown(boolean useUpAndDown);

    /**
     * Used to set the difficulty of the game.
     * @param difficulty - use to set the difficulty parameter.
     * @return the difficulty property.
     */
    GameSettingsBuilder difficulty(Difficulty difficulty);

    /**
     * Used to build a correct version of Game Settings.
     * @return a correct version of Game Settings whit validation data.
     */
    Settings build();
}
