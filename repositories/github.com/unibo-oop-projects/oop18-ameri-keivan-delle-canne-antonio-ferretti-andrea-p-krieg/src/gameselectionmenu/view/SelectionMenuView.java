package gameselectionmenu.view;

import gameselectionmenu.controller.SelectionMenuController;

/**
 * The SelectionMenuView interface represents the view part of the players
 * selection menu. This is the part of the menu that interacts directly with the
 * user.
 */
public interface SelectionMenuView {

    /**
     * This method set the controller.
     * @param controller the controller of the view
     * @throws IllegalStateException if the controller is already set.
     */
    void setController(SelectionMenuController controller);

    /**
     * Draws the GUI and refresh it depending on the button selected.
     */
    void draw();
}
