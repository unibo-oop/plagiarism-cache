package controller.matchsetup;

import java.util.Optional;

/**
 * perform validity checks on the selected players.
 */
public interface PlayerChecker {

    /**
     * @param username1 - player1's username
     * @param username2 - player2's username
     * @param isPlayer2Ai - true if player2 is AI-controlled
     * @return true - if the selected players are considered valid.
     */
    boolean isPlayerSelectionValid(Optional<String> username1, Optional<String> username2, boolean isPlayer2Ai);

    /**
     * @param username - player's username
     * @return true if the player successfully authenticates
     */
    boolean areCredentialsValid(String username);

    /**
     * warns the user that there are not enough registered profiles to start a match.
     */
    void noProfilesAvailable();

}
