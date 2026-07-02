package it.unibo.modularcheckers.model.piece;

import it.unibo.modularcheckers.model.Color;

/**
 * AbstractPiece implementing getColor.
 */
public abstract class AbstractPiece implements Piece {

    /**
     *
     */
    private static final long serialVersionUID = -9165659735212458951L;
    private final Color color;

    /**
     *
     * @param color is the color of the piece.
     */
    public AbstractPiece(final Color color) {
        this.color = color;
    }

    /**
     * {@inheritDoc}}
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
        final AbstractPiece other = (AbstractPiece) obj;
        if (color != other.color) {
            return false;
        }
        return true;
    }

    /**
     * @return the color
     */
    @Override
    public Color getColor() {
        return color;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((color == null) ? 0 : color.hashCode());
        return result;
    }

}
