package migglione.model.impl;

import java.util.ArrayList;
import java.util.List;

/**
 * In the game all player have their pile,
 * the winer of the round get all cards played in the pile,
 * at the and of the game all player pile was compare,
 * ho have more card in the pile win and the cards become points.
 */
public class PointsPile {

private final List<Card> pile;

    /**
     * Constructor for create empty pile.
     */
    public PointsPile() {
        this.pile = new ArrayList<>();
    }

    /**
     * Method for add cards in the pile.
     * 
     * @param card you want to go in the pile
     */
    public void addPile(final Card card) {
        pile.add(card);
    }

    /**
     * Getter for see if pile is empty.
     * 
     * @return if the pile is empty or not
     */
    public boolean isPileEmpty() {
        return this.pile.isEmpty();
    }

    /**
     * Method for get the unmodifiable player pile.
     * 
     * @return copy of the pile
     */
    public List<Card> getPile() {
        return List.copyOf(this.pile);
    }

    /**
     * Method for see cards in your pile.
     * 
     * @return firs card in your pile/greviard 
     */
    public Card scry() {
        if (isPileEmpty()) {
            return null;
        }
        return pile.get(pile.size() - 1);
    }
}
