package it.unibo.plantsfarm.controller;

import it.unibo.plantsfarm.controller.gamepanel.ImplControllerGamePanel;
import it.unibo.plantsfarm.controller.menu.EncyclopediaController;
import it.unibo.plantsfarm.controller.menu.PauseMenuController;
import it.unibo.plantsfarm.controller.menu.ShopController;
import it.unibo.plantsfarm.controller.menu.StorageController;
import it.unibo.plantsfarm.model.menu.impl.GameStateImpl;
import it.unibo.plantsfarm.view.MainScreen;

/**
 * Manages the interactions on the Main Screen, acting as a central navigation hub 
 * for the Shop, Encyclopedia, Storage, and Pause menus.
 */
public final class MainScreenController {

    private final MainScreen view;
    //private final ImplControllerGamePanel gamecontroller;

    /**
     * Initializes the Main Screen Controller and synchronizes the view with the current game state.
     *
     * @param gameState         The current game state.
     * @param gamecontroller    The game panel controller.
     */
    public MainScreenController(final GameStateImpl gameState, final ImplControllerGamePanel gamecontroller) {
        //this.gamecontroller = gamecontroller;
        this.view = new MainScreen();
        this.view.createMainScreen(gamecontroller.getView());
        setupListeners(gameState);
        updateView(gameState);
    }

    private void setupListeners(final GameStateImpl gameState) {

        // Shop Button
        this.view.attachShopListener(e -> {
            new ShopController(gameState, () -> updateView(gameState)).start();
            updateView(gameState);
        });

        // Encyclopedia Button
        this.view.attachEncyclopediaListener(e -> {
            new EncyclopediaController().start(gameState);
        });

        // Storage Button
        this.view.attachStorageListener(e -> {
            new StorageController(gameState).start();
        });

        // Exit Button
        this.view.attachExitListener(e -> {
            new PauseMenuController(this.view::close, gameState::resetGame).start();
        });
    }

    private void updateView(final GameStateImpl gameState) {
        this.view.updateCoinLabel(gameState.getWallet());
    }
}
