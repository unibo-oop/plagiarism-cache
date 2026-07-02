package it.tbt.view.api;

import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.impl.ShopController;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.modelmanager.InventoryState;

/**
 * Interface for a Factory of GameViews. To be implemented with framework
 * specific logic.
 */

public interface GameViewFactory {

    /**
     * @param menuController
     * @param menuState
     * @return the Menu view which renders the menuState data and has InputHandling done by menuController.
     */
    GameView createMenu(ViewController menuController, MenuState menuState);

    /**
     * @param menuController the controller to be passed the view
     * @param menuState the state the view will need to render
     * @return the Menu view which renders the menuState data and has InputHandling done by menuController.
     */
    GameView createPause(ViewController menuController, MenuState menuState);
    /**
     * @param exploreController the controller to be passed the view
     * @param exploreState the state the view will need to render
     * @return the GameView for the {@link it.tbt.model.GameState#EXPLORE} Game State
     */
    GameView createRoom(ViewController exploreController, ExploreState exploreState);

    /**
     * @param shopController the controller to be passed the view
     * @param shopState the state the view will need to render
     * @return the GameView for the {@link it.tbt.model.GameState#SHOP} Game State
     */
    GameView createShop(ShopController shopController, ShopState shopState);

    /**
     * @param fightController the controller to be passed the view
     * @param fightState the state the view will need to render
     * @return the GameView for the {@link it.tbt.model.GameState#FIGHT} Game State
     */
    GameView createFight(ViewController fightController, FightState fightState);

    /**
     * @param inventoryController the controller to be passed the view
     * @param inventoryState the state the view will need to render
     * @return the GameView for the {@link it.tbt.model.GameState#INVENTORY} Game State
     */
    GameView createInventory(ViewController inventoryController, InventoryState inventoryState);

    /**
     * @param endViewController the controller to be passed the view
     * @param endState the state the view will need to render
     * @return the GameView for the {@link it.tbt.model.GameState#ENDING} Game State
     */
    GameView createEndScreen(ViewController endViewController, EndState endState);

}
