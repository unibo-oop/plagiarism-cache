package model;

import java.util.List;
import java.util.Optional;

import model.enums.PlayerNumber;
import model.match.PlaygroundBattle;
import model.gamemode.GameMode;
import model.match.players.MatchInfo;
import model.players.ArtificialPlayer;
import model.players.Player;
import model.players.PlayerManager;
import model.util.Pair;

/**
 * Main model interface containing all the main interactions available.
 * 
 */
public interface Model {

    /**
     * 
     * @param players 
                the list of players saved on system.
     * @return 
     *      Returns the model's player manager object.
     */
    PlayerManager setPlayerManager(Optional<List<Player>> players);

    /**
     * @return
     *      Returns the artificial player initialized.
     */
    ArtificialPlayer getArtificialPlayer();

    /**
     * 
     * @return 
     *      Returns a new PlaygroungBattle containing all the ships 
     *      positionated by AI basic implementation. 
     */
    PlaygroundBattle startBasicAI();

    /**
     * 
     * @return 
     *      Returns the point in which set the new attack, 
     *      calculated by AI basic implementation.
     */
    Pair<Integer, Integer> getNextHitPointBasicAI();

    /**
     * @return the current player
     */
    Optional<PlayerNumber> getCurrentPlayer();

    /**
     * @param playerNumber - the new current player
     */
    void setCurrentPlayer(PlayerNumber playerNumber);

    /**
     * this method checks whether the player has won the match according to selected win conditions.
     * @param hits - how many times the player has hit the opponent's ships.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    Boolean isMatchOver(int hits, int opponentRemainingShips);

    /**
     * @param gameMode - the new current gameMode
     */
    void setGameMode(GameMode gameMode);

    /**
     * @return the match's info, if set
     */
    Optional<MatchInfo> getMatchInfo();

    /**
     * @param info - match's info
     */
    void setMatchInfo(MatchInfo info);

    /**
     * sets the next player as current.
     */
    void nextPlayer();

}
