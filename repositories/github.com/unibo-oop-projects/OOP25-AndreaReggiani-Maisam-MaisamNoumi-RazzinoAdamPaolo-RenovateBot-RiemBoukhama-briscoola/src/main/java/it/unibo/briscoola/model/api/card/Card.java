package it.unibo.briscoola.model.api.card;

import it.unibo.briscoola.model.api.attributes.CardSeed;
import it.unibo.briscoola.model.api.attributes.CardValue;

/**
 * Represents the interface of the generic card of the game.
 * 
 * @author Andrea Reggiani
 */
public interface Card {

    /**
     * Get the seed of the card.
     * 
     * @return the seed of the card
     */
    CardSeed getCardSeed();

    /**
     * Get the value of the card.
     * 
     * @return the value of the card
     */
    CardValue getCardValue();

    /**
     * Get the point of the card.
     * 
     * @return the points of the card
     */
    int getCardPoints();

    /**
     * Get the strenght of the card.
     * 
     * @return the Strength of the card
     */
    int getCardPower();
}
