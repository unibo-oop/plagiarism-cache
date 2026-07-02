package controller.matchsetup;

import java.util.Optional;

import application.Battleships;
import controller.Controller;
import controller.users.AccountManager;
import view.dialog.DialogType;

/**
 * this class performs validity checks on the selected players.
 */
public final class PlayerCheckerImpl implements PlayerChecker {

    private final AccountManager accountManager = Battleships.getController().getAccountManager();
    private final Controller controller = Battleships.getController();

    /**
     * @param username1 - player1's username
     * @param username2 - player2's username
     * @param isPlayer2Ai - true if player2 is AI-controlled
     * @return true - if the selected players are considered valid.
     */
    public boolean isPlayerSelectionValid(final Optional<String> username1, final Optional<String> username2, final boolean isPlayer2Ai) {
        if (!username1.isPresent() || (!username2.isPresent() && !isPlayer2Ai)) {
            controller.launchDialog(DialogType.ERROR, "Error!", "Some players have no profile selected!\nChange your selection and try again.", null);
        } else if (username1.equals(username2) && !isPlayer2Ai) {
            controller.launchDialog(DialogType.ERROR, "Error!", "Player1 and Player2 cannot be the same!\nChange your selection and try again.", null);
        } else {
            return true;
        }
        return false;
    }

    /**
     * @param username - player's username
     * @return true if the player successfully authenticates
     */
    public boolean areCredentialsValid(final String username) {
        final Optional<String> password = controller.launchDialog(DialogType.LOGIN, "Login", "Insert password for user \"" + username + "\"", null);
        if (password.isPresent() && accountManager.logInAccount(username, password.get())) {
            controller.launchDialog(DialogType.INFORMATION, "Login", "Login successful!", null);
            return true;
        } else {
            controller.launchDialog(DialogType.ERROR, "Login", "Invalid account credentials!", null);
            return false;
        }
    }

    /**
     * warns the user that there are not enough registered profiles to start a match.
     */
    public void noProfilesAvailable() {
        controller.launchDialog(DialogType.WARNING, "Warning: No Profiles Available", "You need to create at least a profile in order to play.\n"
                + "From the Main Menu, click \"Profile\" to create a new account.", null);
    }
}
