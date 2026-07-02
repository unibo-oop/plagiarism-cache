package it.unibo.controller;

import it.unibo.controller.interfaces.TickListenerInterface;
import it.unibo.model.Grid;
import it.unibo.model.ScoreModel;
import it.unibo.model.StatusModel;

/**
 * The EndController class is responsible for monitoring the game state and
 * determining when the game should end based on score and grid status.
 */
public class EndController implements TickListenerInterface {
    /**
     * The game grid model.
     */
    private final Grid grid;
    /**
     * The score model tracking the player's score.
     */
    private final ScoreModel score;
    /**
     * The status model managing the game's state.
     */
    private final StatusModel gameStatus;
    /**
     * Score thresholds for awarding stars.
     */
    private static final int[] THRESHOLDS = { 200, 350, 500 };

    /**
     * Constructor for initializing the EndController.
     * 
     * @param grid       the grid model.
     * @param score      the model tracking the player's score.
     * @param gameStatus the model managing the game's state.
     */
    public EndController(Grid grid, ScoreModel score, StatusModel gameStatus) {
        this.grid = grid;
        this.score = score;
        this.gameStatus = gameStatus;
    }

    /**
     * Called on each game tick to check if the game should end.
     * Update the game's status based on score and grid conditions.
     */
    @Override
    public void onTick() {
        /**
         * Check score thresholds to assign stars and potentially end the game.
         */
        if (this.score.getScore() >= THRESHOLDS[2]) {
            this.gameStatus.setStars(3);
            this.score.setScore(Math.min(THRESHOLDS[2], this.score.getScore()));
            this.gameStatus.setGameEnded();
        } else if (this.score.getScore() >= THRESHOLDS[1]) {
            this.gameStatus.setStars(2);
        } else if (this.score.getScore() >= THRESHOLDS[0]) {
            this.gameStatus.setStars(1);
        }
        /**
         * End the game if the grid is full
         */
        if (this.grid.isGridFull()) {
            this.gameStatus.setGameEnded();
        }
    }
}
