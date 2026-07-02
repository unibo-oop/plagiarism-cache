package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.InputManager;
import it.unibo.javajump.controller.input.InputManagerImpl;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.GameModelImpl;
import it.unibo.javajump.model.GameModelObserver;
import it.unibo.javajump.view.GameFrame;
import it.unibo.javajump.view.GameFrameImpl;
import it.unibo.javajump.view.MainGameView;
import it.unibo.javajump.view.MainGameViewImpl;

import static it.unibo.javajump.utility.Constants.GAME_TITLE;
import static it.unibo.javajump.utility.Constants.SCREEN_HEIGHT;
import static it.unibo.javajump.utility.Constants.SCREEN_WIDTH;

/**
 * Class implementation of the GameInitializer interface, for setting-up the managers and starting the game loop.
 */
public class GameInitializerImpl implements GameInitializer {
    /**
     * The model, which contains all data & logic related aspects of the game.
     */
    private final GameModel model;
    /**
     * The game frame, to visualize the game within a window when run.
     */
    private final GameFrame frame;
    /**
     * The view, which contains all visual(UI, UX) aspects of the game.
     */
    private final MainGameView view;
    /**
     * The responsible for inputs given to the game by the player.
     */
    private final InputManager inputManager;
    /**
     * The controller, which mediates between player and model, and updates model & view accordingly.
     */
    private final GameController controller;

    /**
     * Constructor for GameInitializerImpl, which associates all interfaces to actual implementations.
     */
    public GameInitializerImpl() {
        this.model = new GameModelImpl(SCREEN_WIDTH, SCREEN_HEIGHT);
        this.frame = new GameFrameImpl();
        this.view = new MainGameViewImpl(model);
        this.inputManager = new InputManagerImpl();
        this.controller = new GameControllerImpl(view, inputManager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize() {
        model.addObserver((GameModelObserver) view);
        frame.setUp(inputManager, SCREEN_HEIGHT, SCREEN_WIDTH, view, GAME_TITLE);
        controller.startGameLoop(model, frame);
    }

}
