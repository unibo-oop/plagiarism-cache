package models;

import java.util.List;

/**
 * The Scores interface gives contracts to generate different implementation of scores.
 * This follows Strategy Pattern to give the program more extensibility and removing redundant usage of code.
 */
public interface Scores {
    
    /**
     * Save a new score.
     * 
     * @param name the name of the player 
     * @param score the score achieved by player
     */
    void setScore(String name, Integer score);
    
    /**
     * Get all saved scores.
     * 
     * @return All saved scores
     */
    List<Pair<String, Integer>> getAllScores();
    
    /**
     * Get one single score
     * 
     * @return The name and the score of one game played
     */
    String getScore();
}
