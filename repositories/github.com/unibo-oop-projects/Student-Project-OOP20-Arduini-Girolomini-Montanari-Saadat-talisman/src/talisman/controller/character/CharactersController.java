package talisman.controller.character;

import talisman.model.character.CharacterModelImpl;
import talisman.model.character.PlayerModelImpl;

import java.util.List;

/**
 * An MVC Controller for the players
 *
 * @author Enrico Maria Montanari
 */

public interface CharactersController {

    /**
     * Gets an instance of the player who is their turn to play
     *
     * @return the player
     */
    PlayerModelImpl getCurrentPlayer();

    /**
     * Gets the amount of players currently registered in the game session
     *
     * @return the amount of players
     */
    int getActivePlayers();

    /**
     * Sets the player who is their turn to play
     *
     * @param index The id of the player
     */
    void setCurrentPlayer(int index);

    /**
     * Add a player to the game
     *
     * @param character the instance of the player
     */
    void addPlayer(CharacterModelImpl character);

    /**
     * Remove a player from the game
     *
     * @param index the id of the player
     */
    void removePlayer(int index);

    /**
     * Get all the registered players
     *
     * @return the array of players
     */
    List<PlayerModelImpl> getPlayers();

    /**
     * Gets the player who currently owns a crown
     *
     * @return the instance of the player or null if no player
     */
    PlayerModelImpl getCrownPlayer();
}
