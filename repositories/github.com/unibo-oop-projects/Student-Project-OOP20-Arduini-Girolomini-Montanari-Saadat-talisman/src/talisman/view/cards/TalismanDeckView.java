package talisman.view.cards;

import java.util.List;

import talisman.model.cards.Card;
/**
 * A class with a method that creates a view of a list of cards.
 * @author Abtin Saadat
 *
 */
public interface TalismanDeckView {
    static TalismanDeckView create(final List<Card> deck) {
        return new TalismanDeckViewImpl(deck);
    }
}
