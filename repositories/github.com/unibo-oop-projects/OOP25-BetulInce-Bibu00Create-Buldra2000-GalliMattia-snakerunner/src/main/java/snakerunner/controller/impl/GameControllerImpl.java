package snakerunner.controller.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.swing.Timer;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import snakerunner.controller.GameController;
import snakerunner.core.GameConfiguration;
import snakerunner.core.StateGame;
import snakerunner.graphics.MainFrame;
import snakerunner.graphics.hud.BaseHUD;
import snakerunner.model.Direction;
import snakerunner.model.GameModel;
import snakerunner.model.LevelData;
import snakerunner.model.impl.LevelLoader;

/**
 * GameControllerImpl define methods for GameController'Interface.
 */
public final class GameControllerImpl implements GameController {

    private static final int MAX_LEVEL = GameConfiguration.MAX_LEVEL;
    private static final int INITIAL_LEVEL = GameConfiguration.INITIAL_LEVEL;
    private static final int INITIAL_TIME = GameConfiguration.INITIAL_TIME;
    private StateGame state;
    private Timer gameLoopTimer;
    private BaseHUD timerView;
    private BaseHUD scoreView;
    private BaseHUD levelView;
    private BaseHUD lifeView;
    private final MainFrame mainFrame;
    private final GameModel gameModel;
    private int currentLevel = INITIAL_LEVEL;
    private int timeLeft;

    /**
     * Constructor for GameControllerImpl.
     *
     * @param mainFrame the main frame of the game.
     * @param gameModel the game model that contains the game state and logic.
     */
    @SuppressFBWarnings
    public GameControllerImpl(final MainFrame mainFrame, final GameModel gameModel) {
        this.mainFrame = mainFrame;
        this.gameModel = gameModel;
        this.state = StateGame.MENU;
        initGameLoop(gameModel.getSpeed());
    }

    //Creation components

    @Override
    public void moveUp() {
    if (state == StateGame.RUNNING) {
        gameModel.getSnake().setDirection(Direction.UP);
    }
}

    @Override
    public void moveDown() {
        if (state == StateGame.RUNNING) {
            gameModel.getSnake().setDirection(Direction.DOWN);
        }
    }

    @Override
    public void moveLeft() {
        if (state == StateGame.RUNNING) {
            gameModel.getSnake().setDirection(Direction.LEFT);
        }
    }

    @Override
    public void moveRight() {
        if (state == StateGame.RUNNING) {
            gameModel.getSnake().setDirection(Direction.RIGHT);
        }
    }

    /**
     * Starts the game by initializing the timer, loading the current level,
     * and updating the HUD.
     */
    @Override
    public void start() {
        timeLeft = INITIAL_TIME;
        loadCurrentLevel();

        timerView.setValue(timeLeft);
        scoreView.setValue(gameModel.getScore());
        gameLoopTimer.start();
        state = StateGame.RUNNING;
    }

    /**
     * Pauses the game if it is currently running by stopping the game loop timer.
     */
    @Override
    public void pause() {
        if (state == StateGame.RUNNING) {
            state = StateGame.PAUSED;
            gameLoopTimer.stop();
        }
    }

    /**
     * Resumes the game if it is currently paused by restarting.
     * the game loop timer and updating the game state to RUNNING.
     */
    @Override
    public void resume() {
        if (state == StateGame.PAUSED) {
            state = StateGame.RUNNING;
            gameLoopTimer.restart();

        }
    }

    /**
     * Updates the game state by calling the update method of the game model,
     * checking for game over and level completion conditions,
     * and updating the HUD and main frame accordingly.
     */
    @Override
    public void updateGame() {
        if (state != StateGame.RUNNING) {
            return;
        }

        gameModel.update();
        timeLeft--;

        setTimerDelay(gameModel.getSpeed());

        if (gameModel.isGameOver() || timeLeft == 0) {
            gameLoopTimer.stop();
            currentLevel = INITIAL_LEVEL;
            state = StateGame.GAME_OVER;
            mainFrame.lose();
            return;
        } else if (gameModel.isLevelCompleted()) {
            handleLevelCompleted();
            }

        //view Render
        updateHUD();
        mainFrame.refresh();
    }

    /**
     * Loads a level from a file and updates the game model with the new level data.
     */
    @SuppressFBWarnings
    @Override
    public void loadLevelFromFile(final String filePath) {
        try (InputStream is = LevelLoader.class
                .getClassLoader()
                .getResourceAsStream(filePath)) {

            if (is == null) {
                throw new IllegalArgumentException("File livello non trovato: " + filePath);
            }

            final List<String> lines = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))
                    .lines()
                    .toList();

            final LevelData level = LevelLoader.load(lines);
            gameModel.loadLevel(level);

        } catch (final IOException e) {
            throw new IllegalStateException("Errore caricamento livello", e);
        }
    }

    /**
     * Sets the delay of the game loop timer
     * after collecting a power-up.
     *
     * @param delay the new delay for the game loop timer in milliseconds.
     */
    private void setTimerDelay(final int delay) {
        gameLoopTimer.setDelay(delay);
    }

    private void updateHUD() {
        timerView.setValue(timeLeft);
        scoreView.setValue(gameModel.getScore());
        levelView.setValue(currentLevel);
        lifeView.setValue(gameModel.getLives());

    }

    private void loadCurrentLevel() {
        gameModel.resetState();
        final String filePath = "levels/level" + currentLevel + ".txt";
        loadLevelFromFile(filePath);
    }

    private void nextLevel() {
        currentLevel++;
        if (currentLevel > MAX_LEVEL) {
            currentLevel = 1;
            mainFrame.won();
            state = StateGame.MENU;
        } else {
            mainFrame.levelComplete();
        }
    }

    private void initGameLoop(final int delay) {
        gameLoopTimer = new Timer(delay, e -> {
            updateGame();
        });
    }

    @SuppressFBWarnings
    @Override
    public void setHUD(final BaseHUD timer, final BaseHUD score, final BaseHUD level, final BaseHUD life) {
        this.timerView = timer;
        this.scoreView = score;
        this.levelView = level;
        this.lifeView = life;
    }

    private void handleLevelCompleted() {
        gameLoopTimer.stop();
        nextLevel();
        loadCurrentLevel();

        gameLoopTimer.start();
    }
}


