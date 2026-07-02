package it.unibo.javajump.controller;

import it.unibo.javajump.controller.input.GameAction;
import it.unibo.javajump.controller.input.InputManager;
import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.view.GameFrame;
import it.unibo.javajump.view.MainGameView;

import static it.unibo.javajump.utility.Constants.FPS;
import static it.unibo.javajump.utility.Constants.NANOSECONDS_PER_SECOND;
import static it.unibo.javajump.utility.Constants.NULL_DIRECTION;
import static it.unibo.javajump.utility.Constants.SLEEP_THREAD;

/**
 * Implementation of GameController interface.
 */
public class GameControllerImpl implements GameController {
    /**
     * Private field to access the view.
     */
    private final MainGameView view;
    /**
     * Flag to set the current state of the GameLoop thread.
     */
    private volatile boolean running;
    /**
     * Private field to access the player inputs.
     */
    private final InputManager inputManager;

    /**
     * Constructor for the GameControllerImpl class.
     *
     *
     * @param view         The game view
     * @param inputManager The input manager
     *
     */
    public GameControllerImpl(final MainGameView view,
                              final InputManager inputManager) {

        this.view = view;
        this.running = false;
        this.inputManager = inputManager;
    }

    /**
     * Starts the GameLoop in a separate thread (~60 FPS).
     *
     * @param model the GameModel to update
     * @param frame the GameFrame to update
     */
    @Override
    public void startGameLoop(final GameModel model, final GameFrame frame) {
        running = true;
        final Thread loopThread = new Thread(() -> {
            long previousTime = System.nanoTime();
            final double nsPerFrame = NANOSECONDS_PER_SECOND / FPS;

            while (running) {
                final long currentTime = System.nanoTime();
                final double elapsedNs = currentTime - previousTime;
                if (elapsedNs >= nsPerFrame) {
                    final float deltaTime = (float) (elapsedNs / NANOSECONDS_PER_SECOND);
                    processDiscreteInput(model);
                    updateModel(deltaTime, model, frame);
                    view.updateView();
                    previousTime = currentTime;
                }

                try {
                    Thread.sleep(SLEEP_THREAD);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            Thread.currentThread().interrupt();
        });
        loopThread.start();
    }

    /**
     * Stops the GameLoop thread.
     *
     * @param frame the GameFrame to close
     */
    private void stopGameLoop(final GameFrame frame) {
        running = false;
        frame.closeGame();
    }

    /**
     * Private method to update the model regarding the current pressed direction.
     *
     * @param model the Model to update
     * @param deltaTime time passed since last update (in seconds)
     * @param frame the GameFrame to stop game loop
     */
    private void updateModel(final float deltaTime, final GameModel model, final GameFrame frame) {
        if (model.isRunning()) {
            final int horizontalDirection = inputManager.getHorizontalDirection();
            if (horizontalDirection < NULL_DIRECTION) {
                model.handleAction(GameAction.MOVE_LEFT);
            } else if (horizontalDirection > NULL_DIRECTION) {
                model.handleAction(GameAction.MOVE_RIGHT);
            } else {
                model.handleAction(GameAction.STOP_HORIZONTAL);
            }
            model.update(deltaTime);
        } else {
            stopGameLoop(frame);
        }
    }

    /**
     * Private method to process the GameAction(s) stored in the queue,
     * it demands the model to process them accordingly.
     *
     * @param model the GameModel to give the handling to
     */
    private void processDiscreteInput(final GameModel model) {
        GameAction action = inputManager.getAction();
        while (action != null) {
            model.handleAction(action);
            action = inputManager.getAction();
        }
    }

}
