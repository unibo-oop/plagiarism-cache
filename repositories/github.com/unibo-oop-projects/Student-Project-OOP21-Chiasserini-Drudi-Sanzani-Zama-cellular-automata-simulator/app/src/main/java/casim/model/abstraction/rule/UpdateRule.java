package casim.model.abstraction.rule;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.cell.AbstractCell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * The Automaton's rule used to update the Cell's state.
 * 
 * @param <T> the {@link AbstractCell} implementation to update.
 * @param <C> the {@link Coordinates} implementation used by the cell (it can be 2D or 3D).
 */
public interface UpdateRule<C extends Coordinates<? extends Number>, T extends AbstractCell<?>> {

     /**
     * Return the updated Cell.
     * 
     * @param cellPair pair of {@link Coordinates} and a cell to update;
     * @param grid the {@link Grid} representing the automaton.
     * @return the updated cell.
     */
    T getNextCell(Pair<C, T> cellPair, Grid<C, T> grid);

}
