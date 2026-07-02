package it.unibo.michelito.model.gamemanager.impl;

import it.unibo.michelito.controller.playercommand.api.PlayerCommand;
import it.unibo.michelito.model.gamemanager.api.GameManager;
import it.unibo.michelito.model.maze.api.Level;
import it.unibo.michelito.model.maze.impl.MazeImpl;
import it.unibo.michelito.util.GameObject;

import java.util.Set;
import java.util.function.Function;

/**
 * Implementation of {@link GameManager} interface, responsible for managing
 * levels, lives and current game state.
 */
public final class GameManagerImpl implements GameManager {
    private static final int STARTER_MAZE = 1;
    private static final int MAX_MAZE_INDEX = 3;
    private static final int STARTING_LIFE_COUNT = 5;

    private final Function<Integer, Set<GameObject>> levelGenerator;

    private int currentLevelIndex = STARTER_MAZE;
    private int currentLives;
    private Level currentLevel;

    private boolean gameOver;
    private boolean gameWon;

    /**
     * Construct a GameManager with a Function that produce the relative Set of {@link GameObject}.
     *
     * @param levelGenerator {@link Function} that, given a level index, generates the corresponding {@link GameObject}.
     */
    public GameManagerImpl(final Function<Integer, Set<GameObject>> levelGenerator) {
        this.currentLives = STARTING_LIFE_COUNT;
        this.levelGenerator = levelGenerator;
        this.currentLevel = createMaze(this.currentLevelIndex);
    }

    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    @Override
    public boolean isGameWon() {
        return this.gameWon;
    }

    @Override
    public Set<GameObject> getObjects() {
        return this.currentLevel.getGameObjects();
    }

    @Override
    public int getRemainingLives() {
        return this.currentLives;
    }

    @Override
    public int getCurrentIndexLevel() {
        return this.currentLevelIndex;
    }

    @Override
    public void setCommand(final PlayerCommand playerCommand) {
        this.currentLevel.setCommand(playerCommand);
    }

    @Override
    public void update(final long deltaTime) {
        this.currentLevel.update(deltaTime);
        if (this.currentLevel.isLost()) {
            this.loseLife();
        }
        if (this.currentLevel.isWon()) {
            this.wonMaze();
        }
    }

    /**
     * Handles the event of a Michelito's death.
     */
    private void loseLife() {
        if (this.currentLives == 0) {
            this.gameOver = true;
        } else {
            this.currentLives--;
            this.currentLevel = createMaze(currentLevelIndex);
        }
    }

    /**
     * Handles the event when Michelito complete a level.
     */
    private void wonMaze() {
        if (this.currentLevelIndex >= MAX_MAZE_INDEX) {
            this.gameWon = true;
        } else {
            this.currentLevelIndex++;
            this.currentLevel = createMaze(this.currentLevelIndex);
        }
    }

    /**
     * Simple create method for {@link MazeImpl}.
     *
     * @param levelIndex is the {@code currentLevelIndex}.
     * @return a {@link Level}.
     */
    private Level createMaze(final int levelIndex) {
        return new MazeImpl(levelIndex, levelGenerator);
    }
}
