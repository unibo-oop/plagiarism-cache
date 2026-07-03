package model.player;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;

import model.board.Position;
import model.cards.Card;
import utilities.enumerations.CharacterCard;
import utilities.enumerations.PlayerType;
import utilities.enumerations.RoomCard;

/**
 * This interface offers limited access to the player. It only shows the methods
 * that the Controller can use.
 */
public interface PlayerInfo extends Serializable {
    /**
     * Returns player name (character).
     * 
     * @return player name
     */
    CharacterCard getName();

    /**
     * Returns the type of player (human or AI).
     * 
     * @return the type of player
     */
    PlayerType getType();

    /**
     * Returns the cards of the player.
     * 
     * @return the cards of the player
     */
    Set<Card> getCards();

    /**
     * Returns the current room of the player.
     * 
     * @return an optional containing the current room of the player. It's empty
     *         if the player is in the hallway.
     */
    Optional<RoomCard> getRoom();

    /**
     * Returns the current position of the player.
     * 
     * @return the current position of the player
     */
    Position getPosition();

    /**
     * Returns true if the player can formulate a suspicion.
     * 
     * @return true if the player can formulate a suspicion
     */
    boolean canSuspect();

    /**
     * Returns the clues collected by the player.
     * 
     * @return the clues collected by the player
     */
    Map<Card, Boolean> getClues();

    /**
     * Updates player collected clues.
     * 
     * @param clues
     *            the clues collected by the player
     */
    void setClues(Map<Card, Boolean> clues);

    /**
     * Returns the player notes.
     * 
     * @return the player notes.
     */
    String getNotes();

    /**
     * Update player notes.
     * 
     * @param notes
     *            the notes of the player
     */
    void setNotes(String notes);

    /**
     * Returns the event history of the player.
     * 
     * @return the event history of the player
     */
    List<String> getHistory();

    /**
     * Adds an event to the player history.
     * 
     * @param description
     *            the description of the event
     */
    void addHistoryEvent(String description);

    /**
     * Returns true if the player is out of the game.
     * 
     * @return true if the player is out of the game, false otherwise
     */
    boolean isOut();
}