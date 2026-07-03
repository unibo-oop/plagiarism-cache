package view.menu;

import controller.Controller;

/**
 * Interface of menu's controller.
 *
 */
public interface ViewMenu {

    /**
     * Set controller on menu class.
     * @param ctr Controller of application
     */
    void setController(Controller ctr);

    /**
     * Wait that user finished to insert setting for the game.
     */
    void waitMenu();

}