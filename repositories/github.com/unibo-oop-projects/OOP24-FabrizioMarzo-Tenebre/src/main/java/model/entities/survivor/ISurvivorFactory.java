package model.entities.survivor;

import org.apache.commons.lang3.tuple.Pair;

/**
 * Factory interface for creating {@link Survivor}.
 * <p>
 * This interface abstracts the creation of survivor entities, allowing
 * clients to instantiate survivor objects without coupling to their concrete
 * implementation.
 */
public interface ISurvivorFactory {

    /**
     * Creates a new instance of a common {@link Survivor} with default attributes.
     *
     * @param pos The initial position of the survivor as a pair (x, y)
     * @return A new {@code Survivor} instance positioned at the given coordinates
     */
    Survivor createCommonSurvivor(final Pair<Double, Double> pos);
}
