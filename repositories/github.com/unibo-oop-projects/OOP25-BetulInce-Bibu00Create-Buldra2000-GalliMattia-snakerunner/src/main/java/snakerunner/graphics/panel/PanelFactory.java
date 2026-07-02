package snakerunner.graphics.panel;

import snakerunner.controller.GameController;
import snakerunner.controller.NavigationController;
import snakerunner.controller.WorldController;

/**
 * Factory class for creating UI panel components of the application.
 * Provides static factory methods that return {@link BasePanel} instances
 * for each screen of the application, plus {@link GameBoardPanel} for the game rendering area.
 */
public final class PanelFactory {

    private PanelFactory() { }

    /**
     * Create new GamePanel.
     * 
     * @param controller GameController.
     * @return new GamePanel.
     */
    public static BasePanel createGamePanel(final GameController controller) {
        return new GamePanel(controller);
    }

    /**
     * Create new MenuPanel.
     * 
     * @param navigationController NavigationController.
     * @return new MenuPanel.
     */
    public static BasePanel createMenuPanel(final NavigationController navigationController) {
        return new MenuPanel(navigationController);
    }

    /**
     * Create new OptionPanel.
     * 
     * @param navigationController NavigationController.
     * @return new OptionPanel.
     */
    public static BasePanel createOptionPanel(final NavigationController navigationController) {
        return new OptionPanel(navigationController);
    }

    /**
     * Create a new TutorialPanel.
     * 
     * @param navigationController NavigationController.
     * @return new TutorialPanel.
     */
    public static BasePanel createTutorialPanel(final NavigationController navigationController) {
        return new TutorialPanel(navigationController);
    }

    /**
     * Create new GameBoardPanel.
     * 
     * @param worldController WorldController.
     * @return new GameBoardPanel.
     */
    public static GameBoardPanel createGameBoardPanel(final WorldController worldController) {
        return new GameBoardPanel(worldController);
    }
}
