package it.unibo.oop.crossline.launcher;

import java.awt.Dimension;
import java.awt.GraphicsDevice;
import it.unibo.oop.crossline.io.Settings;

/**
 * Model of the launcher.
 */
public interface LauncherModel {

    /**
     * Get the settings instance.
     * @return the settings instance
     */
    Settings getSettings();

    /**
     * Save the current settings to file.
     */
    void saveSettings();

    /**
     * Get all the available screens.
     * @return an array of screens
     */
    GraphicsDevice[] getAvailableScreens();

    /**
     * Get the default screen (this is setted by user in the OS).
     * @return the default screen
     */
    GraphicsDevice getDefaultScreen();

    /**
     * Gets the resolution of the passed screen.
     * @param screen the screen
     * @return the screen resolution
     */
    Dimension getScreenResolution(GraphicsDevice screen);

    /**
     * Start the main game.
     */
    void launchGame();

}
