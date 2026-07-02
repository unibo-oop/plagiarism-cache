package casim.model.gameoflife;

import java.util.List;
import java.util.function.BiFunction;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.rule.AbstractUpdateRule;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.grid.Grid;

/**
 * The GameOfLife's rule used to update the GameOfLifeCell's state.
 */
//package-private
class GameOfLifeUpdateRule extends AbstractUpdateRule<Coordinates2D<Integer>, GameOfLifeCell> {
    //package-private
    GameOfLifeUpdateRule(final BiFunction<Pair<Coordinates2D<Integer>, GameOfLifeCell>, Grid<Coordinates2D<Integer>, GameOfLifeCell>, List<Pair<Coordinates2D<Integer>, GameOfLifeCell>>> neighborsFunction) {
        super(neighborsFunction);
    }

    /**
     * Function that analize the neighbors of the {@link GameOfLifeCell} and calculates his next state.
     * 
     * @param cellPair a {@link Pair} that contains the {@link GameOfLifeCell} and his {@link Coordinates2D}.
     * @param neighborsPairs a {@link List} of all the {@link GameOfLifeCell} neighbors and hsi {@link Coordinates2D}.
     */
    @Override
    protected GameOfLifeCell nextCell(final Pair<Coordinates2D<Integer>, GameOfLifeCell> cellPair,
        final List<Pair<Coordinates2D<Integer>, GameOfLifeCell>> neighborsPairs) {

        final int aliveCells = this.countAliveNeighbors(neighborsPairs);

        if (cellPair.getRight().getState() == GameOfLifeState.ALIVE) {
            if (aliveCells == 2 || aliveCells == 3) {
                return new GameOfLifeCell(GameOfLifeState.ALIVE);
            } else {
                return new GameOfLifeCell(GameOfLifeState.DEAD);
            }
        } else {
            if (aliveCells == 3) {
                return new GameOfLifeCell(GameOfLifeState.ALIVE);
            } else {
                return new GameOfLifeCell(GameOfLifeState.DEAD);
            }
        }
    }

    /**
     * Utility function for count the number of neighbors alive of one {@link Cell}.
     * 
     * @param neighborsPairs an {@link Iterable} {@link Pair} of {@link GameOfLifeCell} and his {@link Coordinates2D}.
     * @return count the number of the neighboring cells.
     */
    private int countAliveNeighbors(final Iterable<Pair<Coordinates2D<Integer>, GameOfLifeCell>> neighborsPairs) {
        int count = 0;
        for (final var neighbor : neighborsPairs) {
            if (neighbor.getRight().getState() == GameOfLifeState.ALIVE) {
                count++;
            }
        }
        return count;
    }
}
