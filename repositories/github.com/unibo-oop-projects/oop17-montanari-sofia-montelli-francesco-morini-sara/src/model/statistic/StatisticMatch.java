package model.statistic;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;

import model.basic_component.Cell;
/**
 * Represents the match data for a single player.
 *  that will serve to manage and build statistics.
 *
 */
public interface StatisticMatch extends Serializable {
    /**
     * 
     * @return the name of the player.
     */
    String getPlayerName();
    /**
     * 
     * @return the name of the opponent.
     */
    String getOpponentName();
    /**
     * 
     * @return the date of the onset of the game.
     */
    LocalDateTime getMatchStartDate();
    /**
     * 
     * @return the history of the shots performed by this player.
     */
    List<Cell> getShootsPerformed();
    /**
     * 
     * @return the history of shots received by this player.
     */
    List<Cell> getShootsReceived();
    /**
     * 
     * @return the number of shots performed.
     */
    long getShotPerformedCount();
    /**
     * 
     * @return the number of shots that hit the opposing ships.
     */
    long getShotPerformedHitCount();
    /**
     * 
     * @return the number of shots received.
     */
    long getShotReceivedCount();
    /**
     * 
     * @return the number of hits received that hit the player's ships.
     */
    long getShotReceivedHitCount();
    /**
     * 
     * @param winner says if the player is the winner or not.
     */
    void setWinner(boolean winner);
    /**
     * 
     * @return a boolean that says if the player is the winner or not.
     */
     boolean isWinner();
     /**
      * method that is used to update the data.
      * for now it is not necessary, but it is kept for a possible need.
      * @throws NotImplementedException because is not necessary now.
      */
     void update() throws NotImplementedException;

}
