package casim.controller.automaton;

import java.util.Iterator;

import casim.model.abstraction.utils.stats.Stats;
import casim.utils.grid.Grid2D;

/**
 * Interface for the automaton controller.
 *
 * @param <S> the states of the cells.
 */
public interface AutomatonController<S> extends Iterator<Grid2D<S>> {
    /**
     * Get the current state grid.
     * 
     * @return the state grid.
     */
    Grid2D<S> getGrid();

    /**
     * Get stats about the automaton.
     * 
     * @return the stats.
     */
    Stats<S> getStats();
}
