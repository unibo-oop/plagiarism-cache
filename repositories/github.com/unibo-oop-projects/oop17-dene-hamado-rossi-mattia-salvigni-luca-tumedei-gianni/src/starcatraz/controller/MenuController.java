package starcatraz.controller;

import javafx.event.ActionEvent;
import javafx.scene.input.MouseEvent;

/**
 * Menu controller.
 */
public interface MenuController {

    /**
     * Invoked when is clicked a menu's buttons.
     * @param event
     * @throws IOException
     */
    void buttonClickEvent(final ActionEvent event);

    /**
     * Invoked when the cursor enter in a button.
     * @param event
     */
    void buttonMouseEntered(final MouseEvent event);

    /**
     * Invoked when the cursor exit from a button.
     * @param event
     */
    void buttonMouseExited(final MouseEvent event);

    /**
     * Invoked when newGame button is clicked.
     * @param event
     */
    void newGameButtonClik(ActionEvent event);
}
