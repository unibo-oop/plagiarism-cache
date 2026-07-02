package it.unibo.view.app.api;

import it.unibo.controller.end.api.EndController;
import it.unibo.controller.gamelaunched.api.GameLaunchedController;
import it.unibo.controller.gamelaunched.api.GameLaunchedInputController;
import it.unibo.controller.inventory.api.InventoryController;
import it.unibo.controller.menu.api.MenuController;
import it.unibo.controller.pause.api.PauseController;
import it.unibo.controller.shop.api.ShopController;

/**
 * Interface for the main view.
 */
public interface MainView {

    /**
     * Set the menu view.
     * 
     * @param menuController the controller for the menu view
     */
    void setMenuView(MenuController menuController);

    /**
     * Set the game launched view.
     * 
     * @param gameLaunchedController the controller for the game launched view
     * @param gameLaunchedInputController the controller for handling user input in the game launched view
     */
    void setGameLaunchedView(GameLaunchedController gameLaunchedController,
            GameLaunchedInputController gameLaunchedInputController);

    /**
     * Set the inventory view.
     * 
     * @param inventoryController the controller for the inventory view
     */
    void setInventoryView(InventoryController inventoryController);

    /**
     * Set the shop view.
     * 
     * @param shopController the controller for the shop view
     */
    void setShopView(ShopController shopController);

    /**
     * Set the end view.
     * 
     * @param endController the controller for the end view
     */
    void setEndView(EndController endController);

    /**
     * Set the pause view.
     * 
     * @param pauseController the controller for the pause view
     */
    void setPauseView(PauseController pauseController);

}
