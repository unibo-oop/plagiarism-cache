package snakerunner.model.impl;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import snakerunner.commons.Point2D;
import snakerunner.core.GameConfiguration;
import snakerunner.model.Collectible;
import snakerunner.model.CollectibleType;
import snakerunner.model.Door;
import snakerunner.model.GameModel;
import snakerunner.model.Level;
import snakerunner.model.LevelData;
import snakerunner.model.Snake;
import snakerunner.model.VictoryCondition;

/**
 * GameModelImpl class that implements the GameModel interface 
 * and represents the core game logic and state management for the Snake Runner game.
 */
public final class GameModelImpl implements GameModel {

    private static final int INITIAL_SPEED = GameConfiguration.INITIAL_SPEED;
    private static final int SLOW_EFFECT_DURATION = GameConfiguration.SLOW_EFFECT_DURATION;
    private static final int SLOW_EFFECT_SPEED = GameConfiguration.SLOW_EFFECT_SPEED;
    private static final int INITIAL_LIVES = GameConfiguration.INITIAL_LIVES;
    private static final Point2D<Integer, Integer> STARTING_POSITION = new Point2D<>(2, 10);
    private boolean isGameOver;
    private Level currentLevel;
    private Snake snake;
    private List<Collectible> collectibles;
    private boolean levelCompleted;
    private int score;
    private int speed;
    private int lives;
    private int slowEffectDuration;
    private List<Door> doors;
    private VictoryCondition victoryCondition;

    /**
     * GameModelImpl constructor.
     */
    public GameModelImpl() {
        currentLevel = null;
        snake = new Snake(STARTING_POSITION);
        collectibles = Collections.emptyList();
        levelCompleted = false;
        score = 0;
        speed = INITIAL_SPEED;
        lives = INITIAL_LIVES;
        slowEffectDuration = 0;
    }

    /**
     * Updates the game state by moving the snake, checking for collisions and collectibles,
     * and managing the slow effect duration.
     */
    @Override
    public void update() {
        if (isGameOver() || levelCompleted) {
            return;
        }

        snake.move();

        checkCollisions();
        checkCollectibles();

        //after every update, we check if 
        // the slow effect is active and decrease its duration
        if (slowEffectDuration > 0) {
            slowEffectDuration--;
            if (slowEffectDuration == 0) {
                speed = INITIAL_SPEED; 
            }
        }

        if (isGameOver) {
            resetAfterGameOver();
        }
    }

    /**
     * Checks if the game is over based on the player's lives.
     */
    @Override
    public boolean isGameOver() {
       return this.lives <= 0;

    }

    /**
     * Loads a new level into the game model using the provided LevelData,
     * initializing the level state.
     */
    @Override
    public void loadLevel(final LevelData data) {
        this.currentLevel = new LevelImpl(data);
        this.collectibles = data.getCollectibles();
        this.doors = data.getDoors();
        this.levelCompleted = false;
        this.victoryCondition = data.getVictoryCondition();
    }

    /**
     * Returns the current state of the snake, including its position and body segments.
     */
    @Override
    @SuppressFBWarnings
    public Snake getSnake() {
        return this.snake;
    }

    /**
     * Returns an unmodifiable list of collectibles currently present in the level.
     */
    @Override
    public List<Collectible> getCollectibles() {
        return Collections.unmodifiableList(collectibles);
    }

    /**
     * Returns a set of points representing the positions of obstacles in the current level.
     */
    @Override
    public Set<Point2D<Integer, Integer>> getObstacles() {
        /* Error control in case the current level is still null */
        if (currentLevel != null) {
            /* We get the coordinates */
            return currentLevel.getObstacles();
        }
        return Collections.emptySet(); /* In order to avoid errors we return an empty set of points */
    }

    /**
     * Checks if the current level has been completed 
     * based on the victory condition and game state.
     */
    @Override
    public boolean isLevelCompleted() {
        return this.levelCompleted;
    }

    /**
     * Marks the current level as completed, 
     * allowing the game to transition to the next level or end state.
     */
    @Override
    public void completeLevel() {
        this.levelCompleted = true;
    }

    /**
     * Adds points to the player's score based 
     * on the type of collectible consumed or other game events.
     */
    @Override
    public void addScore(final int points) {
        score += points;
    }

    /**
     * Returns the player's current score.
     */
    @Override
    public int getScore() {
        return score;
    }

    /**
     * Returns the number of lives the player has remaining.
     */
    @Override
    public int getLives() {
        return lives;
    }

    /**
     * Returns the current level object.
     */
    @Override
    public Level getLevel() {
        return this.currentLevel;
    }

    /**
     * Applies a slow effect to the snake, 
     * reducing its speed for a certain duration of updates.
     */
    @Override
    public void applySlowEffect() {
        speed = SLOW_EFFECT_SPEED;
        slowEffectDuration = SLOW_EFFECT_DURATION;
    }

    /**
     * Returns the current speed of the snake.
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * Opens all doors in the current level, allowing the snake to pass through them.
     */
    @Override
    public void openDoor() {
        for (final Door door : doors) {
            door.setOpen(true);
        }
    }

    /**
     * Resets the game state to its initial conditions.
     */
    @Override
    public void resetState() {
        this.snake = new Snake(STARTING_POSITION);
        this.collectibles = Collections.emptyList();
        this.levelCompleted = false;
        this.isGameOver = false;
        this.score = 0;
        this.speed = INITIAL_SPEED;
        this.slowEffectDuration = 0;
        this.lives = 3;
    }

    /**
     * Add a life to the snake after consuming a mushroom(Life Boost).
     */
    @Override
    public void addLife() {
        this.lives++;
    }

    private void checkCollisions() {
        final Point2D<Integer, Integer> head = snake.getHead();
        if (snake.isCollidingWithItself()) {
            handleCollision();
            return;
        }

        if (currentLevel.isBlocked(head)) {
            handleCollision();
            return;
        }
        if (doors != null) {
            for (final Door door : doors) {
                if (!door.isOpen() && door.getPosition().equals(head)) { 
                handleCollision();
                return;
                }
            }
        }
    }

    private void handleCollision() {
        this.lives--;
        if (this.lives > 0) {
            this.snake = new Snake(STARTING_POSITION);
        } else {
            this.isGameOver = true;
        }
    }

    private void checkCollectibles() {
        final Iterator<Collectible> iterator = collectibles.iterator();
        final Point2D<Integer, Integer> snakeHead = snake.getHead();

        while (iterator.hasNext()) {
            final Collectible c = iterator.next();

            if (c.getPosition().equals(snakeHead)) {
                c.consume(this);
                iterator.remove();
            }
        }
        if (victoryCondition == VictoryCondition.COLLECT_ALL_FOOD) {
            boolean hasFood = false;
            for (final Collectible c : collectibles) {
                if (c.getType() == CollectibleType.FOOD) {
                    hasFood = true;
                    break;
                }
            }
            if (!hasFood) {
                completeLevel();
            }
        }
    }

    private void resetAfterGameOver() {
        this.snake = new Snake(STARTING_POSITION);
        this.isGameOver = false;
        this.speed = INITIAL_SPEED;
        this.slowEffectDuration = 0;
    }

    @Override
    public void killSnake() {
        lives = 0;
    }
}
