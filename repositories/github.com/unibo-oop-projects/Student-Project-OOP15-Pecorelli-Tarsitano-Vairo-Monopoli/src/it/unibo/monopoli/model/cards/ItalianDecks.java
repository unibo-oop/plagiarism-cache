package it.unibo.monopoli.model.cards;

/**
 * This is enum for italian game's {@link Deck}s.
 *
 */
public enum ItalianDecks {

    /**
     * Probabilità's {@link Deck}.
     */
    PROBABILITÀ("PROBABILITÀ"),

    /**
     * Imprevisti's {@link Deck}.
     */
    IMPREVISTI("IMPREVISTI");

    private final String name;

    /**
     * Constructs an instance of {@link Deck} named {@link CommunityChest}.
     */
   ItalianDecks(final String name) {
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
