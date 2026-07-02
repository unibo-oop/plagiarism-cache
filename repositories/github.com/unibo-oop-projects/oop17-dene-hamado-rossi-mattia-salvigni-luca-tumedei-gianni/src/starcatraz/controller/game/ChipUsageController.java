package starcatraz.controller.game;

import starcatraz.view.cards.CardView;

/**
 * Controller for handling the usage of chips inside of the game.
 */
public interface ChipUsageController {

    /**
     * Add a card to the view.
     * @param cardView
     */
    void addCard(CardView cardView);
}
