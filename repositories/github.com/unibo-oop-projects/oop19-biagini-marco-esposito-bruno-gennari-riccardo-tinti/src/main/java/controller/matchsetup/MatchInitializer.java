package controller.matchsetup;

import java.util.Optional;

import model.enums.PlayerType;
import model.gamemode.GameMode;

/**
 * This interface offers methods to start a new match.
 */
public interface MatchInitializer {

    /**
     * starts a new match using given parameters.
     * @param username1 - player1's username.
     * @param username2 - player2's username.
     * @param playerType - player2's type.
     * @param gameMode - selected game mode.
     */
    void startNewMatch(String username1, Optional<String> username2, PlayerType playerType, GameMode gameMode);
}
