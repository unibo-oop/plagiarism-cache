package starcatraz.controller;

import javafx.event.ActionEvent;

/**
 * Pause Controller.
 */
public interface PauseController {

    /**
     * Resume Starcatraz's game.
     * @param event
     */
    void resumeButtonClick(ActionEvent event);

    /**
     * Close the pause's menu.
     * @param event
     */
    void backButtonClick(ActionEvent event);
}
