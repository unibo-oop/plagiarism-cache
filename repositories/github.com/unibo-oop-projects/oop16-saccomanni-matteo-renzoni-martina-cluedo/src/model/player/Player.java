package model.player;

import com.google.common.base.Optional;

import model.board.Cell;
import model.cards.Card;
import model.cards.Solution;

/**
 * This interface represents a player of the game. It extends PlayerInfo by
 * adding other setters and methods used only by the Model.
 */
public interface Player extends PlayerInfo {

    /**
     * This method is called only when a player loses or retires and stores his
     * cards as not part of the solution.
     * 
     * @param player
     *            player that left the game
     */
    void registerPlayerCards(PlayerInfo player);

    /**
     * Changes player cell.
     * 
     * @param newPosition
     *            the new player cell.
     */
    void setCell(Cell newPosition);

    /**
     * Set whether the player has already made an accusation.
     * 
     * @param alreadySuspected
     *            true if the player already suspected, false otherwise
     */
    void haveSuspected(boolean alreadySuspected);

    /**
     * Called if the player lose the game, then the player is out.
     */
    void gameOver();

    /**
     * Returns one of the requested cards (if the player owns one).
     * 
     * @param opponent
     *            the player who asked to see a card
     * @param cards
     *            requested cards
     * @return one of the requested cards (if the player owns one)
     */
    Optional<Card> showCard(PlayerInfo opponent, Solution cards);
}