package pvz.model.zombies.impl;

import java.util.Random;

import pvz.model.game.api.Difficulty;
import pvz.utilities.Position;

/**
 * Utility class for spawning zombies based on game difficulty.
 */
public final class ZombieSpawnUtil {

    /** Random instance for zombie spawning. */
    private static final Random RANDOM = new Random();
    /** The x position where zombies spawn. */
    private static final int SPAWN_POSITION_X = 10;
    /** Probability threshold for basic zombie (easy). */
    private static final int EASY_BASIC_PROBABILITY = 70;
    /** Probability threshold for fast zombie (easy). */
    private static final int EASY_FAST_PROBABILITY = 90;
    /** Probability threshold for basic zombie (normal). */
    private static final int NORMAL_BASIC_PROBABILITY = 50;
    /** Probability threshold for fast zombie (normal). */
    private static final int NORMAL_FAST_PROBABILITY = 80;
    /** Probability threshold for fast zombie (hard). */
    private static final int HARD_FAST_PROBABILITY = 30;
    /** Probability threshold for strong zombie (hard). */
    private static final int HARD_STRONG_PROBABILITY = 60;

    /**
     * Private constructor to prevent instantiation.
     */
    private ZombieSpawnUtil() {
    }


    /**
     * Generates a random zombie based on the difficulty and bounds.
     *
     * @param difficulty the game difficulty.
     * @param bounds     the number of rows available for spawning.
     * @return a new {@link AbstractZombie} instance.
     */
    public static AbstractZombie generateRandomZombie(final Difficulty difficulty, final int bounds) {
        final Position spawnPosition = new Position(SPAWN_POSITION_X, RANDOM.nextInt(bounds));
        final String type = getRandomZombieType(difficulty);
        return ZombieFactory.createZombie(type, spawnPosition);
    }

    /**
     * Returns a random zombie type string based on the difficulty.
     *
     * @param difficulty the game difficulty.
     * @return the type of zombie as a string.
     */
    private static String getRandomZombieType(final Difficulty difficulty) {
        final int randomValue = RANDOM.nextInt(100);

        return switch (difficulty) {
            case EASY -> {
                if (randomValue < EASY_BASIC_PROBABILITY) {
                    yield "basic"; 
                } else if (randomValue < EASY_FAST_PROBABILITY) {
                    yield "fast";
                } else {
                    yield "strong";
                }
            }
            case NORMAL -> {
                if (randomValue < NORMAL_BASIC_PROBABILITY) {
                    yield "basic";
                } else if (randomValue < NORMAL_FAST_PROBABILITY) {
                    yield "fast";
                } else {
                    yield "strong";
                }
            }
            case HARD -> {
                if (randomValue < HARD_FAST_PROBABILITY) {
                    yield "fast";
                } else if (randomValue < HARD_STRONG_PROBABILITY) {
                    yield "strong"; 
                } else {
                    yield "beast";
                }
            }
        };
    }
}
