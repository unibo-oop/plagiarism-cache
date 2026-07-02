package casim.model.abstraction.rule;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.cell.AbstractCell;
import casim.utils.coordinate.Coordinates;
import casim.utils.grid.Grid;

/**
 * Abstract implementation of {@link UpdateRule}.
 *
 *  @param <T> the {@link AbstractCell} implementation to update.
 *  @param <C> the {@link Coordinates} implementation used by the cell (it can be 2D or 3D).
 */
public abstract class AbstractUpdateRule<C extends Coordinates<? extends Number>, T extends AbstractCell<?>> implements UpdateRule<C, T> {

    private final BiFunction<Pair<C, T>, Grid<C, T>, List<Pair<C, T>>> neighborsFunction;

    /**
     * Constructor of an abstract {@link UpdateRule}.
     * 
     * @param neighborsFunction used to take the neighbors of a given cell:
     *  - First Argument: a pair {@link Coordinates} + {@link AbstractCell} implementation 
     *                          of the cell of which we want to obtain the neighbors;
     *  - Second Argument: the {@link Grid} where the cells are contained;
     *  - Return type: a list containing all the neighbors.
     */
    public AbstractUpdateRule(final BiFunction<Pair<C, T>, Grid<C, T>, List<Pair<C, T>>> neighborsFunction) {
        this.neighborsFunction = neighborsFunction;
    }

    @Override
    public T getNextCell(final Pair<C, T> cellPair, final Grid<C, T> grid) {
        return this.nextCell(cellPair, this.neighborsFunction.apply(cellPair, grid));
    }

    /**
     * Abstract method used to calculate the updated cell of the one taken as input. 
     * 
     * @param cellPair pair of {@link Coordinates} and Cell to updates;
     * @param neighborsPairs a list containing all the pairs {@link Coordinates} + Cell neighbors of the cellPair.
     * @return the new updated Cell.
     */
    protected abstract T nextCell(Pair<C, T> cellPair, List<Pair<C, T>> neighborsPairs);
}
