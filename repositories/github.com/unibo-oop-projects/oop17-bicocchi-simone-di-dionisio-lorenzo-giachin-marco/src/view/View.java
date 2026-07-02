package view;

import controller.ViewManager;

/**
 * Interface for the class ViewImpl.
 */
public interface View {

    /**
     * An enum for the frames of the view.
     */
    enum Frames {

        /**
         * 
         */
        ACCESS, TUTORIAL, LOGIN, RANKING, GAME, SHOP, INVENTORY, DEATH;
    }

    /**
     * Sets the controller of the view.
     * 
     * @param newController
     *            is the controller
     */
    void attachViewManager(ViewManager newController);

    /**
     * Starts the manager and the view.
     */
    void start();

    /**
     * @param frame
     *            is the frame to be started;
     */
    void startFrame(Frames frame);

    /**
     * @return controller is the controller;
     */
    ViewManager getController();

    /**
     * @param cat
     *            is the category of the choosen item
     */
    void refreshInventory(Category cat);

    /**
     * Used to restard the game.
     */
    void restartGame();

    /**
     * Used to refresh game frame.
     * 
     * @param both 
     */
    void refreshGameFrame(boolean both);

    /**
     * Used to refresh age in game frame.
     */
    void refreshAge();

    /**
     * Goes to loginFrame and closes ranking if open.
     */
    void goToLogin();

    /**
     * Sets the value of the money.
     */
    void setMoneyValue();
}
