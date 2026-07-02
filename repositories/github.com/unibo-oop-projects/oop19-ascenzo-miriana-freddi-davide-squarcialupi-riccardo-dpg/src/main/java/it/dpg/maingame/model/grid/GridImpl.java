package it.dpg.maingame.model.grid;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public class GridImpl implements Grid {

    private final Cell first;
    private final Cell last;
    private final Map<Cell, Pair<Integer, Integer>> grid;

    public GridImpl(final Cell first, final Cell last, final Map<Cell, Pair<Integer, Integer>> grid) {
        this.first = first;
        this.last = last;
        this.grid = grid;
    }

    @Override
    public Cell getFirst() {
        return this.first;
    }

    @Override
    public Cell getLast() {
        return this.last;
    }

    @Override
    public Cell getCellByCoordinates(Integer X, Integer Y) {
        for (var i : grid.entrySet()) {
            if (i.getValue().getLeft().equals(X) && i.getValue().getRight().equals(Y)) {
                return i.getKey();
            }
        }
        //the method throws an exception if there is no cell corresponding to @param
        throw new IllegalStateException();
    }

    public Map<Cell, Pair<Integer, Integer>> getCellList() {
        return this.grid;
    }
}
