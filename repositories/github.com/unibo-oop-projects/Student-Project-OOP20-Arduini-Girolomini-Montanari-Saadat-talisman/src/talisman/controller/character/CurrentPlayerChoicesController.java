package talisman.controller.character;

import java.util.List;

import talisman.model.cards.Card;
import talisman.view.CurrentPlayerChoicesWindow;

/**
 * Interface that controls the current player's choices.
 * 
 * @author Alice Girolomini
 *
 */
public interface CurrentPlayerChoicesController {

    /**
     * Checks whether the player rolled the dice or not.
     * 
     * @return true if the player rolled the dice
     */
    boolean checkRoll();

    /**
     * Checks if there are available opponents.
     * 
     * @return true if there are available opponents
     */
    boolean checkOpponents();

    /**
     * Rolls the dice.
     * 
     * @return the result
     */
    int getDiceRoll();

    /**
     * Gets the current player's index.
     * 
     * @return the index
     */
    int getCurrentPlayerIndex();

    List<Card> getCurrentCharacterCards();

    /**
     * Moves the current player's pawn.
     */
    void movePawn();

    /**
     * Passes the turn.
     */
    void passTurn();

    /**
     * Applies the cell event.
     */
    void cellEvent();

    /**
     * Opens the OpponentChoiceWindow and starts the fight.
     */
    void challengeCharacter();

    /**
     * If there is an enemy on the current cell, start the fight with it.
     */
    void challengeEnemy();

    /**
     * Skips the current turn.
     */
    void skipTurn();

    /**
     * Gets the current view.
     * 
     * @return the view
     */
    CurrentPlayerChoicesWindow getView();

    /**
     * Creates the current player's controller.
     * 
     * @param index - index of the current player
     * @return the controller
     */
    static CurrentPlayerChoicesController create(int index) {
        return new CurrentPlayerChoicesControllerImpl(index);
    }
}
