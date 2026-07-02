package it.unibo.javacrush.model.impl;

import java.util.HashSet;
import java.util.Set;

import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Match;

/**
 * Implementation of the Match interface representing a match of cells in the game grid.
 * A match consists of a set of positions (at least three) and a cell type that defines the type of cells.
 */
public final class MatchImpl implements Match {

    private final Set<Position> positions = new HashSet<>();
    private final CellType type;

    /**
     * Constructs a MatchImpl with the specified positions and cell type.
     * 
     * @param positions the set of positions that form the match
     * @param type the cell type of the match
     */
    public MatchImpl(final Set<Position> positions, final CellType type) {
        this.positions.addAll(positions);
        this.type = type;
    }

    @Override
    public CellType getType() {
        return this.type;
    }

    @Override
    public Set<Position> getPositions() {
        return Set.copyOf(this.positions);
    }

    @Override
    public int getSize() {
        return positions.size();
    }

    @Override
    public boolean isEmpty() {
        return positions.isEmpty();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((positions == null) ? 0 : positions.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final MatchImpl other = (MatchImpl) obj;
        return positions.equals(other.positions) && type == other.type;
    }

    @Override
    public String toString() {
        return "MatchImpl [positions=" + positions + ", type=" + type + "]";
    }
}
