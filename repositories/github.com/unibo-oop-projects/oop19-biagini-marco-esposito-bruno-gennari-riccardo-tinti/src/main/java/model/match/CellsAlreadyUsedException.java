package model.match;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.util.Pair;

/**
 * 
 * Exception representing situation where cells these it want to use are already used.
 * 
 */
public class CellsAlreadyUsedException extends IOException {

    private static final long serialVersionUID = 7451832879293050065L;

    private final List<Pair<Integer, Integer>> cellsUsed;


    public CellsAlreadyUsedException(final List<Pair<Integer, Integer>> cellsUsed) {
        super();
        this.cellsUsed = cellsUsed;
    }

    /**
     * Get a list of cells already used.
     * 
     * @return list - list of cell already used
     */
    public List<Pair<Integer, Integer>> getCellsUsed() {
        return new ArrayList<>(this.cellsUsed);
    }
}
