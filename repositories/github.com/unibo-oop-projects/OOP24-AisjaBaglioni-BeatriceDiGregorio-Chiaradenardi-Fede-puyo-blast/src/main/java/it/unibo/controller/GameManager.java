package it.unibo.controller;

import it.unibo.GameListener;
import it.unibo.model.ModelStorage;
import it.unibo.model.Scale;
import it.unibo.view.GameView;

import javax.swing.*;

/**
 * The {@code GameManager} class is responsible for initializing and managing the core game components.
 * It extends {@link JInternalFrame} and serves as a container for the game view and its associated logic.
 * 
 * The class initializes the game model, controllers, and view, and ensures that they are properly linked.
 * Once instantiated, it starts the game loop and adds the graphical representation to the UI.
 */
public class GameManager extends JInternalFrame {

    /** The storage for the game model, responsible for maintaining the game state. */
    private final ModelStorage modelStorage;

    /** The storage for the game controllers, handling user interactions and game logic. */
    private final ControllerStorage controllerStorage;

    /** The graphical representation of the game, responsible for rendering the game state. */
    private final GameView gameView;

    /**
     * Constructs a {@code GameManager} instance, initializing the game components and starting the game.
     * 
     * @param gameListener the listener that handles game-related events and interactions
     * @param scale the scale factor used to define game dimensions and rendering properties
     * @param levelConfig the configuration settings for the current level, including difficulty and environment parameters
     */
    public GameManager(GameListener gameListener, Scale scale, LevelManager.LevelConfig levelConfig) {
        /**
         *  Removes the title bar from the internal frame
         */
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        this.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));

        /**
         * Initializes the model storage, responsible for managing game data
         */
        this.modelStorage = new ModelStorage(scale);

        /**
         * Initializes the controller storage, which handles user inputs and game mechanics
         */
        this.controllerStorage = new ControllerStorage(modelStorage, gameListener, levelConfig);

        /**
         * Initializes the game view, which is responsible for rendering the game
         */
        this.gameView = new GameView(modelStorage, controllerStorage);

        /**
         * Links the controller storage to the game view
         */
        this.controllerStorage.linkGameView(gameView);

        /**
         * Starts the game logic
         */
        controllerStorage.start();

        /**
         * Adds the game view to the internal frame and makes it visible
         */
        this.add(gameView);
        gameView.setVisible(true);
    }
}
