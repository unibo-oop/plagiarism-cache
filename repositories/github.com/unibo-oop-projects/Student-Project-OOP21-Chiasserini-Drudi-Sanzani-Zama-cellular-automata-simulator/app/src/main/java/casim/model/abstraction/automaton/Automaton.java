package casim.model.abstraction.automaton;

import java.util.Iterator;

import casim.model.abstraction.cell.AbstractCell;
import casim.model.abstraction.utils.stats.Stats;
import casim.utils.grid.Grid2D;

/**
 *  An interface which describes an {@link Automaton}.
 *
 *  @param <T> the {@link AbstractCell} implementation used by the {@link Automaton}.
 *  @param <S> the states type for the states an {@link Automaton} cell can assume.
 */
public interface Automaton<S, T extends AbstractCell<?>> extends Iterator<Grid2D<T>> {

    /**
     * Get the {@link Grid2D} describing the current {@link Automaton}'s state.
     * 
     * @return A {@link Grid2D} of an {@link AbstractCell} implementation.
     */
    Grid2D<T> getGrid();

    /**
     * Get the {@link Stats} about the {@link Automaton}.
     * 
     * @return a {@link Stats} object containing all the stats data.
     */
    Stats<S> getStats();

}
