package controller.gamecontroller;

import controller.command.Action;
import controller.playercontroller.PlayerController;

/**
 * Modeling interface for the Controller of the game: manages the creation of View/Player(s) and the progress of the game.
 * This interface also defines the way a Player can interact with his opponent.
 */
public interface Controller {

    /**
     * Allows Players to communicate the selected Character.
     * @param player 
     *                  the Controller of the Player
     * @param characterName 
     *                  the name of the selected Character
     */
    void selected(PlayerController player, String characterName);

    /**
     * Allows Players to ask question to the opponent.
     * @param action 
     *                  the action to do.
     */
    void askOpponent(Action action);

    /**
     * Allows Players to properly quit the game.
     * @param player 
     *                  the Controller of the Player who wants to quit.
     */
    void endGame(PlayerController player);

}
