package it.unibo.monoopoly.model.gameboard.api;

import java.util.List;

import it.unibo.monoopoly.model.deck.api.Deck;
import it.unibo.monoopoly.model.player.api.Player;

/**
 * This interface rapresents the game board of the game.
 * Save the current player in turn amd check if the game is ended.
 */
public interface GameBoard {

    /**
     * The next method return the cell using the index.
     * @param index the index of cell
     * @return cell in index position
     */
    Cell getCell(int index);

    /**
     * Remove a player from the game.
     */
    void removePlayer();

    /**
     * Control if the game is ended checking the number of player remaining.
     * 
     * @return true if the game is ended, false otherwise
     */
    boolean isGameEnded();

    /**
     * Set the next player.
     */
    void nextPlayer();

    /**
     * Gets the current player to play.
     * 
     * @return the current player to play
     */
    Player getCurrentPlayer();

    /**
     * Gets the the list of players in game.
     * 
     * @return the list of players in game
     */
    List<Player> getPlayersList();

    /**
     * Gets the list of names of cells.
     * 
     * @return the list of names of cells
     */
    List<String> getCellsNames();

    /**
     * Gets the list of cells.
     * 
     * @return the list of cells
     */
    List<Cell> getCellsList();

    /**
     * Gets the dices.
     * 
     * @return the dices
     */
    Dices getDices();

    /**
     * Gets the deck.
     * 
     * @return the deck.
     */
    Deck getDeck();

}
