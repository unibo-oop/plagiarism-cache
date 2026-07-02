package model;

import java.util.List;

import model.game_data.GameData;
import model.match.Match;
import model.navy.Navy;
import model.player.Player;

/**
 * The interactive model extends {@link ImmutableModel} by adding
 * method designed for the interaction.
 */
public interface Model extends ImmutableModel {
    /**
     * Add new player to the model.
     * @param playerName is the name of the player to add
     */
    void addNewPlayer(String playerName);
    /**
     * Add the data in the GameData.
     * @param player the player choose in order to play
     * @param password the password of the {@link Player} p
     * @throws IllegalStateException if the data of both players are already set
     */
    void setPlayerData(Player player, String password) throws IllegalStateException;
    /**
     * Sets the specification of the {@link Navy} composition.
     * @param specification is a list representing the specification
     * @param gridSize is the grid's side
     * @throws IllegalArgumentException if the grid's side and the navy specification are not coherent
     * @throws IllegalArgumentException if the provided specification is not a valid ship composition
     *                       - length not equals to the max size of a ship
     *                       - every element of the list equals to zero
     */
    void setSpecification(List<Integer> specification, int gridSize) throws IllegalArgumentException;
    /**
     * @return the GameData one
     * @throws IllegalStateException if the Game Data isn't present
     */
    GameData getGameData1() throws IllegalStateException;
    /**
     * @return the GameData two
     * @throws IllegalStateException if the Game Data isn't present
     */
    GameData getGameData2() throws IllegalStateException;
    /**
     * Sets the navy for the current player.
     * @param navy the {@link Navy} to set.
     * @throws IllegalStateException if the current player does not need a navy
     * @throws IllegalArgumentException if the navy passed as a parameter is different from that of the specifications 
     */

    void setNavy(Navy navy) throws IllegalStateException, IllegalArgumentException;

    /**
     * Getter for the current match.
     * @return a intractable match
     */
    Match getMatch();

    /**
     * This method handles the turn exchange process.
     */
    void changeTurn();

    /**
     * Add the specified path to the model.
     * @param playerFile the path for the player
     * @param statisticsFile the path for the statistics
     */
    void setPaths(String playerFile, String statisticsFile);
}
