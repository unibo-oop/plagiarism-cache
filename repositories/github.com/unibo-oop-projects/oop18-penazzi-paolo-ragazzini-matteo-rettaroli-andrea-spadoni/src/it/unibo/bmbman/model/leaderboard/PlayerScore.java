package it.unibo.bmbman.model.leaderboard;

import java.util.List;

import it.unibo.bmbman.model.entities.Entity;

/**
 * Interface of PlayerScore.
 */
public interface PlayerScore {
    /**
     * Get name.
     * @return name
     */
    String getName();
    /** 
     * Get gameTime.
     * @return gameTime
     */
    String getGameTime();
    /**
     * Get score.
     * @return score
     */
    int getScore();
    /**
     * Get level.
     * @return level's number
     */
    int getLevel();
    /**
     * It updates score according to {@link Scoring}. 
     * If score is zero and hero has collected malus, it does nothing.
     * @param entityToRemoved 
     */
    void updateScore(List<Entity> entityToRemoved);
}
