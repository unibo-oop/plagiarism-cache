package model.environment.cell;

import model.environment.Point;
import constraints.CellConstraints;

/**
 *
 */
public class CellImpl implements Cell {

    private Point position;
    private CellConstraints typecell;

    /**
     * @param position of the cell
     * @param type of the cell
     */
    public CellImpl(final CellConstraints type, final Point position) {
        this.typecell = type;
        this.position = position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Point getPosition() {

        return this.position;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPosition(final Point point) {
        this.position = point;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CellConstraints getType() {

        return this.typecell;
    }

    @Override
    public final int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.position == null) ? 0 : this.position.hashCode());
        result = prime * result + ((this.typecell == null) ? 0 : this.typecell.hashCode());
        return result;
    }

    @Override
    public final boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        final CellImpl other = (CellImpl) obj;
        if (this.position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!this.position.equals(other.position)) {
            return false;
        }
        if (this.typecell != other.typecell) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "CellImpl [position=" + this.position + ", typecell=" + this.typecell + "]";
    }

    public final void setType(final CellConstraints type) {
        this.typecell = type;
    }

}
