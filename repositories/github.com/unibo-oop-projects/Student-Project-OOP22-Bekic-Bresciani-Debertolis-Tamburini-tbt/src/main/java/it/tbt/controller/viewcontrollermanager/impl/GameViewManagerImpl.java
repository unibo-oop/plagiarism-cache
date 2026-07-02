package it.tbt.controller.viewcontrollermanager.impl;

import it.tbt.controller.SimpleLogger;
import it.tbt.controller.modelmanager.ExploreState;
import it.tbt.controller.modelmanager.MenuState;
import it.tbt.controller.modelmanager.ModelState;
import it.tbt.controller.modelmanager.FightState;
import it.tbt.controller.modelmanager.InventoryState;
import it.tbt.controller.modelmanager.EndState;
import it.tbt.controller.modelmanager.shop.ShopState;
import it.tbt.controller.viewcontrollermanager.api.ViewController;
import it.tbt.controller.viewcontrollermanager.api.ViewControllerManager;
import it.tbt.model.command.api.Command;
import it.tbt.model.GameState;
import it.tbt.view.api.GameView;
import it.tbt.view.api.GameViewFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.logging.Level;

/**
 * Default implementation of a ViewControllerManager.
 */

public class GameViewManagerImpl implements ViewControllerManager {

    private final GameViewFactory gameViewFactory;
    private ViewController currentController;
    private GameView currentGameView;

    /**
     * @param gameViewFactory the GameView factory which will be used to generate
     *                        the GameViews.
     */
    public GameViewManagerImpl(final GameViewFactory gameViewFactory) {
        this.gameViewFactory = gameViewFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<List<Command>> getCommands() {
        return Optional.ofNullable(this.currentController.getCommands());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void renderView(final GameState gameState, final ModelState modelState, final Boolean hasChanged) {
        if (hasChanged) {
            switch (gameState) {
                case EXPLORE -> {
                    handleViewState(modelState,
                            ExploreState.class,
                            ExploreControllerImpl.class,
                            (controller, state) -> this.gameViewFactory.createRoom(state, controller));
                }
                case MENU -> {
                    handleViewState(modelState,
                            MenuState.class,
                            MainMenuController.class,
                            (controller, state) -> this.gameViewFactory.createMenu(state, controller));
                }
                case SHOP -> {
                    handleViewState(modelState,
                            ShopState.class,
                            ShopController.class,
                            (controller, state) -> this.gameViewFactory.createShop(state, controller));
                }
                case PAUSE -> {
                    handleViewState(modelState,
                            MenuState.class,
                            PauseMenuController.class,
                            (controller, state) -> this.gameViewFactory.createPause(state, controller));
                }
                case FIGHT -> {
                    handleViewState(modelState,
                            FightState.class,
                            FightControllerImpl.class,
                            (controller, state) -> this.gameViewFactory.createFight(state, controller));
                }
                case INVENTORY -> {
                    handleViewState(modelState,
                            InventoryState.class,
                            InventoryViewController.class,
                            (controller, state) -> this.gameViewFactory.createInventory(state, controller));

                }
                case ENDING -> {
                    handleViewState(modelState,
                            EndState.class, EndViewController.class,
                            (controller, state) -> this.gameViewFactory.createEndScreen(state, controller));
                }
                default -> {
                    throw new IllegalStateException("GameState not handled by ViewManager.");
                }
            }
        }
        this.currentGameView.render();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void cleanCommands() {
        this.currentController.clean();
    }

    /**
     * @param modelState the modelState object of the current {@link GameState}.
     * @param stateClass the interface which extends modelState, the one specific to the current GameState.
     * @param controllerClass the controller class which shall be used to create the {@link ViewController}.
     *                        It MUST contain at least a constructor of only one parameter: the {@link ModelState}
     *                        appropriate for the ViewController.
     * @param createViewFunction function which create a GameView, will use the {@link GameViewFactory}
     * @param <T> generic type for the ModelState specific implementation/interface.
     * @param <C> generic type for the ViewController specific implementation/interface.
     */
    private <T extends ModelState, C extends ViewController> void handleViewState(
            final ModelState modelState,
            final Class<T> stateClass,
            final Class<C> controllerClass,
            final BiFunction<T, C, GameView> createViewFunction) {
        try {
            if (!stateClass.isInstance(modelState)) {
                throw new IllegalStateException("Data passed to View Manager inconsistent");
            }
            final T state = stateClass.cast(modelState);
            final C controller;
            controller = controllerClass.getConstructor(stateClass).newInstance(state);
            final var x = createViewFunction.apply(state, controller);
            this.currentController = controller;
            this.currentGameView = x;
        } catch (InvocationTargetException
                 | InstantiationException
                 | IllegalAccessException
                 | NoSuchMethodException e) {
            SimpleLogger.getLogger("GameViewManagerImpl", Level.SEVERE).severe("Error type: " + e);
        }
    }
}
