package it.unibo.briscoola;

import it.unibo.briscoola.controller.api.MenuController;
import it.unibo.briscoola.controller.impl.MenuControllerImpl;
import it.unibo.briscoola.view.impl.GameViewImpl;

/**
 * import it.unibo.briscoola.model.api.game.GameModel;.
 * Class that handles the launch of the game.
 */
public final class BriscOOla {

    private BriscOOla() { }

    /**
     * Launches the application.
     *
     * @param args possible input arguments
     */
    public static void main(final String[] args) {

            final MenuController menuController = new MenuControllerImpl();

            final GameViewImpl gameView = new GameViewImpl(menuController);
 
            gameView.setOnGameStartListener((playerName, difficulty) -> {
            menuController.startGame(playerName, difficulty, gameView);
            });
 
            gameView.start();

    }
}
