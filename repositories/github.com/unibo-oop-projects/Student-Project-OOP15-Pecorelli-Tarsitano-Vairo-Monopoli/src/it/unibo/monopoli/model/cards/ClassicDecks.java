package it.unibo.monopoli.model.cards;

/**
 * This is enum for classic game's {@link Deck}s.
 *
 */
public enum ClassicDecks {

    /**
     * Community chest's {@link Deck}.
     */
    COMMUNITY_CHEST("PROBABILITÀ"),
    /**
     * Canche's {@link Deck}.
     */
    CHANCE("IMPREVISTI");

    private final String name;

    ClassicDecks(final String name) {
        this.name = name;
    }

    /**
     * Returns the {@link Deck}'s name.
     * 
     * @return {@link Deck}'s name
     */
    public String getName() {
        return this.name;
    }
}
