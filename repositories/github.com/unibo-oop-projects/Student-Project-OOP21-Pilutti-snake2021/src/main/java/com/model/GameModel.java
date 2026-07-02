package main.java.com.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import main.java.com.utility.Direction;
import main.java.com.utility.Pos;
import main.java.com.utility.Position;

/**
 * This class represents the game's model entry point. Implements the
 * {@link Model} interface.
 */
public class GameModel implements Model {

    private static final Direction START_DIR = Direction.UP;
    private static final int INITIAL_BODY_LENGTH = 5;
    private static final int X_MAP_SIZE = 21;
    private static final int Y_MAP_SIZE = 21;
    private static final Position SNAKE_START_POSITION = new Pos(X_MAP_SIZE / 2, (Y_MAP_SIZE / 3) * 2);

    private final SnakeEntity snake;
    private final EatableEntity apple;
    private final GameMap gameMap;
    private int score;

    /**
     * Constructor that initializes all the model's entities.
     */
    public GameModel() {
        snake = new Snake.SnakeBuilder().direction(START_DIR)
                                        .headPosition(SNAKE_START_POSITION)
                                        .body(getInitialSnake())
                                        .mapSize(X_MAP_SIZE, Y_MAP_SIZE)
                                        .build();
        final Set<Position> s = new HashSet<>();
        for (int i = 0; i <= X_MAP_SIZE; i++) {
            for (int j = 0; j <= Y_MAP_SIZE; j++) {
                s.add(new Pos(i, j));
            }
        }
        gameMap = new GameMapImpl(s, X_MAP_SIZE, Y_MAP_SIZE);
        apple = new Apple(randomApplePos());
        score = 0;
    }

    /** {@inheritDoc} */
    public SnakeEntity getSnake() {
        return snake;
    }

    /** {@inheritDoc} */
    public EatableEntity getApple() {
        return apple;
    }

    /** {@inheritDoc} */
    public GameMap getGameMap() {
        return gameMap;
    }

    /** {@inheritDoc} */
    public int getScore() {
        return score;
    }

    /** {@inheritDoc} */
    @Override
    public void eatApple() {
        incScore(apple.getPointsValue());
        snake.increaseLength();
        apple.incrementEatenCounter();
        apple.setPosition(randomApplePos());
    }

    /** {@inheritDoc} */
    @Override
    public void resetGame() {
        snake.setBodyPosition(getInitialSnake());
        snake.setLength(INITIAL_BODY_LENGTH);
        snake.resetDirection();
        apple.setPosition(randomApplePos());
        apple.resetPoints();
        score = 0;
    }

    /**
     * @return a new random position in the game map that doesn't overlap with
     *         snake.
     */
    private Position randomApplePos() {
        final Random rand = new Random();
        int x = rand.nextInt(gameMap.getXMapSize());
        int y = rand.nextInt(gameMap.getYMapSize());
        // Randomize position until you get one that does not overlap with snake or
        // walls.
        while (snake.getBodyPosition().contains(new Pos(x, y))) {
            x = rand.nextInt(gameMap.getXMapSize());
            y = rand.nextInt(gameMap.getYMapSize());
        }
        return new Pos(x, y);
    }

    /**
     * 
     * @return a list representing the initial snake.
     */
    private List<Position> getInitialSnake() {
        final List<Position> initBody = new ArrayList<>();
        for (int i = 0; i < INITIAL_BODY_LENGTH; i++) {
            initBody.add(new Pos(SNAKE_START_POSITION.getX(), SNAKE_START_POSITION.getY() + i));
        }
        return initBody;
    }

    private void incScore(final int value) {
        score += value;
    }
}
