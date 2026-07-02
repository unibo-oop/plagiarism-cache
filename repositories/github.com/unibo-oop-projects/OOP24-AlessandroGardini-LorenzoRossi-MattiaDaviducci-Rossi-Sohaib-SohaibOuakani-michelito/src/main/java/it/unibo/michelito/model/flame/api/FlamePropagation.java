package it.unibo.michelito.model.flame.api;

import it.unibo.michelito.model.maze.api.Maze;
import it.unibo.michelito.util.Position;

import java.util.Set;

/**
 * Interface for the propagation of flames.
 */
public interface FlamePropagation {
    /**
     * Propagate a flame from a given origin.
     *
     * @param origin      The origin of the flame.
     * @param range       The range of the flame.
     * @param passThrough Whether the flame can pass through walls.
     * @param maze        The maze in which the flame is propagated.
     * @return The set of flames that have been propagated.
     */
    Set<Flame> propagate(Position origin, int range, boolean passThrough, Maze maze);
}
