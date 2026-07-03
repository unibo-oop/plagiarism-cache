package game.buffs;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import game.GameImpl;
import game.ID;
import utilities.Clamp;
import utilities.Pair;

/**
 * The class used to spawn powerUps.
 */
public final class PowerUpSpawner {

    private static final int GLOBAL_POWER_UP_PROBABILITY = 50;
    private static final int LEVEL_LIMIT_FOR_LOW_GRADE_BUFFS = 10;
    private static final int LEVEL_LIMIT_FOR_BASIC_BUFFS = 20;

    private final int spawnNuke;
    private final int spawnFreeze;

    /**
     * 
     */
    public PowerUpSpawner() {
        final Random random = new Random();
        this.spawnNuke = random.nextInt(GLOBAL_POWER_UP_PROBABILITY);
        this.spawnFreeze = random.nextInt(GLOBAL_POWER_UP_PROBABILITY);
    }

    /**
     * Generates one to four powerUps of different types.
     * @param level the current level
     * @return a list of randomly generated powerUps
     */
    public List<PlayerPowerUp> spawnPlayerPowerUps(final int level) {
        final List<PlayerPowerUp> list = new ArrayList<>();
        final Random random = new Random();
        final BuffingStrategy strategy = level < LEVEL_LIMIT_FOR_LOW_GRADE_BUFFS 
                ? new LowGradeBuff() : level < LEVEL_LIMIT_FOR_BASIC_BUFFS 
                        ? new BasicBuff() : new HighGradeBuff();
        int randomNumber;
        PowerUpType type;
        int numberOfPowerUpsToSpawn = random.nextInt(3) + 1;
        while (numberOfPowerUpsToSpawn > 0) {
            randomNumber = random.nextInt(4);
            switch (randomNumber) {
            case 0 : type = PowerUpType.HEALTH_RECOVERY;
            break;
            case 1 : type = PowerUpType.SPEED_BOOST;
            break;
            case 2 : type = PowerUpType.FIRE_RATE_BOOST;
            break;
            default : type = PowerUpType.SHIELD;
            }
            list.add(new PlayerPowerUp(ID.POWER_UP, this.generateRandomPosition(), this.generateRandomVelocity(), this.generateRandomVelocity(), type, strategy));
            numberOfPowerUpsToSpawn--;
        }
        return list;
    }

    /**
     * @return a new random generated position
     */
    private Pair<Integer, Integer> generateRandomPosition() {
        final Random random = new Random();
        final int xPosition = Clamp.clamp(random.nextInt(GameImpl.GAMEAREA_WIDTH), 0 + PowerUpImpl.WIDTH / 2, GameImpl.GAMEAREA_WIDTH - PowerUpImpl.WIDTH / 2);
        final int yPosition = Clamp.clamp(random.nextInt(GameImpl.GAMEAREA_HEIGHT), 0 + PowerUpImpl.HEIGHT / 2, GameImpl.GAMEAREA_HEIGHT - PowerUpImpl.HEIGHT / 2);
        return new Pair<Integer, Integer>(xPosition, yPosition);
    }

    /**
     * @return a new random generated velocity
     */
    private int generateRandomVelocity() {
        final Random random = new Random();
        return random.nextInt(PowerUpType.values().length) + 1;
    }

    /**
     * Randomly calculates if a globalPowerUp should be spawned or not.
     * @return an optional which could contain a GlobalPowerUp or nothing
     */
    public Optional<GlobalPowerUp> spawnGlobalPowerUp() {
        final Random random = new Random();
        final int randomNumber;
        final PowerUpType type;
        randomNumber = random.nextInt(GLOBAL_POWER_UP_PROBABILITY);
        if (randomNumber == this.spawnNuke) {
            type = PowerUpType.NUKE;
        } else if (randomNumber == this.spawnFreeze) {
            type = PowerUpType.FREEZE;
        } else {
            return Optional.empty();
        }
        return Optional.of(new GlobalPowerUp(ID.POWER_UP, this.generateRandomPosition(), this.generateRandomVelocity(), this.generateRandomVelocity(), type));
    }
}
