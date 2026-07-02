package frogger.controller;

import frogger.common.GameState;
import frogger.view.GameScene;

/**
 * Is the Controller that start at the beginning of the program and his loop is always activated.
 * Choose the right controller based on GameState anche give to him the frame. 
 */
public class MainControllerImpl {
    /**
     * The current active controller.
     */
    private Controller controller;
    /**
     * The controller responsible for the game logic.
     */
    private final GameController gameController = new GameControllerImpl();
    /**
     * The controller responsible for the shop logic.
     */
    private final ShopController shopController = new ShopController(gameController);

    /**
     * main loop that choose the controller based on Game actual State.
     */
    public void mainLoop() {
        final GameScene frame = new GameScene();
        while (true) {
            switch (GameState.getState()) {
                case PLAYING -> {
                    this.controller = this.gameController;
                }
                case MENU -> {
                    this.controller = new MenuControllerImpl();
                    this.gameController.newGame();
                }
                case SHOP -> {
                    this.controller = this.shopController;
                }
                case DEAD -> {
                    this.controller = new DeathController(gameController.getGame().getScore());
                    this.gameController.newGame();
                }
                case PAUSE -> {
                    this.controller = new PauseController(gameController);
                }
                default -> System.exit(0);
            }
            this.controller.init(frame);
            this.controller.loop();
        }
    }
}
