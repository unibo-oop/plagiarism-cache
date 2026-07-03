package it.unibo.apice.oop.machikoro.model;

/**
 * Eccezione generata quando si cerca di acquistare una carta che costa più
 * delle monete che si hanno a disposizione.
 */
public class NotEnoughMoneyException extends Exception {

    private final Card card;

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore che permette di salvarsi la carta che ha generato
     * l'eccezione.
     * 
     * @param card
     *            Carta che ha generato l'eccezione.
     */
    public NotEnoughMoneyException(final Card card) {
        super("Non si ha avuto abbastanza monete per comprare una carta.");
        this.card = card.getClone();
    }

    /**
     * Metodo per ottenere la carta che ha generato l'eccezione.
     * 
     * @return Carta che ha generato l'eccezione.
     */
    public Card getCard() {
        return this.card;
    }
}
