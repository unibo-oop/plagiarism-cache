package it.unibo.modularcheckers.view;

import java.util.List;
import java.util.Map;

import it.unibo.modularcheckers.model.GameType;
import it.unibo.modularcheckers.model.Player;
import it.unibo.modularcheckers.model.PlayerType;

/**
 * Observer for the Start View.
 */
public interface StartViewObservable {

    /**
     * Players are created and returned in a list.
     * 
     * @param players A map containing the names (String) and the types (PlayerType)
     *                of the players.
     * @return A list containing the players inserted.
     */
    List<Player> insertPlayers(Map<String, PlayerType> players);

    /**
     * Set the game to play to "game".
     * 
     * @param game The game to be played.
     * 
     */
    void chooseGame(GameType game);

    /**
     * Exit the application.
     */
    void exit();

    /**
     * Start a new game.
     * 
     */
    void newGame();
}
