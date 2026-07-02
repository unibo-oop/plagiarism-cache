package view;

import controller.Updater;

/**
 * The main view that links all the single views.
 */
public interface MainView extends Updater {

    /**
     * Closes the graphical view and with it the entire application.
     */
    void exit();
}
