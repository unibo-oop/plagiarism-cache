package it.unibo.core.impl;

import it.unibo.core.api.GameEngine;
import it.unibo.utilities.GameState;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.controller.impl.GameController;
import it.unibo.view.impl.EndGameView;
import it.unibo.view.impl.WinGameView;
import it.unibo.view.impl.WindowGame;
import javafx.stage.Stage;

/**
 * Implementation of the game engine.
 */
public class GameEngineImpl implements GameEngine {

    private final GameController gameController;
    private boolean isEndShown;
    private boolean isWinShown;
    private static final Logger LOGGER = Logger.getLogger(GameEngineImpl.class.getName());

    /**
     * Constructor for the game engine.
     */
    public GameEngineImpl() {
        this.gameController = new GameController();
    }

    /**
     * Loop of the game.
     * 
     * @param windowGame the main window of the game.
     */
    @Override
    public void gameLoop(final WindowGame windowGame) {
        if (gameController.gameIsNotOver() && !gameController.isWin()
                && GameState.getGameState().equals(GameState.PLAYING)) {
            this.update();
            this.draw(windowGame);
        }
        if (!gameController.gameIsNotOver() && !isEndShown) {
            GameState.setGameState(GameState.GAMEOVER);
            try {
                final Stage currentStage = windowGame.getStage();
                new EndGameView().start(new Stage());
                currentStage.close();
                isEndShown = true;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
        }
        if (gameController.isWin() && !isWinShown) {
            GameState.setGameState(GameState.WIN);
            try {
                final Stage currentStage = windowGame.getStage();
                new WinGameView().start(new Stage());
                currentStage.close();
                isWinShown = true;
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "An error occurred", e);
            }
        }
    }

    /**
     * Draw the game.
     * 
     * @param windowGame the main window of the game.
     */
    @Override
    public void draw(final WindowGame windowGame) {
        windowGame.update();
        windowGame.updateBird();
        windowGame.updateCake();
    }

    /**
     * Update the game.
     */
    @Override
    public void update() {
        gameController.update();
    }

    /**
     * Getter of the GameController.
     * 
     * @return the GameController.
     */
    @SuppressFBWarnings(value = "EI_EXPOSE_REP", justification = "We need the original object")
    public GameController getGameController() {
        return this.gameController;
    }
}
