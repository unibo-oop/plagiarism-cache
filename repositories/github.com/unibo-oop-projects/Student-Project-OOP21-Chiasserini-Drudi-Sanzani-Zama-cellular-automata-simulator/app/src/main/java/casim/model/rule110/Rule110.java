package casim.model.rule110;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import casim.model.abstraction.automaton.AbstractAutomaton;
import casim.utils.grid.Grid2D;
import casim.utils.grid.Grid2DImpl;
import casim.utils.grid.RowGrid;
import casim.utils.range.Ranges;

/**
 * Rule 110 automaton, composed of a {@link RowGrid} of {@link Rule110Cell}.
 */
public class Rule110 extends AbstractAutomaton<Rule110CellState, Rule110Cell> {
    private static final Map<Integer, Rule110CellState> UPDATE_MAP = Map.of(
        0, Rule110CellState.DEAD,
        1, Rule110CellState.ALIVE,
        2, Rule110CellState.ALIVE,
        3, Rule110CellState.ALIVE,
        4, Rule110CellState.DEAD,
        5, Rule110CellState.ALIVE,
        6, Rule110CellState.ALIVE,
        7, Rule110CellState.DEAD
    );

    private final int maxRows;
    private final RowGrid<Rule110Cell> grid;
    private int last;

    /**
     * Costructs a new Rule110 automaton.
     * 
     * @param maxRows rows' number of the grid.
     */
    public Rule110(final int maxRows) {
        this.maxRows = maxRows;
        this.grid = new RowGrid<>(new Grid2DImpl<>(maxRows, maxRows, () -> new Rule110Cell(Rule110CellState.DEAD)));
        this.grid.set(0, 0, new Rule110Cell(Rule110CellState.ALIVE));
        this.last = 1;
    }

    @Override
    public boolean hasNext() {
        return this.last < this.maxRows;
    }

    @Override
    protected Grid2D<Rule110Cell> doStep() {
        final var current = this.grid.getRow(this.last - 1);
        final var newRow = Ranges.of(0, current.size()).stream()
            .map(index -> getNewCell(index, current))
            .collect(Collectors.toList());
        this.grid.setRow(this.last, newRow);
        this.last++;
        return this.grid;
    }

    @Override
    public Grid2D<Rule110Cell> getGrid() {
        return this.grid;
    }

    /**
     * Method for take the value of the neighbours of a {@link Rule110Cell}.
     * 
     * @param left value of the left {@link Rule110Cell}.
     * @param center value of the {@link Rule110Cell} we are analyzing.
     * @param right value of the right {@link Rule110Cell}.
     * @return the base number 10 represented by the 3 bits.
     */
    private int getValueFromNeighbours(final Rule110CellState left, final Rule110CellState center, final Rule110CellState right) {
        return left.getValue() + 2 * center.getValue() + 4 * right.getValue();
    }

    /**
     * Method that update the {@link Rule110Cell} we are analyzing.
     * 
     * @param index of the {@link Rule110Cell} we are analyzing.
     * @param row current row.
     * @return the updated {@link Rule110Cell}.
     */
    private Rule110Cell getNewCell(final int index, final List<Rule110Cell> row) {
        final int value = this.getValueFromNeighbours(
            index == 0 ? Rule110CellState.DEAD : row.get(index - 1).getState(),
            row.get(index).getState(),
            index == row.size() - 1 ? Rule110CellState.DEAD : row.get(index + 1).getState());
        return new Rule110Cell(UPDATE_MAP.get(value));
    }
}
