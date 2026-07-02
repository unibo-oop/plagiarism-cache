
package model.score;

/**
 * This interface models points achieved by right answers.
 * 
 *
 */
public interface ScoreModel {

    /**
     * Add points when the user gave the right answer.
     */
    void addPoint();

    /**
     * Get the score of the minigame played.
     * 
     * @return score
     */
    int getScore();
}

