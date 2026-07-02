package model.entities.zombie;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Factory interface for creating {@link Zombie} instances.
 * <p>
 * Defines methods to instantiate different types of zombies at specified
 * positions.
 * </p>
 */
public interface IZombieFactory {

    /**
     * Creates a new Clicker-type zombie positioned at the given coordinates.
     *
     * @param pos a pair (x, y) representing the initial position of the zombie
     * @return a new {@link Zombie} instance of type Clicker
     */
    Zombie createClickerZombie(final Pair<Double, Double> pos);
}
