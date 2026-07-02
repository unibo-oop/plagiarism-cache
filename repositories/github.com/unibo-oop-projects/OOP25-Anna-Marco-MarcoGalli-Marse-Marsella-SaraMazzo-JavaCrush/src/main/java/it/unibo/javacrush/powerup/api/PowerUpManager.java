package it.unibo.javacrush.powerup.api;

import java.util.Set;

import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.model.api.Board;
import it.unibo.javacrush.model.api.Match;

/**
 * This interface PowerUpManager manages all the PowerUps of the game.
 */
public interface PowerUpManager {

    /**
     * Check if there is a PowerUp already selected.
     * 
     * @return true if there's a PowerUp selected.
     */
    Boolean isPowerUpSelected();

    /**
     * Select one of the possible PowerUps.
     * 
     * @param num the index of the PowerUp to select.
     * @return false if the specified PowerUp doesn't exist.
     */
    Boolean selectPowerUp(int num);

    /**
     * Reset the selection of the PowerUp.
     * 
     * @return true if the resetting operation had success.
     */
    Boolean resetPowerUpSelection();

    /**
     * Apply the selected PowerUp.
     * 
     * @param board the board where to work.
     * @param pos the position on where apply the PowerUp.
     * @return true if there wasn't any problem, false if something went wrong.
     */
    Boolean applyPowerUp(Board board, Position pos);

    /**
     * Get all the Matches obtained by applying the selected PowerUp.
     * 
     * @return a Set of Matches.
     */
    Set<Match> getMatches();

}
