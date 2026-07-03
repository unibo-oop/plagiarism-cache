package maingame.menu.menu;

/**
 * enum che contiene i possibili comandi che un menù generico può gestire.
 */
public enum Option {

    /**
     * Avvia o riprende il gioco.
     */
    START("Start"), 
    /**
     * Mostra i comandi di gioco.
     */
    COMMAND("Commands"),
    /**
     * Imposta difficoltà.
     */
    DIFF("Difficulty"),
    /**
     * Mostra statistiche.
     */
    STATS("Statistics"),
    /**
     * Avvia editor.
     */
    EDITOR("Editor"),
    /**
     * Ricomincia il gioco.
     */
    RESTART("Restart");
    private final String opzione;

    /**
     * @param text
     *            Stringa che rappresenta il comando
     */
    Option(final String text) {
        this.opzione = text;
    }

    @Override
    public String toString() {
        return this.opzione;
    }
}
