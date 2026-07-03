package it.unibo.apice.oop.machikoro.model;

/**
 * Interfaccia per rappresentare gli effetti che ogni carta possiede.
 */
public interface Effect extends Cloneable {

    /**
     * Metodo per ottenere una copia esatta dell'oggetto d'istanza.
     * 
     * @return Oggetto copia dell'effetto corrente.
     * @throws CloneNotSupportedException
     *             Eccezione generata quando non è implementata l'interfaccia
     *             cloneable nella classe padre.
     */
    Object clone() throws CloneNotSupportedException;

    /**
     * Metodo per ottenere il colore dell'effetto per sapere anche quando
     * attivarlo.
     * 
     * @return Colore dell'effetto.
     */
    EffectColor getColor();

    /**
     * Metodo per ottenere la rendita per istanza che si ottiene quando si
     * attiva l'effetto.
     * 
     * @return Rendita.
     */
    int getRevenue();

    /**
     * Metodo per ottenere il tipo di carta moltiplicatore della rendita.
     * 
     * @return Tipo di carta da gioco.
     */
    CardType getForInstanceOf();

    /**
     * Metodo per ottenere la rendita totale che si ottiene quando si attiva
     * l'effetto.
     * 
     * @param player
     *            Giocatore sul quale dovrebbe aver effetto.
     * @return Rendita totale.
     */
    int getTotalRevenue(Player player);

    /**
     * Metodo per risolvere l'effetto sul Player passato come parametro per
     * carte Verdi o Blu.
     * 
     * @param receiver
     *            Giocatore che riceverà le monete derivanti dall'effetto.
     * @throws EffectColorNotMatch
     *             Eccezione generata quando si vuole eseguire questo metodo su
     *             una carta rossa.
     */
    void resolveEffect(Player receiver) throws EffectColorNotMatch;

    /**
     * Metodo per risolvere l'effetto di una carta Rossa per il Player passato
     * come Receiver dal Player passato come Giver.
     * 
     * @param receiver
     *            Player proprietario della carta Rossa che deve ricevere i
     *            soldi
     * @param giver
     *            Player che ha lanciato i dadi in questo turno che deve cedere
     *            i soldi
     * @throws EffectColorNotMatch
     *             Eccezione generata quando si vuole invocare questo metodo su
     *             carte non di colore rosso
     */
    void resolveEffect(Player receiver, Player giver) throws EffectColorNotMatch;

}