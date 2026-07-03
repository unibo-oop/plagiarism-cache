package model.data;

import model.entities.powerup.PowerUpType;

/**
 * This class is a wrapper of a game data. Game data can be read, but not
 * modified.
 */
public class UnmodifiableGameData implements GameData {

    private final GameData gameData;

    /**
     * 
     * @param gameData
     *            game data to wrap into this class
     */
    public UnmodifiableGameData(final GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public final double getLife() {
        return this.gameData.getLife();
    }

    @Override
    public final int getScore() {
        return this.gameData.getScore();
    }

    @Override
    public final int getNumDeadEemies() {
        return this.gameData.getNumDeadEemies();
    }

    @Override
    public final int getTimer() {
        return this.gameData.getTimer();
    }

    @Override
    public final int getNumPowerUpTakenByType(final PowerUpType type) {
        return this.gameData.getNumPowerUpTakenByType(type);
    }

    @Override
    public final int getNumPowerUpTaken() {
        return this.gameData.getNumPowerUpTaken();
    }

    @Override
    public final double getRateOfFire() {
        return this.gameData.getRateOfFire();
    }

    @Override
    public final double getDamage() {
        return this.gameData.getDamage();
    }

    @Override
    public final boolean isDefensePowerUpActive() {
        return this.gameData.isDefensePowerUpActive();
    }

    @Override
    public final void setLife(final double life) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void addScore(final int score) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void increaseNumDeadEemies() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void addTime(final int timeElapsed) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void increasePowerUpByType(final PowerUpType type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void setRateOfFire(final double rateOfFire) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void setDamage(final double damage) {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void turnOnShield() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final void turnOffShield() {
        throw new UnsupportedOperationException();
    }

    @Override
    public final GameData unmodifiableGameData() {
        return this;
    }

}
