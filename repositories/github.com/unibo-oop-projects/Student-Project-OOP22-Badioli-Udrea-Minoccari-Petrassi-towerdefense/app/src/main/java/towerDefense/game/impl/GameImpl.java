package towerDefense.game.impl;

import towerDefense.game.api.Game;
import towerDefense.game.api.Panel;

public class GameImpl implements Game{

    private static Panel currentPanel;
    private static GameWindow gameWindow;
    
    /**
     * Creates a new Game and sets its window and panel
     */
	public GameImpl() {   
        currentPanel = new MenuPanel();
        gameWindow = new GameWindow(currentPanel);     
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Panel getCurrentPanel() {
        return currentPanel;
    }

    /**
     * Sets the panel as the current one
     * @param panel
     *          the panel that we want to set
     */
    public static void setCurrentPanel(Panel panel) {
        currentPanel = panel;
        gameWindow.changeWindow(panel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<? extends Panel> getCurrentPanelClass() {
        return currentPanel.getClass();
    }
}
