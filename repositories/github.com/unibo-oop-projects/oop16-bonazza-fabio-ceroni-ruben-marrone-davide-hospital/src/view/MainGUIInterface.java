package view;

import java.awt.event.ActionEvent;
/**
 * Interface for the main GUI.
 *
 */
public interface MainGUIInterface {

    /**
     * Overriding this method allow to place the actionListeners together in one place instead of putting them between other parts of code.
     * @param event
     * the event to be handled
     */
    void actionPerformed(ActionEvent event);

}