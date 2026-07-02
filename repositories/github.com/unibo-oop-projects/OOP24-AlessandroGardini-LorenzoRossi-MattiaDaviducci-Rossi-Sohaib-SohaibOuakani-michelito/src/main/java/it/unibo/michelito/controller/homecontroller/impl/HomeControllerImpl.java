package it.unibo.michelito.controller.homecontroller.impl;

import it.unibo.michelito.controller.homecontroller.api.HomeController;
import it.unibo.michelito.controller.homecontroller.api.ViewControllerListener;
import it.unibo.michelito.controller.maincontroller.api.HomeParentController;
import it.unibo.michelito.view.homeview.HomeView;

/**
 * Implementation of the {@link HomeController} interface.
 */
public class HomeControllerImpl implements HomeController, ViewControllerListener {
    private final HomeParentController homeParentController;
    private HomeView homeView;

    /**
     * Creates a new instance of {@link HomeControllerImpl}.
     *
     * @param homeParentController the parent controller.
     */
    public HomeControllerImpl(final HomeParentController homeParentController) {
        this.homeParentController = homeParentController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void switchToGame() {
        homeParentController.switchToGame();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMenu() {
        homeView = new HomeView(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void hideMenu() {
        if (homeView != null) {
            homeView.dispose();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        homeParentController.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleException(final Exception exception) {
        homeParentController.handleException(exception);
    }
}
