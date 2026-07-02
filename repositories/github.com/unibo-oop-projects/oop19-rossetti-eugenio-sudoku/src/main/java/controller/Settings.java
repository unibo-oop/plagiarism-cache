package controller;

import utilities.Theme;

/**
 * 
 * Interface for settings of the game.
 *
 */
public interface Settings {

    /**
     * Check if darkTheme is on.
     * @return if Dark theme is On
     */
    boolean isDarkThemeOn();

    /**
     * Set the darkTheme.
     */
    void setDarkTheme();

    /**
     * Check if assists are enabled.
     * @return if assists are On
     */
    boolean assistsOn();

    /**
     * Enabled the assists.
     */
    void setAssists();

    /**
     * Get the actual theme.
     * @return actual theme
     */
    Theme getTheme();
}
