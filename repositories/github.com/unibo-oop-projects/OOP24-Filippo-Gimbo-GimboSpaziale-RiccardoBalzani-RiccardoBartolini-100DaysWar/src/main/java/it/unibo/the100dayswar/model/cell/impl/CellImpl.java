package it.unibo.the100dayswar.model.cell.impl;

import java.util.Objects;
import java.util.Optional;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import it.unibo.the100dayswar.model.cell.api.Cell;
import it.unibo.the100dayswar.model.unit.api.Unit;
/**
 * Class that model the concept of a generic cell.
 */
public  class CellImpl implements Cell {
    private static final long serialVersionUID = 1L;

    private final Position position;
    private final boolean isBuildable;
    private final boolean isSpawn;
    private Unit currentUnit;

    /**
     * Constructor from coordinates and cell characteristics.
     * @param coordinate coordinates to identify the cell in the map.
     * @param isBuildable true if the cell can be built upon.
     * @param isSpawn true if the cell is a spawn point.
     */
    public CellImpl(final Position coordinate, final boolean isBuildable, final boolean isSpawn) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Position cannot be null");
        }
        this.position = new PositionImpl(coordinate);
        this.isBuildable = isBuildable;
        this.isSpawn = isSpawn;
        this.currentUnit = null;
    }
    /**
     * Constructor from a cell.
     * @param cell  identify the cell in the map.
     */
    public CellImpl(final Cell cell) {
        this.position = cell.getPosition();
        this.isBuildable = cell.isBuildable();
        this.isSpawn = cell.isSpawn();
        this.currentUnit = cell.getUnit().orElse(null);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Position getPosition() {
        return new PositionImpl(position);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isBuildable() {
        return this.isBuildable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSpawn() {
        return this.isSpawn;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Unit> getUnit() {
        return Optional.ofNullable(this.currentUnit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFree() {
        return this.currentUnit == null && this.isBuildable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setOccupation(final Optional<Unit> unit) {
        this.currentUnit = unit.orElse(null);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public  boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CellImpl)) {
            return false;
        }
        final CellImpl other = (CellImpl) obj;
        return Objects.equals(this.position, other.position)
                && Objects.equals(this.isSpawn, other.isSpawn)
                && Objects.equals(this.isBuildable, other.isBuildable)
                && Objects.equals(this.currentUnit, other.currentUnit);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.position, this.isSpawn, this.isBuildable, this.currentUnit);
    }

    /**
     * 
     * @param pos is the postion that will be checked.
     * @return true if the cell is in diagonal rather than the actusl cell.
     */
    private boolean isDiagonal(final Position pos) {
        final Position actualPos = this.getPosition();
        return (pos.getX() == actualPos.getX() - 1 && pos.getY() == actualPos.getY() - 1) 
                || (pos.getX() == actualPos.getX() + 1 && pos.getY() == actualPos.getY() + 1) 
                || (pos.getX() == actualPos.getX() - 1 && pos.getY() == actualPos.getY() + 1) 
                || (pos.getX() == actualPos.getX() + 1 && pos.getY() == actualPos.getY() - 1);
    }

    /**
     * @param pos is the postion that will be checked.
     * @return true if the cell is in cross rather than the actusl cell.
     */
    private boolean isCross(final Position pos) {
        final Position actualPos = this.getPosition();
        return (pos.getX() == actualPos.getX() && pos.getY() == actualPos.getY() - 1) 
                || (pos.getX() == actualPos.getX() && pos.getY() == actualPos.getY() + 1) 
                || (pos.getX() == actualPos.getX() - 1 && pos.getY() == actualPos.getY()) 
                || (pos.getX() == actualPos.getX() + 1 && pos.getY() == actualPos.getY());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdiacent(final Cell cell) {
        final Position checkPos = cell.getPosition();
        return this.isCross(checkPos) || this.isDiagonal(checkPos);
    }
}
