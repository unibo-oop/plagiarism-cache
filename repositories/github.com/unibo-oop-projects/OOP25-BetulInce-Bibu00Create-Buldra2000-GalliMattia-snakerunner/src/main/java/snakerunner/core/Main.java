package snakerunner.core;

import snakerunner.controller.NavigationController;
import snakerunner.controller.impl.NavigationControllerImpl;
import snakerunner.graphics.MainFrame;
import snakerunner.graphics.impl.MainFrameImpl;
import snakerunner.model.GameModel;
import snakerunner.model.impl.GameModelImpl;

/**
 * The Main class of the Snake Runner game.
 */
public final class Main {

    private Main() { }

    /**
     * The entry point of the application.
     *
     * @param args the command line arguments (not used).
     */
    public static void main(final String[] args) {
        final MainFrame mainFrame = new MainFrameImpl();
        final GameModel gameModel = new GameModelImpl();
        final NavigationController navigationController = new NavigationControllerImpl(mainFrame, gameModel);

        navigationController.init();
    }
}

