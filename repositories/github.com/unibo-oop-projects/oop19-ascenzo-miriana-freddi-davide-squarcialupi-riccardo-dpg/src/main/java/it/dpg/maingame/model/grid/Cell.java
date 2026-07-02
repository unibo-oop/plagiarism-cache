package it.dpg.maingame.model.grid;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Set;

public interface Cell {

    /**
     * returns whether the Cell is connected to a fork
     */
    Boolean isAFork();

    /**
     * returns the connected Cells
     */
    Set<Cell> getNext();

    /**
     * returns the Cell coordinates
     */
    Pair<Integer, Integer> getCoordinates();

    /**
     * returns the Cell Type
     */
    CellType getType();

    /**
     * returns the previous Cell
     */
    Cell getPrevious();
}
