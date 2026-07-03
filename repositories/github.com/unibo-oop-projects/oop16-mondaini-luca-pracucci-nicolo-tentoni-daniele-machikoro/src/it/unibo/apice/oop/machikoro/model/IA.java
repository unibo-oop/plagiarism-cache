package it.unibo.apice.oop.machikoro.model;

import java.util.List;

/**
 * Interfaccia Singleton per gestire l'intelligenza artifiaciale del programma.
 * Una sola gestirà le priorità con cui comprare le carte da parte del giocatore
 * gestito dal computer, dato che esso è l'unico aspetto decisionale all'interno
 * del gioco.
 *
 */
public interface IA {

    /**
     * Metodo che restituisce la carta con priorità d'acquisto maggiore tra
     * quelle da comprare. Questo metodo guarda solamente alle carte obiettivo
     * del giocatore e non alle carte in gioco. Per le carte in gioco usare
     * l'altro metodo getHighestPriorityBoardCard.
     * 
     * @param turn
     *            Turno di gioco.
     * @param player
     *            Istanza del player di turno.
     * @return Carta con la massima priorità tra le carte obiettivo.
     */
    Card getHighestPriorityAimCard(int turn, PlayerImpl player);

    /**
     * Metodo che restituisce la carta con la priorità d'acquisto maggiore tra
     * quelle da comprare. Questo metodo guarda solamente alle carte in gioco e
     * non le carte obiettivo. Per le carte obiettivo usare l'altro metodo
     * getHighestPriorityAimCard.
     * 
     * @param cards
     *            Lista delle carte che è possibile comprare.
     * @param turn
     *            Turno di gioco.
     * @param player
     *            Istanza del player di turno.
     * @return Carta con la massima priorità tra quelle in gioco.
     */
    Card getHighestPriorityBoardCard(List<Card> cards, int turn, PlayerImpl player);

    /**
     * Metodo che restituisce la carta con la priorità d'acquisto maggiore tra
     * quelle da comprare. Questo metodo guarda tutte le carte che potrebbe
     * comprare un giocatore, sia le AimCArd che le BoardCard.
     * 
     * @param cards
     *            Lista delle carte in gioco che è possibile comprare.
     * @param turn
     *            Turno di gioco corrente.
     * @param player
     *            Istanza del player di turno che deve comprare le carte.
     * @return Carta con la massima priorità tra tutte quelle acquistabili. Se
     *         il player non possiede monete allora ritornerà null.
     */
    Card getHighestPriorityCard(List<Card> cards, int turn, PlayerImpl player);

    /**
     * Metodo per capire se è più conveniente tirare due o un dado.
     * 
     * @param player
     *            Giocatore sottoposto al test
     * @return Numero di dadi da tirare
     */
    int rollingDice(Player player);

}