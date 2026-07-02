package controller.player_selection;

import java.util.List;

import controller.SpecificController;

/** 
 * Controller for the {@link SelectionPlayerUI}.
 */
public interface PlayerSelectionUIController extends SpecificController {
    /**
     * @return a List with the player namer ordered ordered 
     *         from the most recently used to the less recently used
     */
    List<String> getPlayerNameList();
    /**
     * @param playerName the new player name to add
     */
    void addPlayer(String playerName);
    /**
     * @param name the name to set in order to play
     */
    void setName(String name);
    /**
     * @param password the password to set for the player chose
     */
    void setPassword(String password);
    /**
     * @throws IllegalStateException if name or password is not set
     */
    void confirmData() throws IllegalStateException;
}
