package casim.model.bryansbrain;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

/**
 * Bryan's Brain's {@link BryansBrainUpdateRule} implementation.
 */
//package-private
class BryansBrainUpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, BryansBrainCell> {

    private static final String UNKNOWN_STATE = "Unknown state.";
    private static final int ALIVE_NEIGHBOUR_BIRTH_VALUE = 2;

    //package-private
    BryansBrainUpdateRule(
        final BiFunction<
            Pair<Coordinates2D<Integer>, BryansBrainCell>, 
            Grid<Coordinates2D<Integer>, BryansBrainCell>, 
            List<Pair<Coordinates2D<Integer>, BryansBrainCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    @Override
    protected BryansBrainCell nextCell(final Pair<Coordinates2D<Integer>, BryansBrainCell> cellPair,
            final List<Pair<Coordinates2D<Integer>, BryansBrainCell>> neighborsPairs) {
        switch (cellPair.getRight().getState()) {
            case ALIVE:
                return new BryansBrainCell(BryansBrainCellState.DYING);
            case DYING:
                return new BryansBrainCell(BryansBrainCellState.DEAD);
            case DEAD:
                final int aliveCells = countAliveNeighbors(neighborsPairs);
                return new BryansBrainCell(
                    aliveCells == ALIVE_NEIGHBOUR_BIRTH_VALUE ? BryansBrainCellState.ALIVE : BryansBrainCellState.DEAD);
            default:
                throw new IllegalStateException(UNKNOWN_STATE);
        }
    }

    private int countAliveNeighbors(final Iterable<Pair<Coordinates2D<Integer>, BryansBrainCell>> neighborsPairs) {
        int count = 0;
        for (final var neighbor : neighborsPairs) {
            if (neighbor.getRight().getState() == BryansBrainCellState.ALIVE) {
                count++;
            }
        }
        return count;
    }
}
