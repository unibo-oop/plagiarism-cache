package talisman.controller.cards;

import talisman.model.cards.Card;
import talisman.view.cards.TalismanDeckView;
/**
 * A deck controller used to draw, shuffle, and create the deck view.
 * @author Abtin Saadat
 *
 */
public interface TalismanDeckController {
    Card draw();
    TalismanDeckController shuffle();
    TalismanDeckView createView();
}
