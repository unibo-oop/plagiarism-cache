package talisman.controller.cards;

import talisman.model.cards.Card;
import talisman.view.cards.TalismanCardView;
/**
 * A controller for a single card.
 * @author Abtin Saadat
 *
 */
public interface TalismanCardController {
    /**
     * Tells the View to create the card.
     * @param card
     * @return Returns the View
     */
    static TalismanCardView createView(Card card) {
        return TalismanCardControllerImpl.createView(card);
    }
}
