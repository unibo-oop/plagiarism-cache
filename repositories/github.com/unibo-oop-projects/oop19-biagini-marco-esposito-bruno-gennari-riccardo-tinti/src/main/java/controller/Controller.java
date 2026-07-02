package controller;

import java.util.Optional;

import controller.game.MatchController;
import controller.users.AccountManager;
import model.enums.PlayerNumber;
import model.gamemode.GameMode;
import model.match.players.MatchInfo;
import view.dialog.DialogType;
import view.scene.SceneName;

/**
 * The MVC Controller of the app.
 */
public interface Controller {

    /**
     * Load a different scene into the application's stage.
     * @param name - the scene's name
     */
    void changeScene(SceneName name);

    /**
     * Launch a dialog using the passed parameters.
     * Parameters you don't care about can be passed as null.
     * @param type - the type of dialog to launch.
     * @param title - dialog's title
     * @param header - dialog's header
     * @param description - dialog's description
     * @return the result of the dialog's operations, if any.
     */
    Optional<String> launchDialog(DialogType type, String title, String header, String description);

    /**
     * 
     * @return the controller's account manager object.
     */
    AccountManager getAccountManager();

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
     * @param playerHits - how many times the player has hit the opponent's ships.
     * @param opponentRemainingShips - how many (not sunk) ships the opponent still has.
     * @return true - if the player has won the match.
     */
    Boolean isMatchOver(int playerHits, int opponentRemainingShips);

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
     * Method to get the controller of match.
     * 
     * @return the controller of the match
     */
    MatchController getMatchController();

    /**
     * sets the next player as current.
     */
    void nextPlayer();

    /**
     * Set AI playground. 
     */
    void setAI();

    /**
     * Shot AI's opponent playground.
     */
    void shotAI();

}
