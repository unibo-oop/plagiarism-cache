package it.unibo.javajump.model.level.spawn.spawnutilities;

import it.unibo.javajump.model.GameModel;
import it.unibo.javajump.model.entities.platforms.Platform;
import it.unibo.javajump.model.factories.GameObjectFactory;

import java.util.Random;

import static it.unibo.javajump.utility.Constants.SPAWN_X_OFFSET;
import static it.unibo.javajump.utility.Constants.SPAWN_Y_OFFSET;

/**
 * The implementation of SpawnUtils interface.
 */
public final class SpawnUtilsImpl implements SpawnUtils {

    /**
     * Returns a random value between min and max.
     *
     * @param rand the rand
     * @param min  the min
     * @param max  the max
     * @return the float
     */
    public static float randomInRange(final Random rand, final float min, final float max) {
        return min + rand.nextFloat() * (max - min);
    }

    /**
     * Creates and adds to the model a standard platform below the player.
     *
     * @param model   the model
     * @param factory the factory
     */
    public static void spawnPlatformBelowPlayer(final GameModel model, final GameObjectFactory factory) {
        final float px = model.getPlayer().getX() - SPAWN_X_OFFSET;
        final float py = model.getPlayer().getY() + SPAWN_Y_OFFSET;
        final Platform p = factory.createStandardPlatform(px, py);
        model.getGameObjects().add(p);
    }

    /**
     * Private constructor for Constants, to avoid instantiation.
     *
     * @throws AssertionError when wrongly called.
     */
    private SpawnUtilsImpl() {
        throw new AssertionError("This is a utility class, it should not be instantiated!");
    }
}
