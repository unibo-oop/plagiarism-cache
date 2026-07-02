package migglione.model.api;

import java.util.List;

import migglione.model.impl.Card;

/**
 * interface where the methods in common with the user and the CPU are described.
 */
public interface Player {
    /**
     * Method used for playing a specified card.
     * Needs the Integer representing the card in the Cards.java class,
     * then removes the card from the player's hand.
     * 
     * @param attr the attribute decided to determine who wins
     * @param playedCard the played card who's going to be removed from the hand
     * @return the value of card's attribute
     */
    int playCard(String attr, Card playedCard);

    /**
     * Used for knowing the cards in hand at the beginning for each round.
     * 
     * @return the List of Integer values representing
     *         the cards in Player's hand
     */
    List<Card> getHand();

    /**
     * Method to draw a card at the start of each round.
     * Only if player has < 3 cards in hand or the deck is not empty
     * 
     * @param drawnCard the card to put in hand
     */
    void drawCard(Card drawnCard);

    /**
     * The chosen attribute is stored internally to the player.
     * So that playing a card and choosing the attributes are 2 separate methods
     * 
     * @param attr the attribute to play the card with
     */
    void chooseAttr(String attr);

    /**
     * Method to understand what attribute a player is using in a round.
     * 
     * @return current selected attribute
     */
    String getAttr();

    /**
     * Method to get the points of the player.
     * 
     * @param pointsWon the cards to add to pile
     * @return the pile of points won
     */
    List<Card> getPile(List<Card> pointsWon);

    /**
     * A method to get the name of the player, used for the scoreboard.
     *
     * @return User's name
     */
    String getName();

    /**
     * Method to get the last played card by the player.
     * Useful for the pile of points won
     * 
     * @return the played card
     */
    Card getPlayedCard();
}
