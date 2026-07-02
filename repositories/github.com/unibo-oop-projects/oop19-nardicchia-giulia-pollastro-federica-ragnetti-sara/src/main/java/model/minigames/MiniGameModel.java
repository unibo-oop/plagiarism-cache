package model.minigames;

/**
 * This interface represents the common parts of the minigame model.
 *
 */
public interface MiniGameModel {

    /**
     * Add points at the score of the game.
     */
    void addPoint();

    /**
     * Gets the game's final score.
     * 
     * @return the final score
     */
    int getFinalScore();
}
