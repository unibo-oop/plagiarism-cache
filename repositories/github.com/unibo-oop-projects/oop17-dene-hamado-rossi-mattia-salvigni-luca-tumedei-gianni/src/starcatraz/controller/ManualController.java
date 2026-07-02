package starcatraz.controller;

import javafx.event.ActionEvent;

/**
 * Manual Controller.
 */
public interface ManualController {

    /**
     * Event to choose manual language.
     * @param event
     */
    void buttonClickEvent(final ActionEvent event);

    /**
     * Close manual view of Starcatraz.
     */
    void closeManualButtonClick();
}
