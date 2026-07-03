package it.unibo.apice.oop.machikoro.model;

/**
 * Interfaccia molto generica per rappresentare tutte le carte in generale.
 * Possiede solamente i metodi in comune ad entrambi i tipi di carta.
 */
public interface Card {

    /**
     * Metodo per ottenere una copia della carta, utile quando ne si vuole
     * comprare una.
     * 
     * @return Copia della carta sulla quale viene invocato questo metodo.
     */
    Card getClone();

    /**
     * Metodo per ottenere il nome della carta.
     * 
     * @return Nome della carta.
     */
    String getName();

    /**
     * Metodo per ottenere il costo in monete della carta.
     * 
     * @return Costo della carta.
     */
    int getCost();

    /**
     * Metodo per ottenere la priorità di acquisto della carta. Utilizzato
     * sopratutto per l'intelligenza artificiale per decidere quale carta
     * acquistare.
     * 
     * @param turn
     *            Turno di gioco.
     * @param player
     *            Player di turno.
     * @return Priorità della carta corrente.
     */
    int getPriority(int turn, PlayerImpl player);
}
