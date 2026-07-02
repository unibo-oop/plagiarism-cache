package starcatraz.controller;

import javafx.event.ActionEvent;
import starcatraz.model.Settings;
import starcatraz.util.AppSound;

/**
 * Controller for Settings.
 */
public interface SettingsController {

    /**
     * Change resolution of Starcatraz.
     * @param event
     */
    void applyResolution(ActionEvent event);

    /**
     * Close Settings of app.
     */
    void closeSettingsButtonClick();

    /**
     * Set settings of app.
     * @param settings
     */
    void setSettings(final Settings settings);

    /**
     * Set music's volume.
     * @param volume
     */
    static void setMusic(double volume) {
        for (AppSound sound : AppSound.values()) {
            if (sound.isMusic()) {
                sound.setVolume(volume);
            }
        }
    }

    /**
     * Set sound's volume.
     * @param volume
     */
    static void setSound(double volume) {
        for (AppSound sound : AppSound.values()) {
            if (!sound.isMusic()) {
                sound.setVolume(volume);
            }
        }
    }

    /**
     * @return Game settings
     */
    Settings getSettings();
}
