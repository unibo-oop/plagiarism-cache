package view.select_player;

import view.interfaces.BasicView;

/**
 * Ui interface for the selection of the player.
 */
public interface SelectionPlayerUI extends BasicView {
    /**
     * Method for chose the player with whom to play.
     */
    void chosePlayer();
    /**
     * Method for chose the player Anonymous with whom to play.
     */
    void playerAnonymous();
    /**
     * Method for add a new player.
     */
    void addNewPlayer();
    /**
     * Opens a dialog that prompts for a password.
     * @param name name of the player of which you are creating the password
     * @return the password
     */
    String createPasswordInputDialog(String name);
    /**
     * Shows an error message.
     * @param message the message error
     */
    void errorMessage(String message);
}
