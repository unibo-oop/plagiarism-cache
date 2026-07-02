package casim.model.gameoflife;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.range.Ranges;

/**
 * Class that models the Game Of Life.
 */
public class GameOfLife extends AbstractAutomaton<GameOfLifeState, GameOfLifeCell> {

    private Grid2D<GameOfLifeCell> state;
    private final GameOfLifeUpdateRule updateRule
        = new GameOfLifeUpdateRule(NeighborsFunctions::mooreNeighborsFunction);

    /**
     * Constructor of the {@link Grid2D} filled with {@link GameOfLifeState}.
     * 
     * @param state starting state for all {@link GameOfLifeCell}.
     */
    public GameOfLife(final Grid2D<GameOfLifeState> state) {
        this.state = state.map(s -> new GameOfLifeCell(s));
    }

    /**
     * hasNext method (always true).
     */
    @Override
    public boolean hasNext() {
        return true;
    }

    /**
     * Function that advances of one step acoording to the {@link GameOfLife}'s' rules.
     * 
     */
    @Override
    protected Grid2D<GameOfLifeCell> doStep() {
        final var newState = new Grid2DImpl<GameOfLifeCell>(this.state.getHeight(), this.state.getWidth());

        for (final var x : Ranges.of(0, this.state.getHeight())) {
            for (final var y : Ranges.of(0, this.state.getWidth())) {
                final var coord = CoordinatesUtil.of(x, y);
                newState.set(coord, this.updateRule.getNextCell(Pair.of(coord, this.state.get(coord)), this.state));
            }
        }

        this.state = newState;
        return this.state;
    }

    /**
     * Method that return the whole {@link Grid2D}.
     */
    @Override
    public Grid2D<GameOfLifeCell> getGrid() {
        return this.state;
    }
}
