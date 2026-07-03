package model.data;

import model.entities.powerup.PowerUpType;

/**
 * Represent the information of the current game.
 */
public interface GameData {

    /**
     * 
     * @return the life of the spaceship
     */
    double getLife();

    /**
     * 
     * @return the score of the spaceship
     */
    int getScore();

    /**
     * 
     * @return the number of enemies died
     */
    int getNumDeadEemies();

    /**
     * 
     * @return the time (in milliseconds) elapsed from the start
     */
    int getTimer();

    /**
     * 
     * @param type
     *            indicates the type of power up
     * @return the number of power up taken of this type
     */
    int getNumPowerUpTakenByType(PowerUpType type);

    /**
     * 
     * @return the total of power up taken in this game
     */
    int getNumPowerUpTaken();

    /**
     * 
     * @return the actual rate of fire (RPM).
     */
    double getRateOfFire();

    /**
     * 
     * @return the actual damage
     */
    double getDamage();

    /**
     * 
     * @return true if the defense power up is active on spaceship
     */
    boolean isDefensePowerUpActive();

    /**
     * 
     * @param life
     *            spaceship's life
     */
    void setLife(double life);

    /**
     * 
     * @param score
     *            score
     */
    void addScore(int score);

    /**
     * Increase 1 to the number of enemies dead.
     */
    void increaseNumDeadEemies();

    /**
     * 
     * @param timeElapsed
     *            time elapsed (in milliseconds)
     */
    void addTime(int timeElapsed);

    /**
     * Increase 1 to the number of power up taken of this type.
     * 
     * @param type
     *            indicates the type of power up
     */
    void increasePowerUpByType(PowerUpType type);

    /**
     * 
     * @param rateOfFire
     *            actual rate of fire
     */
    void setRateOfFire(double rateOfFire);

    /**
     * 
     * @param damage
     *            actual damage of spaceship
     */
    void setDamage(double damage);

    /**
     * Store in game data that shield is active.
     */
    void turnOnShield();

    /**
     * Store in game data that shield isn't active.
     */
    void turnOffShield();

    /**
     * 
     * @return an unmodifiable view of this game data. Attempts to modify the
     *         returned power up result in an UnsupportedOperationException.
     */
    GameData unmodifiableGameData();

}
