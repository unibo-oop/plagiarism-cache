package model.level.manager;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.lang3.tuple.Pair;

import model.armory.munition.Munition;
import model.armory.weapon.FactoryWeapon;
import model.entities.survivor.ISurvivorFactory;
import model.entities.survivor.Survivor;
import model.entities.survivor.SurvivorFactory;
import model.entities.zombie.Zombie;
import model.entities.zombie.ZombieFactory;
import model.level.types.Level;
import model.entities.zombie.IZombieFactory;

/**
 * Basic implementation of the {@link LevelManager} interface.
 * <p>
 * Manages the game logic for a level, including spawning zombies in waves,
 * updating the survivor's attacks, and checking level completion conditions.
 * </p>
 */
public class LevelManagerBase implements LevelManager {

    private static final Pair<Double, Double> SURVIVOR_START_POS = Pair.of(1000.0, 1000.0);

    private static final int INIT_ZOMBIES_LEVEL = 10;
    private static final int MILLIS_IN_SECOND = 1000;
    private static final int SECONDS_BETWEEN_WAVES = 10;
    private static final int ZOMBIES_FOR_WAVE = 2;
    private static final double SPAWN_POS_MIN_MULTIPLIER = -1.0;
    private static final double SPAWN_POS_MAX_MULTIPLIER = 2.0;
    private static final int MAX_WAVE = 3;

    private final Level level;
    private final IZombieFactory zombieFactory = new ZombieFactory();
    private final ISurvivorFactory surFact = new SurvivorFactory();
    private final FactoryWeapon weapFact = new FactoryWeapon();
    private final AtomicLong elapsedTime = new AtomicLong(0);
    private List<Munition> newMunitions = new ArrayList<>();
    private int currentWave;

    /**
     * Constructs a {@code LevelManagerBase} for the specified level.
     * Initializes the survivor and spawns the initial wave of zombies.
     *
     * @param level the level to manage
     */
    public LevelManagerBase(final Level level) {
        this.level = level;
        this.currentWave = 1;
        this.spawnZombies(INIT_ZOMBIES_LEVEL);
        this.setSurvivorOnLevel();
    }

    /**
     * Updates the level logic including survivor attacks, spawning new waves of
     * zombies,
     * and checking if the level is completed.
     *
     * @param dt the elapsed time in milliseconds since the last update
     */
    @Override
    public void update(final int dt) {
        this.checkSurvivorAttack(level.getSurvivorOnLevel(), dt);
        elapsedTime.addAndGet(dt);

        final int seconds = (int) (elapsedTime.get() / MILLIS_IN_SECOND);

        if (seconds >= (currentWave) * SECONDS_BETWEEN_WAVES && currentWave <= MAX_WAVE) {
            spawnZombies(ZOMBIES_FOR_WAVE);
            currentWave++;
        }

        if (currentWave >= MAX_WAVE && level.getZombieOnLevel().isEmpty()) {
            level.setLevelCompleted(true);
        }
    }

    /**
     * Spawns a given number of zombies at random positions within the level bounds.
     *
     * @param count the number of zombies to spawn
     */
    private void spawnZombies(final int count) {
        List<Zombie> newZombies = IntStream.rangeClosed(0, count)
                .mapToObj(i -> zombieFactory.createClickerZombie(randomPos()))
                .collect(Collectors.toList());

        level.getZombieOnLevel().addAll(newZombies);
    }

    /**
     * Generates a random position within an extended bounding box around the level.
     *
     * @return a pair of coordinates representing the spawn position
     */
    private Pair<Double, Double> randomPos() {
        double w = ThreadLocalRandom.current().nextDouble(
                SPAWN_POS_MIN_MULTIPLIER * level.getLevelWidth(),
                SPAWN_POS_MAX_MULTIPLIER * level.getLevelWidth());

        double h = ThreadLocalRandom.current().nextDouble(
                SPAWN_POS_MIN_MULTIPLIER * level.getLevelHeight(),
                SPAWN_POS_MAX_MULTIPLIER * level.getLevelHeight());

        return Pair.of(w, h);
    }

    /**
     * Checks the survivor's attack state and adds any new projectiles to the level.
     *
     * @param sur the survivor entity
     * @param dt  the elapsed time in milliseconds since the last update
     */
    private void checkSurvivorAttack(final Survivor sur, final int dt) {
        if (sur.getState().getIndex() >= 5 && sur.getState().getIndex() <= 8) {
            this.newMunitions.addAll(sur.getWeapon().shoot(dt));
            if (!newMunitions.isEmpty()) {
                this.level.getProjectilesOnLevel().addAll(newMunitions);
            }
            this.newMunitions.clear();
        }
    }

    /**
     * Initializes the survivor on the level with a default weapon.
     */
    private void setSurvivorOnLevel() {
        var sur = surFact.createCommonSurvivor(SURVIVOR_START_POS);
        sur.setWeapon(weapFact.createPistol(SURVIVOR_START_POS));
        this.level.setSurvivorOnLevel(sur);
    }
}
