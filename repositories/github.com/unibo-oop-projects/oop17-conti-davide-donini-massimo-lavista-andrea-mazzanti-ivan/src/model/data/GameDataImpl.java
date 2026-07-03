package model.data;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

import model.entities.powerup.PowerUpType;

/**
 * Class that represents the game data.
 */
public class GameDataImpl implements GameData {

    private double life;
    private int score;
    private int numEnemiesDied;
    private int timer;
    private final Map<PowerUpType, Integer> numPowerUp;
    private double rateOfFire;
    private double damage;
    private boolean defensePowerUpActive;

    /**
     * Constructor used to initialize data when new game start.
     */
    public GameDataImpl() {
        this.life = 0;
        this.score = 0;
        this.numEnemiesDied = 0;
        this.timer = 0;
        this.numPowerUp = new EnumMap<>(PowerUpType.class);
        Arrays.asList(PowerUpType.values()).forEach(p -> this.numPowerUp.put(p, 0));
        this.rateOfFire = 0.0;
        this.damage = 0.0;
        this.defensePowerUpActive = false;
    }

    /**
     * 
     * @param gameData
     *            game data to clone
     */
    public GameDataImpl(final GameData gameData) {
        this.life = gameData.getLife();
        this.score = gameData.getScore();
        this.numEnemiesDied = gameData.getNumDeadEemies();
        this.timer = gameData.getTimer();
        this.numPowerUp = new EnumMap<>(PowerUpType.class);
        Arrays.asList(PowerUpType.values()).forEach(p -> this.numPowerUp.put(p, gameData.getNumPowerUpTakenByType(p)));
        this.rateOfFire = gameData.getRateOfFire();
        this.damage = gameData.getDamage();
        this.defensePowerUpActive = gameData.isDefensePowerUpActive();
    }

    @Override
    public final double getLife() {
        return this.life;
    }

    @Override
    public final int getScore() {
        return this.score;
    }

    @Override
    public final int getNumDeadEemies() {
        return this.numEnemiesDied;
    }

    @Override
    public final int getTimer() {
        return this.timer;
    }

    @Override
    public final int getNumPowerUpTakenByType(final PowerUpType type) {
        return this.numPowerUp.get(type);
    }

    @Override
    public final int getNumPowerUpTaken() {
        return this.numPowerUp.values().stream().mapToInt(n -> n).sum();
    }

    @Override
    public final double getRateOfFire() {
        return this.rateOfFire;
    }

    @Override
    public final double getDamage() {
        return this.damage;
    }

    @Override
    public final boolean isDefensePowerUpActive() {
        return this.defensePowerUpActive;
    }

    @Override
    public final void setLife(final double life) {
        this.life = life;
    }

    @Override
    public final void addScore(final int score) {
        this.score += score;
    }

    @Override
    public final void increaseNumDeadEemies() {
        this.numEnemiesDied++;
    }

    @Override
    public final void addTime(final int time) {
        this.timer += time;
    }

    @Override
    public final void increasePowerUpByType(final PowerUpType type) {
        this.numPowerUp.put(type, this.numPowerUp.get(type) + 1);
    }

    @Override
    public final void setRateOfFire(final double rateOfFire) {
        this.rateOfFire = rateOfFire;
    }

    @Override
    public final void setDamage(final double damage) {
        this.damage = damage;
    }

    @Override
    public final void turnOnShield() {
        this.defensePowerUpActive = true;
    }

    @Override
    public final void turnOffShield() {
        this.defensePowerUpActive = false;
    }

    @Override
    public final GameData unmodifiableGameData() {
        return new UnmodifiableGameData(new GameDataImpl(this));
    }

    @Override
    public final String toString() {
        return "GameDataImpl [life=" + life + ", score=" + score + ", numEnemiesDied=" + numEnemiesDied + ", timer="
                + timer + ", numPowerUp=" + numPowerUp + ", defensePowerUpActive=" + defensePowerUpActive + "]";
    }

}
