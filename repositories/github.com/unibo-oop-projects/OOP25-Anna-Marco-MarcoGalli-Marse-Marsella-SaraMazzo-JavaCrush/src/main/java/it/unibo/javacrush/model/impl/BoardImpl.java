package it.unibo.javacrush.model.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Cell;

/**
 * This class is the implementation of {@link it.unibo.javacrush.model.api.Board}.
 */
public class BoardImpl implements Board {

    private final Map<Position, Optional<Cell>> cells = new HashMap<>();
    private final int rows;
    private final int cols;

    /**
     * BoardImpl constructor.
     * 
     * @param rows the rows size of the board.
     * @param cols the columns size of the board.
     */
    public BoardImpl(final int rows, final int cols) {

        this.rows = rows;
        this.cols = cols;

        for (int j = 0; j < rows; j++) {
            for (int i = 0; i < cols; i++) {
                //creiamo la griglia delle posizioni senza celle dentro
                this.cells.put(new Position(i, j), Optional.empty());
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCols() {
        return this.cols;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Cell> getCellAt(final Position pos) {
        return cells.get(pos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<Position, Optional<Cell>> getGrid() {
        return Map.copyOf(this.cells);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void swapCells(final Position pos1, final Position pos2) {
        final var tmp = cells.get(pos1);
        cells.put(pos1, cells.get(pos2));
        cells.put(pos2, tmp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCell(final Position pos, final Optional<Cell> cell) {
        cells.put(pos, cell);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void removeCell(final Position pos) {
        this.setCell(pos, Optional.empty());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cells == null) ? 0 : cells.hashCode());
        result = prime * result + rows;
        result = prime * result + cols;
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardImpl other = (BoardImpl) obj;
        if (cells == null) {
            if (other.cells != null) {
                return false;
            }
        } else if (!cells.equals(other.cells)) {
            return false;
        }
        return rows == other.rows && cols == other.cols;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "BoardImpl [cells=" + cells + "]";
    }

}
