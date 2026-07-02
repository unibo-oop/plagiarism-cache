package it.unibo.arkanoid.model;

import it.unibo.arkanoid.subject.Subject;
import it.unibo.arkanoid.utility.Vector2D;

/**
 * The implementation of the {@link Model} interface.
 *
 */
public final class ModelImpl implements Model {

    private static final int PLAYER_LIFE = 3;
    private static final double GAME_WORLD_WIDTH = 160;
    private static final double GAME_WORLD_HEIGHT = 90;

    private int score;
    private int playerLife;
    private Level level;

    /**
     * ModelImpl constructor.
     */
    public ModelImpl() {
        this.playerLife = PLAYER_LIFE;
        this.score = 0;
    }

    @Override
    public int getPlayerLife() {
        return this.playerLife;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public Level getCurrentLevel() {
        return this.level;
    }

    @Override
    public void updateAll(final double deltaTime) {
        this.level.checkCollisions();
        this.level.updateAll(deltaTime);
    }

    @Override
    public double getGameWorldWidth() {
        return GAME_WORLD_WIDTH;
    }

    @Override
    public double getGameWorldHeight() {
        return GAME_WORLD_HEIGHT;
    }

    @Override
    public void setPaddlePosition(final double coordinate) {
        final Subject paddle = this.level.getPaddle();
        final double halfLimit = (GAME_WORLD_WIDTH / 2) - (paddle.getWidth() / 2);
        double realX = Math.min(halfLimit, coordinate);
        realX = Math.max(-halfLimit, realX);
        paddle.setPosition(new Vector2D(realX, paddle.getPosition().getY()));
    }

    @Override
    public void setLevel(final Level level) {
        this.level = level;
        this.level.getScoreChangedEvent().register(s -> this.score += s);
        this.level.getLevelFinishedEvent().register(this::onLevelFinished);
    }

    private void onLevelFinished(final LevelState state) {
        if (state == LevelState.LOSE) {
            this.playerLife--;
        }
    }

    @Override
    public LevelBuilder getLevelBuilder() {
        return new LevelBuilderImpl().setWordSize(GAME_WORLD_WIDTH, GAME_WORLD_HEIGHT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetPosition() {
        this.level.resetPosition();
    }
}
