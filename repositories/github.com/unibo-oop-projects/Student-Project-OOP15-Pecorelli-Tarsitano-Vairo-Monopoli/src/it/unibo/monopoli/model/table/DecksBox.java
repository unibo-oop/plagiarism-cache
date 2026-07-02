package it.unibo.monopoli.model.table;

import it.unibo.monopoli.model.cards.Deck;

/**
 * This class represents an implementation of {@link Box}. It represents
 * {@link Decks}'s {@link Box}es of Monopoly, the {@link Box}es on which you
 * have to draw from a {@link Deck}.
 *
 */
public class DecksBox implements Box {

    private final String name;
    private final int id;

    /**
     * Constructs an implementation of {@link DecksBox}. It needs a name, an ID
     * and a {@link Deck} from which you have to draw.
     * 
     * @param name
     *            - of this {@link Box}
     * @param id
     *            - of this {@link Box}
     */
    public DecksBox(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getID() {
        return this.id;
    }

}
