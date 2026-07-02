package spacesurvival.model.gui.game;

import spacesurvival.model.gameobject.takeable.ammo.AmmoType;

public class EngineHUD {
    /**
     * The initial score.
     */
    public static final int INIT_SCORE = 0;
    /**
     * The initial round.
     */
    public static final int INIT_ROUND = 1;
    /**
     * The initial number of lives.
     */
    public static final int INIT_HEART = 3;
    /**
     * Decrement value.
     */
    public static final int DECR_VALUE = 1;
    /**
     * 
     */
    public static final int DEAD = 0;

    private final Chronometer chronometer;
    private int score;
    private int round;
    private int lives;
    private AmmoType ammoType;

    public EngineHUD() {
        this.chronometer = new Chronometer();
        this.score = INIT_SCORE;
        this.round = INIT_ROUND;
        this.lives = INIT_HEART;
    }

    /**
     * Return the current score.
     * 
     * @return the current score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Increment the score.
     * 
     * @param score the score to increment
     */
    public void incrScore(final long score) {
        this.score += score;
    }

    /**
     * Return the current round.
     * 
     * @return the number of the round
     */
    public int getRound() {
        return this.round;
    }

    /**
     * Set the passed round in the HUD.
     * 
     * @param round
     */
    public void setRound(final int round) {
        this.round = round;
    }

    /**
     * Increment the round in the HUD.
     */
    public void incrRound() {
        this.round += DECR_VALUE;
    }

    /**
     * Reutnr the current lives.
     * @return the current lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Set the passed number of heart to the ship.
     * 
     * @param heartShip number of heart to set
     */
    public void setHeartShip(final int heartShip) {
        this.lives = heartShip;
    }

    /**
     * Increase ship lives.
     * 
     * @param amount to increase
     */
    public void increaseLives(final int amount) {
        this.lives += amount;
    }

    /**
     * Decrease ship lives.
     * 
     */
    public void decreaseLives() {
        this.lives -= DECR_VALUE;
    }

    /**
     * Return the current ammo type.
     * 
     * @return an AmmoType
     */
    public AmmoType getAmmoType() {
        return this.ammoType;
    }

    /**
     * Set the passed AmmoType in HUD.
     * 
     * @param ammoType to set
     */
    public void setAmmoType(final AmmoType ammoType) {
        this.ammoType = ammoType;
    }

    /**
     * Return the current timer.
     * 
     * @return a representing string
     */
    public String getTimer() {
        return this.chronometer.getTimer();
    }

    /**
     * Start the timer.
     */
    public void startTimer() {
        this.chronometer.play();
    }
    /**
     * Stop the timer.
     */
    public void stopTimer() {
        this.chronometer.stopTimer();
    }

    /**
     * Reset lives.
     */
    public void resetLives() {
        this.lives = EngineHUD.INIT_HEART;
    }

    /**
     * Reset the timer.
     */
    public void resetTimer() {
        this.chronometer.restart();
    }
}
