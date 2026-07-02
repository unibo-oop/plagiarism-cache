package gameselectionmenu.model;

import java.util.List;

import model.gamerules.GameRules;
import model.player.Player;

/**
 * The SelectionMenuModel interface represents the model part of the players
 * selection menu. The menu includes the choice of the number of players and the
 * relative race.
 */
public interface SelectionMenuModel {

    /**
     * Method that sets the current number of selected players.
     * 
     * @param currentPlayers is the number of players to set.
     */
    void setCurrentPlayers(int currentPlayers);

    /**
     * @return the number of players currently selected by the user.
     */
    int getPlayerNumber();

    /**
     * @return the minimum number of players that could be set.
     */
    int getMinimumPlayers();

    /**
     * @return the maximum number of players that could be set.
     */
    int getMaximumPlayers();

    /**
     * @return the list of players declared.
     */
    List<Player> getPlayers();

    /**
     * @return the list with all the races available within the game.
     */
    List<String> getRaceNameList();

    /**
     * Method that checks if the player can be added, and if it can then adds it.
     * The player will not be added if he has a race and / or a name already
     * selected by another player.
     * 
     * @param id       is the player id.
     * @param name     is the name of the player to add.
     * @param raceName is the race of the player to add.
     * @return true if the player has been added.
     */
    boolean canAddThePlayer(int id, String name, String raceName);

    /**
     * @return true if the conditions to get the game to start are all met.
     */
    boolean canStart();

    /**
     * @return the selected game mode
     */
    GameRules getSelectedGameMode();

}
