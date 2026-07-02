package starcatraz.model.game;

import java.util.Optional;
import javafx.collections.ObservableList;
import starcatraz.model.cards.Card;
import starcatraz.model.cards.CardColor;

/**
 * Game model.
 */
public interface Game {

    /**
     * @return unmodifiable ObservableList containing the deck
     */
    ObservableList<Card> getDeck();

    /**
     * @return unmodifiable ObservableList containing the discard pile
     */
    ObservableList<Card> getDiscardPile();

    /**
     * @return unmodifiable ObservableList containing the player's hand
     */
    ObservableList<Card> getPlayerHand();

    /**
     * @return unmodifiable ObservableList containing all the played cards
     */
    ObservableList<Card> getPlayedCards();

    /**
     * @return unmodifiable ObservableList containing the first 5 cards from the deck
     */
    ObservableList<Card> getChipCards();

    /**
     * @return unmodifiable ObservableList containing the cards in limbo
     */
    ObservableList<Card> getLimboCards();

    /**
     * @return unmodifiable ObservableList containing the active rockets
     */
    ObservableList<Card> getRockets();

    /**
     * @return number of Robot cards remaining in the deck
     */
    int getDeckRobotCount();

    /**
     * @return number of cards left in the deck
     */
    int getDeckSize();

    /**
     * @return the status of the game (lost, in progress or won)
     */
    GameStatus getStatus();

    /**
     * Resets Game parameters.
     */
    void reset();

    /**
     * Assuming that the player hand is empty, draws an entire hand of cards from the deck.
     */
    void drawHand();

    /**
     * @param card: a card from the player's hand
     */
    void playCard(final Card card);

    /**
     * @param card: a card from the player's hand
     */
    void discardCard(final Card card);

    /**
     * @param card: the chip to be discarded
     */
    void useChip(final CardColor chipColor);

    /**
     * Called to select a card revealed by the chip.
     * The first card will be discarded, while the others will be added to the deck
     */
    void selectChipCard(final Card card);

    /**
     * @param choice: what to do to handle the robot's attack
     * @param color: the color of the chip to be discarded or the rocket to be used; present only if necessary
     */
    void handleRobotAttack(final RobotAttackChoice choice, Optional<CardColor> color);

    /**
     * @param useChip: true to use the chip and store the rocket,
     * false to put the rocket back in the deck
     */
    void handleRocket(final boolean useChip);

}
