package model.match;

import java.util.List;
import java.util.stream.Collectors;

import model.util.Pair;

/**
 * 
 * Exception representing situation where cells these it want to use are filled.
 * 
 */
public class CellsFilledException extends CellsAlreadyUsedException {

    private static final long serialVersionUID = -6205542022676663926L;

    public CellsFilledException(final List<Pair<Integer, Integer>> cellsUsed) {
        super(cellsUsed);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final String s = this.getCellsUsed().stream()
                .map(cell -> "[" + cell.getX() + ", " + cell.getY() + "]")
                .collect(Collectors.joining("; ", "{ ", "}"));

        return "Cells already filled: " + s + "\n" + super.toString();
    }

}
