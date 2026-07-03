package view;

import controller.GameEngine;
import controller.HighScoreManager;
import view.GameScreenImpl;

/**
 * The View of the MVC pattern, this class is responsible for everything shown
 * on the screen. It implements the method of the View
 * 
 *
 */
public class ViewImpl implements ViewInterface {

    // private final InputHandler inputHandler;
    private static GameScreenImpl gameScreen;
    private static GameEngine gameEngine;
    private static HighScoreManager highscoreManager;

    public ViewImpl() {
    }

    static void setView(final GameScreenImpl gamescreen) {
        ViewImpl.gameScreen = gamescreen;
    }

    static void setGameEngine(final GameEngine gameEngine) {
        ViewImpl.gameEngine = gameEngine;
    }

    public void startView() {
        MenuFrame.getMenuFrame().showMenu();
    }

    public static GameEngine getController() {
        return ViewImpl.gameEngine;
    }

    static void setHighScoreManager(final HighScoreManager hs) {
        ViewImpl.highscoreManager = hs;
    }

    public static HighScoreManager getHighScoreManager() {
        return ViewImpl.highscoreManager;
    }

    public GameScreenImpl getGameScreen() {
        return gameScreen;
    }

}