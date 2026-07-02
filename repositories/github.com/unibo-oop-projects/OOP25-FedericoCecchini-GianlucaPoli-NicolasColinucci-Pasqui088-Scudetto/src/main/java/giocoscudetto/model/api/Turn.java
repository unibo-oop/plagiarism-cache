package giocoscudetto.model.api;

import giocoscudetto.model.api.match.Club;

/**
 * Interface that manages player turns during a match.
 */
public interface Turn {

    /** 
     * Chooses the starting player.
     */
    void chooseStartingPlayer();

    /**
     * Returns the current player.
     * 
     * @return the player whose turn it is 
     */
    Club getCurrentPlayer();

    /**
     * Switches the turn to the other player.
     */
    void switchTurn();

    /**
     * @return the name of the player that is not the current player.
     */
    Club getNotCurrentPlayer();
}
