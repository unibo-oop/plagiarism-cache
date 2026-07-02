package controller;

import manager.ControllerManager;
import utility.Utils;

/**
 * General interface for each controller of the application.
 */
public interface Controller {

    /**
     * @return the controller manager of the application.
     */
    ControllerManager getManager();

    /**
     * Methods to come back to the main view of the application.
     */
    default void backToMenu() {
        getManager().setController(Utils.getDefaultController());
    }

}
