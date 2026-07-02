package model.player;

import java.util.List;
import java.util.Set;

/**
 * Manages the interaction with the {@link FileDataAccessManager} interface
 * by creating the map of the saved players.
 */
public interface PlayersManipulationDB {
    /**
     * @return a list with the names of the players
     */
    List<String> getPlayerNameList();
    /**
     * @return a list with the names of the players sorted by date of last access 
     */
    List<String> getPlayerNameOrderedList();
    /**
     * @return a set with the players
     */
    Set<Player> getPlayerSet();
    /**
     * @param name of the player who needs information
     * @return the player with name: name
     */
    Player getPlayer(String name);
    /**
     * this method manage the selection of the player,
     * calling the appropriate methods of the player and the update of the file.
     * @param player the player chosen to play 
     */
    void selectedPlayer(Player player);
    /**
     * Adds a new player.
     * @param name name of the new player
     * @throws IllegalArgumentException if the {@link #name} is already present
     */
    void newPlayer(String name) throws IllegalArgumentException;
}
