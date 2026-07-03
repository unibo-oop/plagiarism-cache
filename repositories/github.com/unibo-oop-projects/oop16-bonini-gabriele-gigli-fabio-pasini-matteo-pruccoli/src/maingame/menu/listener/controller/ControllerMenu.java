package maingame.menu.listener.controller;

/**
 * Interfaccia di un controller per il menu.
 */
public interface ControllerMenu {
    /**
     * Gestisce l'evento escapr all'interno del menù.
     */
    void escape();

    /**
     * Setta la diffcoltà del livello.
     */
    void setDiff();

    /**
     * Cambia il comando selezionato.
     * 
     * @param up
     *            true se voglio passare a quello sopra, false se voglio passare
     *            a quello sotto
     */
    void myChangeSelected(boolean up);

    /**
     * Gestisce l'evento enter all'interno del menù.
     */
    void enter();
}
