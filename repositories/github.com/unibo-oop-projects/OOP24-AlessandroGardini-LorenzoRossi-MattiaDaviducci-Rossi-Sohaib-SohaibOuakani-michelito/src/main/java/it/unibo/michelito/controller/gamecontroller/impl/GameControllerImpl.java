package it.unibo.michelito.controller.gamecontroller.impl;

import it.unibo.michelito.controller.gamecontroller.api.GameController;
import it.unibo.michelito.controller.gamecontroller.api.GameExceptionHandler;
import it.unibo.michelito.controller.gamecontroller.movecommandbuilder.api.MoveCommandBuilder;
import it.unibo.michelito.controller.gamecontroller.movecommandbuilder.impl.MoveCommandBuilderImpl;
import it.unibo.michelito.controller.gamecontroller.keybinds.KeyBinds;
import it.unibo.michelito.controller.levelgenerator.LevelGenerator;
import it.unibo.michelito.controller.maincontroller.api.GameParentController;
import it.unibo.michelito.controller.playercommand.impl.PlaceCommand;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.gamemanager.impl.GameManagerImpl;
import it.unibo.michelito.util.Direction;
import it.unibo.michelito.view.gameview.frame.api.GameView;
import it.unibo.michelito.view.gameview.frame.impl.GameViewImpl;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class that implements the {@link GameController} and {@link GameExceptionHandler} interface.
 */
public class GameControllerImpl implements GameController, GameExceptionHandler {
    private static final int FPS = 60;
    private static final long TIME_PER_TICK = (long) 1_000.0 / FPS;

    private final GameParentController gameParentController;

    private boolean game;
    private GameManager gameManager;
    private GameView gameView;

    /**
     * Constructor for the {@link GameControllerImpl}.
     *
     * @param gameParentController the {@link GameParentController} associated.
     */
    public GameControllerImpl(final GameParentController gameParentController) {
        this.gameParentController = gameParentController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame() {
        try {
            this.gameView = new GameViewImpl();
            final Loop looper = new Loop();
            gameManager = new GameManagerImpl(new LevelGenerator(this));
            this.game = true;
            gameView.setViewVisibility(true);
            looper.start();
        } catch (IllegalThreadStateException e) {
            gameParentController.handleException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopGame() {
        this.game = false;
        if (gameView != null) {
            gameView.setViewVisibility(false);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quit() {
        gameParentController.quit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void handleException(final Exception exception) {
        gameParentController.handleException(exception);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void gameControllerHandleException(final Exception exception) {
       handleException(exception);
    }

    private final class Loop extends Thread {
        /**
         * {@inheritDoc}
         */
        @Override
        public void run() {
            long previousTime = System.currentTimeMillis();
            if (gameView == null || gameManager == null) {
                throw new IllegalStateException("GameView or GameManager not initialized before game loop started.");
            }
            while (game && gameView.isViewShowing()) {
                final long currentTime = System.currentTimeMillis();
                final long deltaTime = currentTime - previousTime;

                this.processInput(gameManager, gameView);

                gameManager.update(deltaTime);

                gameView.display(gameManager.getObjects(), gameManager.getRemainingLives(), gameManager.getCurrentIndexLevel());

                if (gameManager.isGameOver()) {
                    game = false;
                }
                if (gameManager.isGameWon()) {
                    game = false;
                }

                previousTime = currentTime;

                this.waitForNextFrame(currentTime);
            }
            gameParentController.switchToHome();
        }

        private void waitForNextFrame(final long currentTime) {
            final long dt = System.currentTimeMillis() - currentTime;
            if (dt < TIME_PER_TICK) {
                try {
                    sleep(TIME_PER_TICK - dt);
                } catch (InterruptedException ex) {
                    currentThread().interrupt();
                    gameParentController.handleException(ex);
                }
            }
        }

        private void processInput(final GameManager gameManager, final GameView gameView) {
            final MoveCommandBuilder commandBuilder = new MoveCommandBuilderImpl();
            final Set<KeyBinds> pressedKeys = gameView.getPressedKeys().stream()
                    .map(KeyBinds::getKeyBinds)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet());

            for (final KeyBinds keyBinds : pressedKeys) {
                switch (keyBinds) {
                    case UP -> commandBuilder.addDirection(Direction.UP);
                    case DOWN -> commandBuilder.addDirection(Direction.DOWN);
                    case RIGHT -> commandBuilder.addDirection(Direction.RIGHT);
                    case LEFT -> commandBuilder.addDirection(Direction.LEFT);
                    case PLACE_BOMB -> gameManager.setCommand(new PlaceCommand());
                    default -> { }
                }
            }
            gameManager.setCommand(commandBuilder.build());
        }
    }
}
