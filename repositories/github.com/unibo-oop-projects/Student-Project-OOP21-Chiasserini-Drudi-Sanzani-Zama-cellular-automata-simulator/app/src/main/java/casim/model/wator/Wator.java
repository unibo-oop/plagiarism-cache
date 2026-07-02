package casim.model.wator;

import org.apache.commons.lang3.tuple.Pair;
import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.model.abstraction.utils.NeighborsFunctions;
import casim.utils.coordinate.CoordinatesUtil;
import casim.utils.grid.Grid2D;
import casim.utils.range.Ranges;

/**
 * Predators and Preys automaton.
 */
public class Wator extends AbstractAutomaton<WatorCellState, WatorCell> {

    private static final int INIT_HEALTH = 5;

    private final Grid2D<WatorCell> state;
    private final WatorUpdateRule updateRule = new WatorUpdateRule(NeighborsFunctions::neighbors2DFunction);

    /**
     * Builds a new {@link Wator} automaton with initial state from input.
     * 
     * @param state {@link Grid2D} of {@link WatorCellState} representing the
     * initial state of the automaton.
     */
    public Wator(final Grid2D<WatorCellState> state) {
        this.state = state.map(x -> new WatorCell(x, INIT_HEALTH));
    }

    @Override
    public boolean hasNext() {
        return this.state.stream()
                .anyMatch(x -> !x.getState().equals(WatorCellState.DEAD));
    }

    @Override
    protected Grid2D<WatorCell> doStep() {
        for (final var x : Ranges.of(0, this.state.getHeight())) {
            for (final var y : Ranges.of(0, this.state.getWidth())) {
                final var coord = CoordinatesUtil.of(x, y);
                final var cellPair = Pair.of(coord, this.state.get(coord));
                this.state.set(coord, this.updateRule.getNextCell(cellPair, this.state));
            }
        }
        this.state.stream().forEach(WatorCell::resetMovement);
        return this.state;
    }

    @Override
    public Grid2D<WatorCell> getGrid() {
        return this.state;
    }

}
