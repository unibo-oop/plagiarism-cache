package it.unibo.scat.model;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import it.unibo.scat.common.Direction;
import it.unibo.scat.common.EntityState;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.common.GameResult;
import it.unibo.scat.common.GameState;
import it.unibo.scat.common.Observable;
import it.unibo.scat.common.Observer;
import it.unibo.scat.model.api.EntityFactory;
import it.unibo.scat.model.api.ModelInterface;
import it.unibo.scat.model.api.ModelState;
import it.unibo.scat.model.game.CollisionReport;
import it.unibo.scat.model.game.GameLogic;
import it.unibo.scat.model.game.GameWorld;
import it.unibo.scat.model.game.TimeAccumulator;
import it.unibo.scat.model.game.entity.EntityFactoryImpl;
import it.unibo.scat.model.leaderboard.Leaderboard;
import it.unibo.scat.model.leaderboard.LeaderboardInterface;

/**
 * The main class for the "Model" section of the MVC pattern.
 */
public final class Model implements ModelInterface, ModelState, Observable {
    private volatile Observer observer;
    private GameState gameState;
    private TimeAccumulator timeAccumulator;
    private final AtomicInteger score = new AtomicInteger(0);

    private String username;
    private LeaderboardInterface leaderboard;
    private GameWorld gameWorld;
    private GameLogic gameLogic;

    /**
     * Creates and initializes the list of entities and the entity object, by
     * reading them from file.
     * Creates and initializes the leaderboard.
     * 
     * @param entitiesFile    the file of entities.
     * @param leaderboardFile the leaderboard file.
     */
    @Override
    public void initEverything(final String entitiesFile, final String leaderboardFile) {
        final EntityFactory entityFactory = new EntityFactoryImpl();

        gameWorld = new GameWorld(entityFactory);
        gameLogic = new GameLogic(gameWorld, entityFactory);
        leaderboard = new Leaderboard(leaderboardFile);

        gameWorld.initEntities(entitiesFile);
        leaderboard.initLeaderboard();

        timeAccumulator = new TimeAccumulator(gameLogic.getDifficultyManager());
    }

    @Override
    public void update() {
        final CollisionReport collisionReport;
        final int newPoints;

        timeAccumulator.incrementTimeAccumulators();

        // Movements
        gameLogic.handleInvadersMovement(timeAccumulator.getInvadersAccMs());
        gameLogic.handleShotsMovement(timeAccumulator.getShotsAccMs());
        gameLogic.handleBonusInvader(timeAccumulator.getBonusInvaderAccMs());
        gameLogic.handleInvadersShooting();

        // Collisions
        collisionReport = gameLogic.checkCollisions();
        newPoints = gameLogic.handleCollisionReport(collisionReport);

        updateScore(newPoints);
        gameLogic.removeDeadShots();

        handleGameEnd(gameLogic.checkGameEnd());
        notifyObserver();
    }

    /**
     * Handles game end conditions: increases level on victory or
     * saves the score to the leaderboard on defeat.
     * 
     * @param gameResult the session outcome.
     */
    public void handleGameEnd(final GameResult gameResult) {
        if (gameResult == GameResult.PLAYING) {
            return;
        }

        if (gameResult == GameResult.PLAYER_WON) {
            increaseLevel();
            gameLogic.removeAllShots();
            gameLogic.resetInvaders();
        }

        if (gameResult == GameResult.INVADERS_WON) {
            setGameState(GameState.GAMEOVER);

            leaderboard.addNewGameRecord(username, gameLogic.getDifficultyManager().getLevel(), score.get());
            leaderboard.sortGames();
            leaderboard.updateFile();
        }
    }

    /**
     * Increses the level by one.
     */
    public void increaseLevel() {
        gameLogic.getDifficultyManager().incrementLevel();
    }

    /**
     * Updates the player's total score by adding the specified amount.
     * 
     * @param points the points to add to the current score.
     */
    public void updateScore(final int points) {
        this.score.addAndGet(points);
    }

    @Override
    public void addPlayerShot() {
        gameLogic.addPlayerShot();
        notifyObserver();
    }

    @Override
    public void movePlayer(final Direction direction) {
        if (gameLogic.canPlayerMove(direction)) {
            gameWorld.getPlayer().move(direction);
        }
        notifyObserver();
    }

    @Override
    public void resetGame() {
        gameLogic.resetEntities();
        if (this.gameWorld.getPlayer() != null) {
            this.gameWorld.getPlayer().reset();
        }
        score.set(0);
        gameLogic.getDifficultyManager().resetLevel();
        setGameState(GameState.PAUSE);
        notifyObserver();
    }

    @Override
    public void setGameState(final GameState state) {
        gameState = state;
    }

    @Override
    public GameState getGameState() {
        return gameState;
    }

    @Override
    public List<EntityState> getEntities() {
        synchronized (gameWorld.getEntities()) {
            return gameWorld.getEntities().stream()
                    .filter(EntityState::isAlive)
                    .map(EntityState.class::cast)
                    .toList();
        }
    }

    @Override
    public List<GameRecord> getLeaderboard() {
        return leaderboard.getAllRecords();
    }

    @Override
    public int getScore() {
        return score.get();
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(final String username) {
        this.username = username;
        notifyObserver();
    }

    @Override
    public int getPlayerHealth() {
        if (gameWorld.getPlayer() == null) {
            return 0;
        }

        return gameWorld.getPlayer().getHealth();
    }

    @Override
    public int getLevel() {
        return gameLogic.getDifficultyManager().getLevel();
    }

    @Override
    public void setObserver(final Observer o) {
        this.observer = o;
    }

    @Override
    public void notifyObserver() {
        if (this.observer == null) {
            throw new IllegalStateException("Observer is null in Model");
        }
        observer.update();
    }

    @Override
    public int getInvadersStepMs() {
        return gameLogic.getDifficultyManager().getInvadersStepMs();
    }

    @Override
    public int getInvadersAccMs() {
        return timeAccumulator.getInvadersAccMs();
    }

    @Override
    public int getBonusInvaderAccMs() {
        return timeAccumulator.getBonusInvaderAccMs();
    }
}
