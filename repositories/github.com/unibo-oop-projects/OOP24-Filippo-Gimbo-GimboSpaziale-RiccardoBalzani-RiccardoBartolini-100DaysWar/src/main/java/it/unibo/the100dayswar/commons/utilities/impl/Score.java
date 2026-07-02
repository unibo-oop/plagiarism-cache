package it.unibo.the100dayswar.commons.utilities.impl;

import java.io.Serializable;
import java.util.Objects;

/**
 * A simple class that represents a score.
 */
public class Score implements Comparable<Score>, Serializable {
    private static final long serialVersionUID = 1L;
    private final int value;
    /**
     * Constructor of the class by a given value.
     * 
     * @param value the value of the score
     */
    public Score(final int value) {
        this.value = value;
    }
    /**
     * Getter of the value of the score.
     * 
     * @return the value of the score
     */
    public int getValue() {
        return value;
    }
    /** 
     * A method that compares two scores.
     */
    @Override
    public int compareTo(final Score other) {
        return Integer.compare(this.value, other.value);
    }
    /** 
     * Overrides the equals method to ensure consistency with compareTo.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Score)) {
            return false;
        }
        final Score other = (Score) obj;
        return this.value == other.value;
    }
    /** 
     * Overrides the hashCode method to ensure consistency with equals.
     */
    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
    /** 
     * Provides a string representation of the score.
     */
    @Override
    public String toString() {
        return "Score{" 
        + "value=" + value 
        + '}';
    }
}
