package it.unibo.apice.oop.machikoro.model;

/**
 * Eccezione generata quando si vuole usare un metodo di risoluzione effetti con
 * un effetto inappropriato.
 */
public class EffectColorNotMatch extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Costruttore dell'eccezione per inserire un messaggio personalizzato alla
     * generazione dell'eccezione.
     * 
     * @param message
     *            Messaggio da comunicare.
     */
    public EffectColorNotMatch(final String message) {
        super(message);
    }
}
