package maingame.menu.model;

import maingame.level.Difficulty;
import maingame.menu.menu.Option;

/**
 * Interfaccia del model per il menu.
 */
public interface ModelMenu {

    /**
     * @return l'indice della voce del menù corrente
     */
    int getIndex();

    /**
     * setta il valore dell'indice.
     * 
     * @param value
     *            valore indice
     */
    void setIndex(int value);

    /**
     * @return true se il menu sta mostrando i comandi di gioco.
     */
    boolean isShowCommand();

    /**
     * setta lo stato del menu su mostra comandi.
     * 
     * @param show
     *            mostra
     */
    void setShowCommand(boolean show);

    /**
     * @return true se il menu sta mostrando le statistiche di gioco.
     */
    boolean isShowStats();

    /**
     * setta lo stato del menu su mostra statistiche.
     * 
     * @param show
     *            mostra
     */
    void setShowStats(boolean show);

    /**
     * Ritorna la difficoltà.
     * 
     * @return difficoltà
     */
    Difficulty getDifficulty();

    /**
     * Setta la difficoltà.
     * 
     * @param difficulty
     *            difficoltà
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Ritorna il comando del menù alla posizione index.
     * 
     * @param index
     *            poszione comando
     * @return comando richiesto
     */
    Option getOption(int index);

    /**
     * Aggiunge un comando alla lista di comandi presenti nel menu.
     * 
     * @param newOption
     *            comando da aggiungere
     */
    void addOption(Option newOption);

    /**
     * @return numero di comandi all'interno del menu
     */
    int getNumOption();
}