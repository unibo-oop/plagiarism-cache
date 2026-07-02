package controller.stagehandler;

import model.ship.PlayerShip.PlayerScore;

/**
 * Controls the stage and every stage-related event.
 */
public interface StageHandler {

    /**
     * Advances the game on one stage. The game is considered to be developed 
     * through a sequence of turns.
     */
    void nextTurn();

    /**
     * Returns true if the current level has ended.
     * @return true if the current level has ended; false otherwise.
     */
    boolean isLevelCompleted();

    /**
     * Returns true if the game has ended.
     * @return true if the game has ended; false otherwise.
     */
    boolean isGameOver();

    /**
     * Returns the final score achieved by the player of this game.
     * @return the final score achieved by the player of this game.
     */
    PlayerScore getFinalScore();

}
