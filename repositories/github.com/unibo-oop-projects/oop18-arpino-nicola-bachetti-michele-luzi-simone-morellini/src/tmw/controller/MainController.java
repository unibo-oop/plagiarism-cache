package tmw.controller;

import tmw.controller.audio.AudioController;
import tmw.controller.menu.OptionsSettings;
import tmw.view.MainView;

/**
 * Interface that handle all the element present in the menus.
 *
 */
public interface MainController {

    /**
     * initialize the main menu.
     */
    void init();

    /**
     * exit the menu.
     */
    void exit();

    /**
     * Getter for the audio controller.
     * 
     * @return The current AudioController
     */
    AudioController getAudioController();

    /**
     * Getter for the main view.
     * 
     * @return the MainView
     */
    MainView getView();

    /**
     * Getter for the option settings.
     * 
     * @return the current option settings
     */
    OptionsSettings getOptionsSettings();

    /**
     * Getter for the stageController.
     * 
     * @return the stage controller
     */
    StageController getStageController();

    /**
     * Setter for the option settings.
     * 
     * @param settings OptionSettings
     */
    void setOptionsSettings(OptionsSettings settings);

}
