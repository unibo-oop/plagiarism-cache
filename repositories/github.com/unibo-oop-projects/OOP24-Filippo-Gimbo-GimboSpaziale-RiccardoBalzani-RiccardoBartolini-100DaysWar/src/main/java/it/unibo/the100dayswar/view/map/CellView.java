package it.unibo.the100dayswar.view.map;

import java.util.Objects;
import java.io.Serializable;

import it.unibo.the100dayswar.commons.utilities.api.Position;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;

/**
 * Represents a cell view.
 */
public class CellView implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Position position;
    private final String imagePath;

    /**
     * Creates a new cell view.
     * 
     * @param pos the position of the cell.
     * @param imagePath the path of the image.
     */
    public CellView(final Position pos, final String imagePath) {
       this.position = new PositionImpl(pos);
        this.imagePath = imagePath;
    }

    /**
     * Returns the x coordinate of the cell.
     * @return the x coordinate.
      */
    public int getX() {
        return this.position.getX();
    }

    /**
     * Returns the y coordinate of the cell.
     * @return the y coordinate.
     */
    public int getY() {
        return this.position.getY();
    }

    /**
     * Returns the path of the image of the cell.
     * @return the path of the image.
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final CellView cellView = (CellView) obj;
        return this.getX() == cellView.getX() && this.getY() == cellView.getY();
    }

    /** 
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getX(), this.getY());
}
}
