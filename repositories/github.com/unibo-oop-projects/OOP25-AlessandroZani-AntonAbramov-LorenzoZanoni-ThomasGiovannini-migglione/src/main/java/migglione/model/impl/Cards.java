package migglione.model.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class Cards create 24 cards and method to get it.
 * 
 * @see CardId where all 24 cards was defined 
 *             whit their atribute and name
 */
public class Cards {
    private final Map<Integer, Card> card;

    /**
     * Constructor of the cards.
     */
    public Cards() {
        card = CardId.buildCardsMap();
    }

    /**
     * Getter for define cards.
     * 
     * @return cards
     */
    public Map<Integer, Card> getCards() {
        return Collections.unmodifiableMap(card);
    }

    /**
     * Getter for the path of the sprites of the cards.
     * 
     * @return the path of the image (see Gallery for use)
     */
    public List<String> getCardsPaths() {
        return card.values().stream().map(n -> "/images/cards/" + n.getName() + ".png").collect(Collectors.toList());
    }
}
