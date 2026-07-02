package it.unibo.oop.crossline.launcher;

import it.unibo.oop.crossline.io.Settings;

/**
 * Controller of the launcher.
 */
public interface LauncherController {

    /**
     * Load the settings from the model to the view.
     * @param settings the settings instance
     */
    void loadSettings(Settings settings);

    /**
     * Get the current settings values from the view and save them.
     * @param settings the settings instance
     */
    void saveSettings(Settings settings);

}
