package it.unibo.model.score.impl;

import it.unibo.model.camera.api.AltitudeObserver;
import it.unibo.model.persistence.api.SaveState;
import it.unibo.model.score.api.ScoreManager;

/**
 * Implementation of {@link ScoreManager} interface.
 */
public class ScoreManagerImpl implements ScoreManager, AltitudeObserver {

    private int currentScore;
    private int coins;
    private int highScore;
    private double startY;
    private double totalCameraDelta;
    private boolean isNewHighScore;

    /**
     * Construct a ScoreManagerImpl with default values.
     */
    public ScoreManagerImpl() {
        this.coins = 0;
        this.currentScore = 0;
        this.highScore = 0;
        this.startY = 0;
        this.totalCameraDelta = 0;
    }

    /**
     * {@inheritDoc}
     * Also updates the total camera delta to keep track of the vertical distance
     * the camera has moved, which is used to calculate the real altitude of the
     * player for scoring purposes.
     */
    @Override
    public void update(final double delta) {
        this.totalCameraDelta += delta;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScore(final double playerY) {
        final double totalRealY = playerY - this.totalCameraDelta;
        final int score = (int) Math.max(0, startY - totalRealY);
        if (score > this.currentScore) {
            this.currentScore = score;
            if (this.currentScore > this.highScore) {
                this.highScore = this.currentScore;
                this.isNewHighScore = true;
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addCoins(final int addedCoins) {
        if (addedCoins > 0) {
            this.coins += addedCoins;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentScore() {
        return this.currentScore;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCoins() {
        return this.coins;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getHighScore() {
        return Math.max(this.highScore, this.currentScore);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadState(final SaveState state) {
        this.highScore = state.getHighestScore();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setStartY(final double y) {
        this.startY = y;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resetScore() {
        this.currentScore = 0;
        this.coins = 0;
        this.totalCameraDelta = 0;
        this.startY = 0;
        this.isNewHighScore = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isNewHighScore() {
        return this.isNewHighScore;
    }
}
