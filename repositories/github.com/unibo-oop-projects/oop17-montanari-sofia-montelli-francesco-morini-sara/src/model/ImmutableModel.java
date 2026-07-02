package model;

import java.util.List;

import model.game_data.ImmutableGameData;
import model.match.ImmutableMatch;
import model.navy.NavyBuilder;
import model.player.Player;
/**
 * model with only getter method.
 *
 */
import model.statistic.StatisticMatchManager;
/**
 * ImmutableModel offers only read-only methods (get).
 */
public interface ImmutableModel {
    /**
     * @return the list of each player name sorted by data 
     */
    List<String> getPlayerNameListOrdered();
    /**
     * @param name of the interested player
     * @return the player with name = {@link #name}
     */
    Player getPlayer(String name);
    /**
     * @return the GameData one
     * @throws IllegalStateException if the Game Data isn't present
     */
    ImmutableGameData getGameData1() throws IllegalStateException;
    /**
     * @return the GameData two
     * @throws IllegalStateException if the Game Data isn't present
     */
    ImmutableGameData getGameData2() throws IllegalStateException;
    /**
     * @return a new {@link NavyBuilder} builded upon the internal specification.
     * @throws IllegalStateException if the navy specifications are not present or if the grid size is not set 
     */
    NavyBuilder getNavyBuilder() throws IllegalStateException;

    /**
     * @return the internal specification of the builder.
     * @throws IllegalStateException if the navy specifications are not present
     */
    List<Integer> getSpecification() throws IllegalStateException;

    /**
     * @return the grid size
     * @throws IllegalStateException if the grid size is not set
     */
    int getGridSize() throws IllegalStateException;

    /**
     * @return true if both player have been setup
     */
    boolean canPlay();

    /**
     * Getter for match.
     * @return the current match
     */
    ImmutableMatch getMatch();

    /**
     * Getter for the statistic manager.
     * @return the {@link StatisticMatchManager} of the model.
     */
    StatisticMatchManager getStatManager();

    /**
     * @return the path for the player data set file.
     */
    String getPlayerPath();

     /**
      * @return the path for the player data set file.
      */
    String getStatisticsPath();
}
