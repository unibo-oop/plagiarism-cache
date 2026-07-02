package view;

/**
 * This class manage the view of the game in play.
 * 
 */

public interface GameView {

    /**
     * This method repaint all the view.
     */

    void refresh();

    /**
     * This method update the value of dices and repaint the proper images of dices.
     * 
     * @param value
     *                  of the dices to show
     */
    void updateDices(Integer value);

    /**
     * This method update the value of Oxygen and repaint the proper images of Oxygen.
     */
    void updateOxygen();

    /**
     * This method update the update of players current situations and repaint the panel.
     */
    void updatePlayersSituation();

    /**
     * This method update change in the boardgame and repaint the panel.
     */
    void updateGameBoard();

    /**
     * This method update the new player in turn and repaint the panel.
     */
    void updatePlayerInTurn();

    /**
     * This method allow to update buttons and set the possible movements.
     */
   void updateMovementButtons();

    /**
     * This method allow to update buttons and set the possible actions.
     */
    void updateActionButtons();

    /**
     * This method disable all the buttons.
     */
    void updateDisableAllButtons();

    /**
     * @param message Message for players.
     */
    void updateMessageForPlayer(String message);

    /**
     * This method set the view to end game.
     */
    void updateEndGame();

    /**
     * @param message The game turn message.
     */
    void updateMessageGameTurn(String message);

}
