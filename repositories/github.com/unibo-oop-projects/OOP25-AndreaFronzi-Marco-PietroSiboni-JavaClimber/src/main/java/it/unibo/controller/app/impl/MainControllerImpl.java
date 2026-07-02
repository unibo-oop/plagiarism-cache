package it.unibo.controller.app.impl;

import java.util.Optional;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.app.api.MainController;
import it.unibo.controller.end.impl.EndControllerImpl;
import it.unibo.controller.gamelaunched.impl.GameLaunchedControllerImpl;
import it.unibo.controller.inventory.impl.InventoryControllerImpl;
import it.unibo.controller.menu.impl.MenuControllerImpl;
import it.unibo.controller.pause.impl.PauseControllerImpl;
import it.unibo.controller.shop.impl.ShopControllerImpl;
import it.unibo.model.launchedgame.api.StateOfLaunchedGame;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.MenuImpl;
import it.unibo.model.menu.impl.MenuState;
import it.unibo.model.persistence.api.SaveManager;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.persistence.impl.SaveManagerImpl;
import it.unibo.view.app.api.MainView;

/**
 * Implementation of the {@link MainController} interface.
 */
public final class MainControllerImpl implements MainController {

    /**
     * The view currently opened. It can be a menu view, a game launched view, an
     * inventory view, a shop view, an end view or a pause view.
     */
    private MainView mainView;

    /**
     * The menu istance for the application.
     */
    private final Menu menu;

    /**
     * The save manager, responsible for saving and loading the progress of the
     * game.
     */
    private final SaveManager saveManager;

    /**
     * The state of the launched game, identifies the state of the game at a certain
     * point in the game.
     */
    private StateOfLaunchedGame runningState;

    /**
     * Constructs a new {@code MainControllerImpl}.
     * Initializes the save manager and the menu, attempts to load a previously
     * saved game,
     * and sets the initial menu state.
     * 
     * @param mainView the main view of the application to be managed by this
     *                 controller
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "In the MVC architecture, the Controller must hold a reference to the exact,"
        + "mutable instance of View and Model to synchronize the application state."
    )
    public MainControllerImpl(final MainView mainView) {
        this.mainView = mainView;
        this.saveManager = new SaveManagerImpl();
        this.menu = new MenuImpl(this);
        this.loadGame();
        this.menu.setState(new MenuState(this.menu));
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP2",
        justification = "In the MVC architecture, the Controller must hold a reference to the exact,"
        + "mutable instance of View and Model to synchronize the application state."
    )
    @Override
    public void setView(final MainView view) {
        this.mainView = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openMenuView() {
        final MenuControllerImpl menuController = new MenuControllerImpl(this, this.menu.getScoreManager(), this.menu);
        mainView.setMenuView(menuController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void launchGame() {
        final GameLaunchedControllerImpl gameLaunchedController = new GameLaunchedControllerImpl(
                this.menu.getLaunchedGame().get(), this.menu.getInventory());
        mainView.setGameLaunchedView(gameLaunchedController, gameLaunchedController);
        runningState = this.menu.getLaunchedGame().get().getState();
        gameLaunchedController.runGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openInventoryView() {
        final InventoryControllerImpl inventoryController = new InventoryControllerImpl(this, this.menu.getInventory(),
                this.menu.getInventory().getFactory());
        mainView.setInventoryView(inventoryController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openShopView() {
        final ShopControllerImpl shopController = new ShopControllerImpl(this, this.menu.getShopManager());
        mainView.setShopView(shopController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openEndView() {
        final EndControllerImpl endController = new EndControllerImpl(this.menu.getLaunchedGame().get(), this.menu);
        mainView.setEndView(endController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openPauseView() {
        final PauseControllerImpl pauseController = new PauseControllerImpl(this.menu.getLaunchedGame().get(),
                this.menu,
                this.runningState, this);
        mainView.setPauseView(pauseController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveProgress() {
        final SaveState currentState = new SaveState(this.menu.getInventory().getTotalCoins(),
                this.menu.getScoreManager().getHighScore(), this.menu.getInventory().getOwnedItems(),
                this.menu.getInventory().getConsumablesStatus(), this.menu.getInventory().getSelectedSkin(),
                this.menu.getInventory().getSelectedJumpLevel(), this.menu.getInventory().getSelectedSpeedLevel());
        this.saveManager.save(currentState);
    }

    /**
     * Loads the game's state using the save manager.
     * If a saved state is present, it updates the inventory and score manager with
     * the loaded data.
     * Otherwise, it initializes a new default save state and saves it.
     */
    private void loadGame() {
        final Optional<SaveState> loadedState = this.saveManager.load();
        if (loadedState.isPresent()) {
            this.menu.getInventory().loadState(loadedState.get());
            this.menu.getScoreManager().loadState(loadedState.get());
        } else {
            final SaveState initialState = new SaveState(0, 0, this.menu.getInventory().getOwnedItems(),
                    this.menu.getInventory().getConsumablesStatus(), this.menu.getInventory().getSelectedSkin(),
                    this.menu.getInventory().getSelectedJumpLevel(), this.menu.getInventory().getSelectedSpeedLevel());
            this.saveManager.save(initialState);
        }
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The Controller must return the exact, "
                      + "mutable instance of the Menù model to allow correct interactions."
    )
    @Override
    public Menu getMenu() {
        return this.menu;
    }
}
