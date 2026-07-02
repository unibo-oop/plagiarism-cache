package it.unibo.controller.menu.impl;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.app.api.MainController;
import it.unibo.controller.menu.api.MenuController;
import it.unibo.model.menu.api.Menu;
import it.unibo.model.menu.impl.InventoryState;
import it.unibo.model.menu.impl.LaunchedGameState;
import it.unibo.model.menu.impl.ShoppingState;
import it.unibo.model.score.api.ScoreManager;
import it.unibo.view.menu.api.MenuView;

/**
 * Implementation of {@link MenuController} interface.
 */
public class MenuControllerImpl implements MenuController {

    private final Menu menu;
    private final MainController mainController;
    private final ScoreManager scoreManager;
    private MenuView view;

    /**
     * Construct a MenuControllerImpl with required model and manager.
     * 
     * @param mainController the main controller for managing view transitions and
     *                       saving progress
     * @param scoreManager   the model manager for scores
     * @param menu           the menu model
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP",
        justification = "The View must hold and interact with the actual Controller instance to dispatch user inputs correctly."
        + "So it is intentionally exposed."
    )
    public MenuControllerImpl(final MainController mainController, final ScoreManager scoreManager, final Menu menu) {
        this.menu = menu;
        this.mainController = mainController;
        this.scoreManager = scoreManager;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setView(final MenuView view) {
        this.view = view;
        refreshView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void start() {
        this.menu.setState(new LaunchedGameState(this.menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shop() {
        menu.setState(new ShoppingState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void inventory() {
        menu.setState(new InventoryState(menu));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void exit() {
        System.exit(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void openViewMenu() {
        mainController.openMenuView();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHighScore() {
        return this.scoreManager.getHighScore();
    }

    /**
     * Refresh the menu view with current data from the model.
     */
    private void refreshView() {
        if (this.view != null) {
            this.view.updateHighScore(this.getHighScore());
        }
    }
}
