package talisman.model.character;

import talisman.model.cards.Card;
import talisman.model.cards.CardImpl;

import java.util.List;

/**
 * Interface model of the inventory of a character
 *
 * @author Enrico Maria Montanari
 */

public interface InventoryModel {

    /**
     * Add a card to the inventory
     *
     * @param card to add
     */
    void addCard(CardImpl card);

    /**
     * Remove a card from the inventory
     *
     * @param index of the card in the inventory
     */
    void removeCard(int index);

    /**
     * Remove a card from the inventory
     *
     * @param card instance of the card to remove
     */
    void removeCard(CardImpl card);

    /**
     * List all cards in the inventory
     *
     * @return an array of the instances in the inventory
     */
    List<Card> listCards();

    /**
     * Check if players has the "Mule" card in the inventory
     *
     * @return true if the card is in the inventory
     */
    boolean hasMule();

    /**
     * List how many cards are in the inventory
     *
     * @return amount of cards
     */
    int size();
}
