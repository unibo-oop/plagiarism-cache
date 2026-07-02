package controller.score;

import java.io.Serializable;
import java.util.Objects;

/**
 * Implements a serializable score of a player.
 */
public class Score implements Serializable {

    private static final long serialVersionUID = -5133674149751753600L;
    private final String name;
    private final int points;

    /**
     * Create the specific score for a player.
     * 
     * @param name   the players name
     * @param points the players points
     */
    public Score(final String name, final int points) {
        this.name = Objects.requireNonNull(name);
        this.points = this.checkPositive(points);
    }

    /*
     * Check and return the score if It's positive. Otherwise It throws an
     * IllegalArgumentException
     */
    private int checkPositive(final int score) {
        if (score < 0) {
            throw new IllegalArgumentException("Invalid score");
        }
        return score;
    }

    /**
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return the points of the player
     */
    public int getPoints() {
        return this.points;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + points;
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
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
        final Score other = (Score) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        if (points != other.points) {
            return false;
        }
        return true;
    }

}
