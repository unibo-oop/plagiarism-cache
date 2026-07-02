package it.unibo.modularcheckers.model.move;

import com.google.common.base.Optional;
import it.unibo.modularcheckers.model.Coordinate;
import it.unibo.modularcheckers.model.Pair;
import it.unibo.modularcheckers.model.piece.Piece;

/**
 * Basic implementation of Step.
 */
public class StepImpl implements Step {

    private final Coordinate coordinate;
    private Pair<Coordinate, Piece> deadPiece;

    /**
     * @param coordinate The coordinate of the piece in this step.
     */
    public StepImpl(final Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    /**
     * @param coordinate The coordinate of the piece in this step.
     * @param deadPiece  The information on the dead piece in this step.
     */
    public StepImpl(final Coordinate coordinate, final Pair<Coordinate, Piece> deadPiece) {
        this.coordinate = coordinate;
        this.deadPiece = deadPiece;
    }

    /**
     * @return the coordinate of this step.
     */
    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    /**
     * @return the info of the dead piece in this step if present.
     */
    @Override
    public Optional<Pair<Coordinate, Piece>> getDeadPiece() {
        return Optional.fromNullable(this.deadPiece);
    }

    /*
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Step: [Coordinate: (" + getCoordinate() + "), Dead Piece: ("
                + ((getDeadPiece().isPresent())
                        ? getDeadPiece().get().getX() + " " + getDeadPiece().get().getY().toString()
                        : "None")
                + ")]";
    }

    @Override
    /**
     * Hash function for equals.
     */
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((coordinate == null) ? 0 : coordinate.hashCode());
        result = prime * result + ((deadPiece == null) ? 0 : deadPiece.hashCode());
        return result;
    }

    @Override
    /**
     * Equals method for Step.
     */
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
        final StepImpl other = (StepImpl) obj;
        if (coordinate == null) {
            if (other.coordinate != null) {
                return false;
            }
        } else if (!coordinate.equals(other.coordinate)) {
            return false;
        }
        if (deadPiece == null) {
            if (other.deadPiece != null) {
                return false;
            }
        } else if (!deadPiece.equals(other.deadPiece)) {
            return false;
        }
        return true;
    }

}
