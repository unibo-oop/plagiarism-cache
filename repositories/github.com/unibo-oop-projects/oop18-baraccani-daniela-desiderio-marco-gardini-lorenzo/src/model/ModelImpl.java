package model;

import java.awt.Rectangle;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

import controller.gameloop.GameController;
import model.entities.PowerUp;
import model.entitiesutil.Entity;
import model.entitiesutil.EntityDirection;
import model.entitiesutil.EntityMovable;
import model.levels.GameLevel;
import model.levels.LevelUtils;

/**
 * Manage every logic part of the game.
 */
public class ModelImpl implements Model {

    private static final int STARTING_LIVES = 3;
    private static final int STARTING_LEVEL = 1;
    private static final int STARTING_SCORE = 0;
    private static final int POWERUP_SPAWNING_TICKS = 5 * GameController.FPS; // Duration (s) * FPS
    private static final double POWERUP_SIZE_FACTOR = 0.4; // * hero size
    private Optional<String> playerName;
    private GameController controller;
    private GameLevel level;
    private int levelNumber;
    private boolean gameOver;
    private int lives;
    private int score;
    private int powerUpSpawningTicks;

    /**
     * The manager of every logic part of the game.
     */
    public ModelImpl() {
        this.reset();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.playerName = Optional.empty();
        this.levelNumber = STARTING_LEVEL - 1;
        this.gameOver = false;
        this.lives = STARTING_LIVES;
        this.score = STARTING_SCORE;
        this.powerUpSpawningTicks = POWERUP_SPAWNING_TICKS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final GameController controller) {
        if (Objects.nonNull(this.controller)) {
            throw new IllegalStateException("The controller can be set just once");
        } else {
            this.controller = Objects.requireNonNull(controller);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameController getController() {
        return this.controller;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void newGame() {
        this.reset();
        this.levelNumber = STARTING_LEVEL;
        this.startLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startLevel() {
        this.level = LevelUtils.importLevel(this, this.levelNumber);
        if (this.controller.isViewVisible()) {
            this.controller.newLevelLoaded();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void nextLevel() {
        this.levelNumber++;
        if (Objects.nonNull(LevelUtils.getLevelStream(this.levelNumber))) {
            this.startLevel();
            this.controller.newLevelLoaded();
        } else {
            this.processVictory();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void executeLoopCycle() {
        // Manage Hero movements
        if (Objects.nonNull(this.controller)) {
            this.controller.getExecutedActions().forEach(this.getLevel().getHero()::move);
        }
        // Manage other entities movements
        for (final Entity entity : this.level.getEntities()) {
            // Only movable entities must do next step
            if (entity.getClass().getSuperclass().getSimpleName().equals("EntityMovable")) {
                ((EntityMovable) entity).doStep();
            }
        }
        // Check collisions
        this.level.getCollisionManager().collision();
        // Call after-collision checker method on entities
        for (final Entity entity : this.level.getEntities()) {
            if (entity.getClass().getSuperclass().getSimpleName().equals("EntityMovable")) {
                ((EntityMovable) entity).doAfterCollisionStep();
            }
        }
        // PowerUp appear manager
        if (this.powerUpSpawningTicks > 0) {
            this.powerUpSpawningTicks--;
            if (this.powerUpSpawningTicks == 0) {
                int minX = 0;
                int maxX = 0;
                int minY = 0;
                int maxY = 0;
                boolean first = true;
                for (final Entity entity : this.level.getEntities()) {
                    if (first || entity.getX() < minX) {
                        minX = entity.getX();
                    }
                    if (first || entity.getX() > maxX) {
                        maxX = entity.getX();
                    }
                    if (first || entity.getY() < minY) {
                        minY = entity.getY();
                    }
                    if (first || entity.getY() > maxY) {
                        maxY = entity.getY();
                    }
                    if (first) {
                        first = false;
                    }
                }
                boolean positionAvailable = true;
                int powerUpX = 0;
                int powerUpY = 0;
                final int powerUpWidth = new Double(this.level.getHero().getWidth() * POWERUP_SIZE_FACTOR).intValue();
                final int powerUpHeight = new Double(this.level.getHero().getHeight() * POWERUP_SIZE_FACTOR).intValue();
                maxX -= powerUpWidth;
                maxY -= powerUpHeight;
                final Random rand = new Random();
                do {
                    positionAvailable = true;
                    powerUpX = rand.nextInt(maxX - minX) + minX;
                    powerUpY = rand.nextInt(maxY - minY) + minY;
                    for (final Entity entity : this.level.getEntities()) {
                        if (new Rectangle(entity.getX(), entity.getY(), entity.getWidth(), entity.getHeight())
                                .intersects(new Rectangle(powerUpX, powerUpY, powerUpWidth, powerUpHeight))) {
                            positionAvailable = false;
                            break;
                        }
                    }
                } while (!positionAvailable);
                this.level.addEntity(
                        new PowerUp(this, powerUpX, powerUpY, powerUpWidth, powerUpHeight, EntityDirection.DOWN_LEFT));
                this.powerUpSpawningTicks = POWERUP_SPAWNING_TICKS;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processDeath() {
        this.lives--;
        if (lives > 0) {
            // RESTARTA IL LIVELLO
            if (Objects.nonNull(this.controller)) {
                this.startLevel();
            }
        } else {
            this.gameOver = true;
            if (Objects.nonNull(this.controller)) {
                this.getController().gameOver();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processVictory() {
        if (Objects.nonNull(this.controller)) {
            this.getController().victory();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addPoints(final int amount) {
        this.score += amount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getScore() {
        return this.score;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getLives() {
        return this.lives;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameLevel getLevel() {
        return this.level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setLevel(final GameLevel gameLevel) {
        this.level = gameLevel;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isGameOver() {
        return this.gameOver;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPlayerName(final String playerName) {
        this.playerName = Optional.of(playerName);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPlayerName() {
        return (this.playerName.isPresent() ? this.playerName.get() : "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void enemyDied() {
        boolean foundEnemy = false;
        for (final Entity entity : this.level.getEntities()) {
            if (entity.getType().equals("Enemy")) {
                foundEnemy = true;
                break;
            }
        }
        if (!foundEnemy) {
            this.nextLevel();
        }
    }

}
