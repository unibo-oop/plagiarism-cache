package it.unibo.pacman.model.leaderboard;

import java.util.Set;

import it.unibo.pacman.model.entities.Entity;
/**
 * Manages in-game scoring.
 */
public interface PlayerScore {
    /**
     * Get score.
     * 
     * @return score
     */
    int getScore();

    /**
     * Set score.
     * 
     * @param score is used to set score
     */
    void setScore(int score);

    /**
     * It updates score according to {@link Scoring}. 
     * @param entityToRemove 
     */
    void updateScore(Set<Entity> entityToRemove);
}
