package casim.model;

/**
 * Automata enum.
 * Used to populate the menu view.
 */
public enum Automata {
    /**
     * Bryan's Brain automaton.
     */
    BRYANS_BRAIN("Bryan's Brain"),

    /**
     * Rule 110 automaton.
     */
    RULE110("Rule 110"),

    /**
     * Predators and preys automaton.
     */
    WATOR("Predators and preys (Wator)"),

    /**
     * Langton's ant automaton.
     */
    LANGTONS_ANT("Langton's ant"),

    /**
     * CoDi automaton.
     */
    CODI("CoDi"),

    /**
     * Conway's game of life automaton.
     */
    GAME_OF_LIFE("Conway's game of life");

    private final String name;

    /**
     * Costructor of the class.
     * 
     * @param name of the automaton
     */
    Automata(final String name) {
        this.name = name;
    }

    /**
     * Get the name of the automaton.
     * 
     * @return the name of the automaton.
     */
    public String getName() {
        return this.name;
    }
}
