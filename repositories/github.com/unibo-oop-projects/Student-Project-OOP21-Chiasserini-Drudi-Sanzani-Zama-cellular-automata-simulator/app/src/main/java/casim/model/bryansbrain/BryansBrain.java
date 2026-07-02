package casim.model.bryansbrain;

import org.apache.commons.lang3.tuple.Pair;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.GridUtils;
import casim.utils.grid.WrappingGrid;

/**
 * Bryan's Brain automaton.
 */
public class BryansBrain extends AbstractAutomaton<BryansBrainCellState, BryansBrainCell> {
    private final boolean wrapping;

    private Grid2D<BryansBrainCell> state;
    private final BryansBrainUpdateRule updateRule
        = new BryansBrainUpdateRule(NeighborsFunctions::mooreNeighborsFunction);

    /**
     * Build a new {@link BryansBrain}.
     * 
     * @param state the initial state of the grid.
     * @param wrapping true if the automaton has to wrap the grid.
     */
    public BryansBrain(final Grid2D<BryansBrainCellState> state, final boolean wrapping) {
        this.wrapping = wrapping;
        this.state = this.wrapping 
            ? new WrappingGrid<>(state.map(s -> new BryansBrainCell(s)))
            : state.map(s -> new BryansBrainCell(s));
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    protected Grid2D<BryansBrainCell> doStep() {
        final var newState = this.getNextStateGrid();
        GridUtils.get2dCoordStream(this.state.getHeight(), this.state.getWidth())
            .forEach(coord -> newState.set(
                coord, 
                this.updateRule.getNextCell(Pair.of(coord, this.state.get(coord)), 
                this.state)));
        this.state = newState;
        return this.state;
    }

    @Override
    public Grid2D<BryansBrainCell> getGrid() {
        return this.state;
    }

    private Grid2D<BryansBrainCell> getNextStateGrid() {
        Grid2D<BryansBrainCell> newState = new Grid2DImpl<>(this.state.getHeight(), this.state.getWidth());
        if (this.wrapping) {
            newState = new WrappingGrid<>(newState);
        }
        return newState;
    }
}
