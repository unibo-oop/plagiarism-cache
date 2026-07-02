package it.dpg.maingame.model.grid;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;

public interface Grid {

    /**
     * return first Cell of the Grid
     */
    Cell getFirst();

    /**
     * returns last Cell of the Grid
     */
    Cell getLast();

    /**
     * returns a Cell based on the coordinates given
     */
    Cell getCellByCoordinates(Integer X, Integer Y);

    /**
     * returns list of Cells
     */
    Map<Cell, Pair<Integer, Integer>> getCellList();
}
