package controller.settings;

import controller.sound.SoundController;
import controller.utilities.IOSettings;
import model.settings.GameSettingsBuilder;
import model.settings.GameSettingsBuilderImpl;
import model.utilities.Difficulty;

public class SettingsControllerImpl implements SettingsController {

    private final GameSettingsBuilder settings;
    private final String filePath;

    public SettingsControllerImpl(final String filePath) {
        this.filePath = filePath;
        this.settings = new GameSettingsBuilderImpl();
        this.settings.enableSoundFx(IOSettings.readSettings(this.filePath).isEnableSoundFx());
        this.settings.enableMusic(IOSettings.readSettings(this.filePath).isEnableMusic());
        this.settings.leftAndRight(IOSettings.readSettings(this.filePath).useLeftAndRight());
        this.settings.upAndDown(IOSettings.readSettings(this.filePath).useUpAndDown());
        this.settings.difficulty(IOSettings.readSettings(this.filePath).getDifficulty());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isSoundFxEnable() {
        return IOSettings.readSettings(this.filePath).isEnableSoundFx();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isMusicEnable() {
        return IOSettings.readSettings(this.filePath).isEnableMusic();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isLeftAndRightEnable() {
        return IOSettings.readSettings(this.filePath).useLeftAndRight();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public boolean isUpAndDownEnable() {
        return IOSettings.readSettings(this.filePath).useUpAndDown();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void changeSoundFxState(final boolean state) {
        this.settings.enableSoundFx(state);
        if (state) {
            SoundController.enableSoundFx();
        } else {
            SoundController.stopFx();
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void changeMusicState(final boolean state) {
        this.settings.enableMusic(state);
        if (state) {
            SoundController.enableMusic();
        } else {
            SoundController.stopMusic();
            SoundController.disableMusic();
        }
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void useLeftAndRightCommand() {
        this.settings.leftAndRight(true);
        this.settings.upAndDown(false);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void useUpAndDownCommand() {
        this.settings.upAndDown(true);
        this.settings.leftAndRight(false);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public Difficulty getDifficulty() {
        return IOSettings.readSettings(this.filePath).getDifficulty();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void changeDifficulty(final Difficulty difficulty) {
        this.settings.difficulty(difficulty);
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void saveNewSettings() {
        IOSettings.printInJsonFormat(this.filePath, this.settings.build());
    }

}
