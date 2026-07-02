package utilitiesimpl;

import utilities.Seat;

public class SeatImpl implements Seat {
    private final Row row;
    private final Integer column;

    public SeatImpl(final Row row, final Integer column) {
        this.row = row;
        this.column = column;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Row getRow() {
        return this.row;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getColumn() {
        return this.column;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((column == null) ? 0 : column.hashCode());
        result = prime * result + ((row == null) ? 0 : row.hashCode());
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
        final SeatImpl other = (SeatImpl) obj;
        if (column == null) {
            if (other.column != null) {
                return false;
            }
        } else if (!column.equals(other.column)) {
            return false;
        }
        if (row != other.row) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return "SeatImpl [row=" + row + ", column=" + column + "]";
    }	
}
