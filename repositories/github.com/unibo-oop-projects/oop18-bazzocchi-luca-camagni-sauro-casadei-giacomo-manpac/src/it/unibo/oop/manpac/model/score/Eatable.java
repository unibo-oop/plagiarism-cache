package it.unibo.oop.manpac.model.score;

/**
 * Every entity that can be eaten by PacMan must implement this interface with
 * just the getPoints method; when this method is called, it returns the score
 * of the eaten entity.
 *
 */
public interface Eatable {

    /**
     * @return the score of the eaten entity
     */
    Points<Integer> getPoints();
}
