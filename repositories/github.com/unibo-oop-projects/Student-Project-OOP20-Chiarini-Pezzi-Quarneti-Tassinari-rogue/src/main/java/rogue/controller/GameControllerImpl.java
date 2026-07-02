package rogue.controller;

import java.io.IOException;

import rogue.controller.inventory.InventoryController;
import rogue.controller.inventory.InventoryControllerImpl;
import rogue.model.creature.Player;
import rogue.model.creature.PlayerFactoryImpl;
import rogue.view.GameView;

/**
 * An implementation of a {@link GameController}.
 *
 */
public class GameControllerImpl implements GameController {

    private final StatusBarController statusBarController;
    private final InventoryController inventoryController;
    private final WorldController worldController;

    public GameControllerImpl() {
        /*
         * User's player.
         */
        final Player player = new PlayerFactoryImpl().create();
        /*
         * Create controllers/views.
         */
        this.statusBarController = new StatusBarControllerImpl(player);
        this.inventoryController = new InventoryControllerImpl(player);
        this.worldController = new WorldController(player);
    }

    /**
     * Show the game view.
     */
    public void showGame() {
        try {
            new GameView(statusBarController, inventoryController, worldController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
