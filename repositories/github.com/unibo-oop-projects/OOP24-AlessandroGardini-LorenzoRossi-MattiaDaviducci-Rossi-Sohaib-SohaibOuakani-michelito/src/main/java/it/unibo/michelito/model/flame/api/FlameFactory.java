package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.util.Position;

/**
 * Factory for creating flames.
 */
public interface FlameFactory {
    /**
     * Create a new flame.
     *
     * @param position The position of the flame.
     * @return The new {@link Flame}.
     */
    Flame createFlame(Position position);
}
