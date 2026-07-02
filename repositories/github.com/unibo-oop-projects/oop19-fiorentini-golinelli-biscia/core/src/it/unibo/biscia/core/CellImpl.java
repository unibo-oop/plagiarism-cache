package it.unibo.biscia.core;

import java.util.Objects;

final class CellImpl implements Cell {
    private final int col;
    private final int row;
    private final int hashCache;
    private String toStringCache;

    /**
     * basic constructor.
     * 
     * @param level level over is this cell
     * @param col   positive column index of cell
     * @param row   positive row index of cell
     */
    CellImpl(final int col, final int row) {
        if (col < 0 || row < 0) {
            throw new IllegalArgumentException();
        }
        this.col = col;
        this.row = row;
        if (this.col == this.row) {
            this.hashCache = (this.col + 1) * (this.col + 1);
        } else {
            if (this.col > this.row) {
                this.hashCache = this.col * this.col + this.row * 2 + 1;
            } else {
                this.hashCache = this.row * this.row + this.col * 2 + 2;
            }
        }
    }

    @Override
    public int getCol() {
        return this.col;
    }

    @Override
    public int getRow() {
        return this.row;
    }

    @Override
    public int hashCode() {
        return this.hashCache;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (Objects.isNull(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return ((Cell) obj).hashCode() == this.hashCache;
    }

    @Override
    public String toString() {
        if (Objects.isNull(this.toStringCache)) {
            this.toStringCache = "[" + col + ", " + row + "]";
        }
        return this.toStringCache;
    }
}
