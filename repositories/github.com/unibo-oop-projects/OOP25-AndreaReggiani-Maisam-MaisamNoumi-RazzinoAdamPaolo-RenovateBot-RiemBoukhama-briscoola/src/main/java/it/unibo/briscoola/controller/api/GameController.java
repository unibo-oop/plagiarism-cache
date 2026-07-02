package it.unibo.briscoola.controller.api;

/**
 * Interface representing the game controller.
 * It manages the game flow, turns and the user interactions.
 * 
 * @author Maisam Noumi
 */
public interface GameController {

    /**
     * Starts the match by preparing the model, initializing the graphics,
     * displaying the initial hands and starting the first turn.
     */
    void startGame();

    /**
     * Handles the current turn logic by checking the game state.
     * If the game is over, it stops the loop and notifies the view.
     * If the round is over, it assigns points, clears the table, update hands and starts a new round.
     * If it's the CPU's turn, it calculates and plays the move automatically.
     * If it's the human's turn, the method returns, leaving the GUI waiting for a user click.
     */
    void manageTurn();

    /**
     * Handles the card selected by the human player.
     * The index corresponds to the card's position in the player's hand.
     * 
     * @param selectedIndex the position of the selected card in the player's hand
     */
    void handlesHumanCardSelection(int selectedIndex);
}
