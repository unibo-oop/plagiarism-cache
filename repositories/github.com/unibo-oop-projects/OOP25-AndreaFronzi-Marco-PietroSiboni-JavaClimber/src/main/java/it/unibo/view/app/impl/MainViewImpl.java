package it.unibo.view.app.impl;

import javax.swing.JFrame;
import javax.swing.JPanel;

import it.unibo.controller.end.api.EndController;
import it.unibo.controller.gamelaunched.api.GameLaunchedController;
import it.unibo.controller.gamelaunched.api.GameLaunchedInputController;
import it.unibo.controller.inventory.api.InventoryController;
import it.unibo.controller.menu.api.MenuController;
import it.unibo.controller.pause.api.PauseController;
import it.unibo.controller.shop.api.ShopController;
import it.unibo.view.app.api.MainView;
import it.unibo.view.end.impl.EndViewImpl;
import it.unibo.view.gamelaunchedview.impl.GameLaunchedViewPanelImpl;
import it.unibo.view.inventory.impl.InventoryViewImpl;
import it.unibo.view.menu.impl.MenuViewImpl;
import it.unibo.view.pause.impl.PauseViewImpl;
import it.unibo.view.shop.impl.ShopViewImpl;

/**
 * Implementation of {@link MainView} interface.
 */
public final class MainViewImpl implements MainView {

    public static final int DECREASE_SCREEN_SIZE = 2;
    private static final int DEFAULT_WIDTH = 1000;
    private static final int DEFAULT_HEIGHT = 800;

    /**
     * The main frame of the application, it contains the current panel displayed.
     */
    private final JFrame frame;

    /**
     * The current panel displayed, is the panel showed in the main frame.
     */
    private JPanel currentPanel;

    /**
     * Construct a MainViewImpl.
     */
    public MainViewImpl() {
        this.frame = new JFrame("Java Climber");
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.frame.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
    }

    /**
     * Switch the current panel to the new panel.
     * 
     * @param newPanel the new panel to be displayed
     */
    private void switchPanel(final JPanel newPanel) {
        if (currentPanel != null) {
            frame.remove(currentPanel);
        }
        currentPanel = newPanel;
        frame.add(currentPanel);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMenuView(final MenuController menuController) {
        final MenuViewImpl menuView = new MenuViewImpl(menuController);
        menuController.setView(menuView);
        switchPanel(menuView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setGameLaunchedView(final GameLaunchedController gameLaunchedController,
            final GameLaunchedInputController gameLaunchedInputController) {
        final GameLaunchedViewPanelImpl gameLaunchedView = new GameLaunchedViewPanelImpl(gameLaunchedController,
                gameLaunchedInputController);
        gameLaunchedController.setPanel(gameLaunchedView);
        switchPanel(gameLaunchedView);

        gameLaunchedView.setFocusable(true);
        gameLaunchedView.requestFocusInWindow();

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInventoryView(final InventoryController inventoryController) {
        final InventoryViewImpl inventoryView = new InventoryViewImpl(inventoryController);
        inventoryController.setView(inventoryView);
        switchPanel(inventoryView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setShopView(final ShopController shopController) {
        final ShopViewImpl shopView = new ShopViewImpl(shopController);
        shopController.setView(shopView);
        switchPanel(shopView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setEndView(final EndController endController) {
        final EndViewImpl endView = new EndViewImpl(endController);
        switchPanel(endView);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPauseView(final PauseController pauseController) {
        final PauseViewImpl pauseView = new PauseViewImpl(pauseController);
        switchPanel(pauseView);
    }

}
