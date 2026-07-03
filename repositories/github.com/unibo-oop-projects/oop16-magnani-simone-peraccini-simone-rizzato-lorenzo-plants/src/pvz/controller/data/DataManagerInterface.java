package pvz.controller.data;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import pvz.controller.Mode;


public interface DataManagerInterface {
/**
 * It adds a score to the data manager. The score will be registered into
 * player scores or general scores only if it is an high score.
 * 
 * @param score
 */

void addScore(final Score score,Mode mode);

/**
 * It sets the current player.
 * 
 * @param playerName
 */

void setPlayer(final String playerName);

/**
 * It registers a new player only if player name is not already used.
 * 
 * @param playerName
 */

boolean registerAndSetPlayer(final String playerName);

/**
 * It returns a list of current player's high scores for each mode.
 * 
 * 
 * @return current player's high scores
 */

Map<Mode,List<Score>> getPlayerHighScores(final String PlayerName);

/**
 * It returns the ranking of all players' scores.
 * 
 * @return
 */

Map<Mode,List<Score>> getAllPlayersHighScores();

/**
 * It saves the player informations and the high scores in a file.
 */

void saveData();

/**
 *  
 * @return true if the player already exist.
 */
boolean playerAlreadyExist(String playerName);

/**
 * Returns the list of all registered players.
 * 
 * @return registered players
 */
List<String> registeredPlayers();

/**
 * 
 * @return current player.
 */

Optional<Player> getCurrentPlayer();

}