package it.unibo.apice.oop.machikoro.model;

/**
 * Eccezione generata quando si cerca di comprare una carta già acquistata.
 */
public class AlreadyBoughtCardException extends Exception {

    private final Card card;

    private static final long serialVersionUID = 1L;

    /**
     * Costruttore dell'eccezione, permette di ricordarsi della carta che ha
     * sollevato l'eccezione.
     * 
     * @param card
     *            Carta che ha sollevato l'eccezione.
     */
    public AlreadyBoughtCardException(final Card card) {
        super("Stai cercando di acquistare una carta che già possiedi.");
        this.card = (Card) card.getClone();
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
