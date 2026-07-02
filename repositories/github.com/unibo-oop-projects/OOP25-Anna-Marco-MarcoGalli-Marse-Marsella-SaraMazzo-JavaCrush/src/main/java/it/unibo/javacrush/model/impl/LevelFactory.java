package it.unibo.javacrush.model.impl;

import java.util.List;
import java.util.Map;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.GameMatchContext;
import it.unibo.javacrush.model.api.GravityEngine;
import it.unibo.javacrush.model.api.LevelConfig;
import it.unibo.javacrush.model.impl.gravity.DownwardGravity;
import it.unibo.javacrush.model.impl.gravity.CrazyGravity;
import it.unibo.javacrush.model.impl.gravity.LeftwardGravity;
import it.unibo.javacrush.model.impl.gravity.RightwardGravity;
import it.unibo.javacrush.model.impl.gravity.UpwardGravity;
import it.unibo.javacrush.powerup.impl.PowerUpManagerImpl;

/**
 * Factory class for creating level configurations based on level number.
 */
public final class LevelFactory {

    private static final int ROWS_EASY = 10;
    private static final int ROWS_MEDIUM = 9;
    private static final int ROWS_HARD = 8;
    private static final int ROWS_VERY_HARD = 6;

    private static final int COLS_EASY = 10;
    private static final int COLS_MEDIUM = 9;
    private static final int COLS_HARD = 8;
    private static final int COLS_VERY_HARD = 3;

    private static final int MOVES_EASY = 20;
    private static final int MOVES_MEDIUM = 15;
    private static final int MOVES_HARD = 10;

    private static final int GOAL_EASY = 10;
    private static final int GOAL_MEDIUM = 15;
    private static final int GOAL_HARD = 20;

    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final int LEVEL_4 = 4;
    private static final int LEVEL_5 = 5;

    private LevelFactory() {
        // Prevent instantiation
    }

    /**
     * Creates a GameMatchContext based on the provided level number.
     * 
     * @param levelNumber the number of the level
     * @return a GameMatchContext object for the specified level
     */
    public static GameMatchContext createGameMatchContext(final int levelNumber) {
        final var levelConfig = createLevel(levelNumber);
        final var board = new BoardImpl(levelConfig.rows(), levelConfig.cols());
        final var physicsHandler = new PhysicsHandlerImpl(levelConfig.gravity(), new StallEngineImpl());
        final var moveEngine = new MoveEngineImpl();
        final var matchManager = new MatchManagerImpl();
        final var stallEngine = new StallEngineImpl();

        final var session = new SessionImpl(levelConfig.moves(), levelConfig.goals(), new GoalFactoryImpl());

        physicsHandler.initializeBoard(board);

        return new GameMatchContextImpl(board, physicsHandler, levelConfig, moveEngine, matchManager, stallEngine, session);
    }

    /**
     * Creates a LevelConfiguration based on the provided level number.
     * 
     * @param levelNumber the number of the level
     * @return a LevelConfig object containing the configuration for the specified level
     */
    public static LevelConfig createLevel(final int levelNumber) {
        return switch (levelNumber) {
            case LEVEL_1 -> new LevelConfig(COLS_EASY, ROWS_EASY, MOVES_EASY,
                Map.of(CellType.getRandomType(), GOAL_EASY),
                new DownwardGravity(),
                new PowerUpManagerImpl());

            case LEVEL_2 -> new LevelConfig(COLS_EASY, ROWS_EASY, MOVES_MEDIUM,
                Map.of(CellType.MILK, GOAL_EASY, CellType.SPOON, GOAL_MEDIUM),
                new DownwardGravity(),
                new PowerUpManagerImpl());

            case LEVEL_3 -> new LevelConfig(COLS_MEDIUM, ROWS_MEDIUM, MOVES_HARD,
                Map.of(CellType.CUP, GOAL_MEDIUM, CellType.COFFEE_BEAN, GOAL_MEDIUM),
                new DownwardGravity(),
                new PowerUpManagerImpl());

            case LEVEL_4 -> new LevelConfig(COLS_VERY_HARD, ROWS_VERY_HARD, MOVES_EASY,
                Map.of(CellType.getRandomType(), GOAL_EASY), 
                new DownwardGravity(),
                new PowerUpManagerImpl());

            case LEVEL_5 -> new LevelConfig(COLS_HARD, ROWS_HARD, MOVES_HARD,
                Map.of(CellType.getRandomType(), GOAL_HARD),
                new CrazyGravity(allGravities()),
                new PowerUpManagerImpl());

            default -> new LevelConfig(COLS_EASY, ROWS_EASY, MOVES_EASY,
                Map.of(CellType.getRandomType(), GOAL_MEDIUM),
                new DownwardGravity(),
                new PowerUpManagerImpl());
        };

    }

    private static List<GravityEngine> allGravities() {
        return List.of(
            new DownwardGravity(),
            new UpwardGravity(),
            new LeftwardGravity(),
            new RightwardGravity()
        );
    }
}
