package talisman.view;

import talisman.controller.character.CurrentPlayerChoicesController;

/**
 * Interface of the view used to display the player's choices.
 * 
 * @author Alice Girolomini
 *
 */
public interface CurrentPlayerChoicesWindow {
    /**
     * Sets if the player can pass the turn.
     * 
     * @param enabled if the player can pass
     */
    void setInteractible(boolean enabled);

    /**
     * Closes the window.
     */
    void closeWindow();

    /**
     * Shows the window used to display the player's choices.
     *
     * @param controller - the controller of the player's choices
     * 
     * @return the shown window
     */
    static CurrentPlayerChoicesWindow show(final CurrentPlayerChoicesController controller) {
        return new CurrentPlayerChoicesWindowImpl(controller);
    }
}
