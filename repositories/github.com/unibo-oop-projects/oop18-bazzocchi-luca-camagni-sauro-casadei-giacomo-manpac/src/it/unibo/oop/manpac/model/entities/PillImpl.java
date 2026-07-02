package it.unibo.oop.manpac.model.entities;

import it.unibo.oop.manpac.model.score.Eatable;
import it.unibo.oop.manpac.model.score.Points;

/**
 * Implementation of Eatable interface for modeling a pill.
 */
public class PillImpl implements Eatable {

    private final Points<Integer> points;

    /**
     * Constructor of PillImpl.
     * @param points points awarded by eating this pill
     */
    public PillImpl(final Points<Integer> points) {
        this.points = points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Points<Integer> getPoints() {
        return this.points;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((points == null) ? 0 : points.hashCode());
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
        final PillImpl other = (PillImpl) obj;
        return (this.points != other.getPoints());

    }

}
