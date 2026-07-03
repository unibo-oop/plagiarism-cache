package it.unibo.apice.oop.machikoro.model;

import java.util.List;

/**
 * Interfaccia per rappresentare un Player reale del gioco.
 */
public interface Player {

    /**
     * Metodo per fare comprare una carta al giocatore.
     * 
     * @param card
     *            Carta che il giocatore intende acquistare.
     * @throws NotEnoughMoneyException
     *             Eccezione generata quando il giocatore non possiede
     *             abbastanza monete per comprare la carta desiderata
     * @throws AlreadyBoughtCardException
     *             Eccezione generata quando si vuole comprare una carta
     *             obiettivo già acquistata in precedenza.
     */
    void buyCard(Card card) throws NotEnoughMoneyException, AlreadyBoughtCardException;

    /**
     * Metodo per far cedere al giocatore le proprie monete ad un altro. Se un
     * giocatore non possiede abbastanza monete, allora consegnerà quelle che
     * riesce.
     * 
     * @param player
     *            Giocatore che deve ricevere le monete
     * @param amount
     *            Ammontare che deve essere consegnato
     */
    void giveMoney(Player player, int amount);

    /**
     * Metodo per far ricevere al giocatore dei soldi, siano essi dalla "banca"
     * o da un altro giocatore.
     * 
     * @param amount
     *            Ammontare che deve essere ricevuto.
     */
    void receiveMoney(int amount);

    /**
     * Metodo per ottenere tutti gli effetti che devono essere attivati quando
     * esce il numero di dadi dato come argomento.
     * 
     * @param number
     *            Numero di dadi uscito dall'ultimo lancio.
     * @return Lista di tutti gli effetti che devono essere attivati.
     */
    List<Effect> getEffectsByNumber(int number);

    /**
     * Metodo per ottenere tutte le carte di un determinato tipo. Utile quando
     * si vogliono contare ad esempio per calcolare le rendite di un effetto.
     * 
     * @param type
     *            Tipo delle carte da ottenere.
     * @return Set delle carte del tipo scelto. Potrebbe essere null, se il
     *         giocatore non ha ancora comprato nessuna carta.
     */
    List<Card> getBoardCards(CardType type);

    /**
     * Metodo per ottenere tutte le carte non obiettivo appartenenti al
     * giocatore.
     * 
     * @return Lista di carte obiettivo
     */
    List<Card> getBoardCards();

    /**
     * Metodo per ottenere tutte le carte obiettivo.
     * 
     * @return Set con tutte le carte obiettivo del giocatore, sia abilitate che
     *         non.
     */
    List<Card> getAimCards();

    /**
     * Metodo per ottenere le monete possedute al momento dal giocatore.
     * 
     * @return Monete del giocatore.
     */
    int getMoney();

    /**
     * Metodo per capire se il giocatore è controllato dal computer.
     * 
     * @return True se il giocatore è controllato dal computer, false
     *         altrimenti.
     */
    boolean isIAPlayer();

    /**
     * Metodo per capire se il giocatore ha vinto oppure no. Si raggiunge la
     * vittoria quando si compra l'ultima carta obiettivo.
     * 
     * @return True se il giocatore ha vinto, false altrimenti.
     */
    boolean checkWin();

    /**
     * Metodo per ottenere il nome del Player.
     * 
     * @return Nome del Player
     */
    String getName();
}
