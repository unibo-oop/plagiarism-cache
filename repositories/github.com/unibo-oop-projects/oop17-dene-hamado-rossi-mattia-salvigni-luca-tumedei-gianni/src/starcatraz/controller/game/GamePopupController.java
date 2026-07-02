package starcatraz.controller.game;

/**
 * Controller for game popup windows.
 */
public abstract class GamePopupController {

    /**
     * The game controller object.
     */
    private GameController gameController;

    /**
     * @return: the game controller object
     */
    public GameController getGameController() {
        return this.gameController;
    }

    /**
     * @param gameController: the game controller object to be set
     */
    public void setGameController(final GameController gameController) {
        this.gameController = gameController;
    }
}
