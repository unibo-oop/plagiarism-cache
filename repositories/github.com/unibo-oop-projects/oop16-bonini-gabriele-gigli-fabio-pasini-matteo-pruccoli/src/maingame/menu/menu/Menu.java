package maingame.menu.menu;

/**
 * Interfaccia di un menu generico.
 */
public interface Menu {

    /**
     * Mostra il menù dei comandi di gioco.
     */
    void showCommand();

    /**
     * Mostra il menù con le statistiche di gioco.
     */
    void showStats();

    /**
     * Aggiorna livello difficoltà mostrato nel menù.
     */
    void setDiff();

    /**
     * Seleziona una diversa opzione.
     * 
     * @param up
     *            true se voglio selezionare la voce sopra
     */
    void changeSelected(boolean up);

    /**
     * Abilita il listener per i comandi del menù.
     * 
     * @param enable
     *            Add a comment to this line true se li abilito
     */
    void enableCommand(boolean enable);
}
