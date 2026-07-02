package model.settings;

/**
 * Interface of a class used to save the current state of the settings.
 * Andrea Serafini.
 *
 */
public interface SettingsManagerInterface {

    /**
     * Delete settings file.
     */
    void deleteSavedSettings();

    /**
     *
     * @return the settings for the game
     */
    GameSettings getSettings();

    /**
     *
     * @return true if the starting settings file is present
     */
    boolean isPresent();

    /**
     *
     * @return true if the saved settings file is present
     */
    boolean isSavedPresent();

    /**
     * Load saved settings from a previous game.
     */
    void loadSavedSettings();

    /**
     * Save the current setting on a file.
     */
    void saveSettings();

    /**
     * Update saved settings file.
     */
    void updateSavedSettings();

}
