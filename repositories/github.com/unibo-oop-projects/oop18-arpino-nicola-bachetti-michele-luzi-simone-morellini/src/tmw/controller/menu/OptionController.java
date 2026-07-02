package tmw.controller.menu;

/**
 * Interface that controls action related to the fxml file {@link OptionView}.
 * 
 */
public interface OptionController {

    /**
     * Set the option scene in the stage.
     */
    void start();

    /**
     * Permits to mute the volume.
     */
    void muteVolume();

    /**
     * Return to the main menu.
     */
    void goBack();

    /**
     * Save the personalize options chose by the player.
     */
    void save();

    /**
     * Modified the volume based on the player preference.
     */
    void setVolume();

    /**
     * Set all the possible resolution.
     */
    void setResolution();

    /**
     * Set the option settings inherited from the previous scene.
     * 
     * @param settings {@link OptionSettings}
     */
    void resetSettings(OptionsSettings settings);

}
