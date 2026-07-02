package it.dpg.maingame.model.grid;

import org.apache.commons.lang3.tuple.Pair;

import java.util.HashSet;
import java.util.Set;

public class CellImpl implements Cell {

    private Set<Cell> nextCell = new HashSet<>();
    private Cell previous;
    private final boolean isAFork;
    private final Pair<Integer, Integer> coordinates;
    private final CellType type;

    public CellImpl(final boolean isAFork, final Pair<Integer, Integer> coordinates, final CellType type) {
        this.isAFork = isAFork;
        this.coordinates = coordinates;
        this.type = type;
    }

    @Override
    public Boolean isAFork() {
        return this.isAFork;
    }

    public void setNext(Set<Cell> next) {
        this.nextCell = next;
    }

    @Override
    public Set<Cell> getNext() {
        return this.nextCell;
    }

    @Override
    public Pair<Integer, Integer> getCoordinates() {
        return this.coordinates;
    }

    @Override
    public CellType getType() {
        return this.type;
    }

    public void setPrevious(Cell previous) {
        this.previous = previous;
    }

    @Override
    public Cell getPrevious() {
        return this.previous;
    }
}
