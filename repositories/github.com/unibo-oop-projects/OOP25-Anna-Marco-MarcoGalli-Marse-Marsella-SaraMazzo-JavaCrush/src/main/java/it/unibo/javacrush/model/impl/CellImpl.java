package it.unibo.javacrush.model.impl;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.model.api.Cell;
import java.util.Objects;

/**
 * Implementation of the Cell interface representing a cell in the game grid.
 * Each cell has a specific type defined by the CellType enum.
 */
public final class CellImpl implements Cell {

    private final CellType type;

    /**
     * Creates a CellImpl with the specified CellType.
     * 
     * @param type the type of the cell
     */
    public CellImpl(final CellType type) {
        this.type = Objects.requireNonNull(type, "Cell type cannot be null");
    }

    @Override
    public CellType getType() {
        return this.type;
    }

    @Override
    public String toString() {
           return "Cell[" + this.type + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

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
        final CellImpl other = (CellImpl) obj;
        return type == other.type;
    }
}
