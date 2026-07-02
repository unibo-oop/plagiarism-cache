package migglione.model.api;

import java.util.List;
import migglione.model.impl.Card;

/**
 * Simple interface in order to design the deck.
 * 
 * <p>
 * It allows to shuffle it so that there is no predermined
 * order, while also being able to return the list
 * containing the indexes of the cards. To each index is
 * associated a card as it can be seen in Cards.java
 */
public interface Deck {
    /**
     * Method used to shuffle the deck.
     * 
     * <p>
     * While it can be different by adopting different implementation,
     * in our case we will use an implementation that lets us
     * shuffle it randomly, without any constraints
     */
    void shuffle();

    /**
     * Used to return the list full of the indexes of the cards.
     * 
     * @return the list of cards, since the indexes
     *         are the keys used in Cards.java
     */
    List<Card> getDeck();
}
