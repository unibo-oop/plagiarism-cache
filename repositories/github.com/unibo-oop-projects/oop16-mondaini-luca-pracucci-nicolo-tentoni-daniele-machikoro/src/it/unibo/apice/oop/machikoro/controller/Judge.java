package it.unibo.apice.oop.machikoro.controller;

import java.util.List;

import it.unibo.apice.oop.machikoro.model.AlreadyBoughtCardException;
import it.unibo.apice.oop.machikoro.model.Card;
import it.unibo.apice.oop.machikoro.model.NotEnoughMoneyException;
import it.unibo.apice.oop.machikoro.model.Player;

/**
 * Interfaccia per la realizzazione di un giudice di gioco per giocare a
 * Machi-Koro. Questa interfaccia è il controller del gioco.
 */
public interface Judge {
    /**
     * Metodo che restituisce l'indice del PLayer del turno corrente.
     * 
     * @return indice intero.
     */
    int getTurn();

    /**
     * Metodo per far lanciare uno o due dadi al giocatore.
     * 
     * @param n
     *            Numero di dadi che il giocatore vuole lanciare.
     * @return numero sorteggiato.
     * @throws NotUnlockSecondDiceException
     *             Eccezione generata quando il giocatore vuole lanciare due
     *             dadi ma non possiede la carta che gli permette di farlo.
     */
    int rollDice(int n);

    /**
     * Metodo che indica se il giocatore di turno può ri-tirare i dadi.
     * 
     * @return true se il player di turno ha la TorreRadio, false altrimenti.
     */
    boolean canReRoll();

    /**
     * Metodo per aggiungere un giocatore alla partita.
     * 
     * @param name
     *            Nome del giocatore.
     * @param isBot
     *            indica se il giocatore è un bot o no.
     */
    void addPlayer(String name, boolean isBot);

    /**
     * Metodo per ricevere la lista dei giocatori.
     * 
     * @return players ArrayList di Player
     */
    List<Player> getPlayers();

    /**
     * Metodo per ricevere la lista delle BoardCard disponibili.
     * 
     * @return bcards ArrayList di Card
     */
    List<Card> getBoardCards();

    /**
     * Metodo che indica, data una carta, quante volte possiede quella carta un
     * giocatore.
     * 
     * @param c
     *            Card da verificare.
     * @param p
     *            Player che possiede quella Card.
     * @return numero di volte che p cossiede c.
     */
    int sameCard(Card c, Player p);

    /**
     * Metodo che controlla e attiva gli effetti delle carte con un determinato
     * livello.
     * 
     * @param level
     *            livello estratto dai dadi.
     */
    void checkCards(int level);

    /**
     * Metodo che passa al turno sucessivo.
     */
    void endTurn();

    /**
     * Metodo per fare comprare una carta al giocatore.
     * 
     * @param card
     *            Carta che il giocatore intende acquistare.
     * @return true se il giocatore ha comprato tutte le AimCards e quindi ha
     *         vinto, sennò false
     * @throws NotEnoughMoneyException
     *             eccezione scatenata quando non si ha bbastanza soldi per
     *             comprare la carta.
     * @throws HaveMaxSameCardException
     *             eccezione scatenata quando un player possiede già il numero
     *             massimo di copie di una determinata carta.
     * @throws AlreadyBoughtCardException
     *             eccezione scatenata se un player prova a comprare una AimCard
     *             già comprata.
     */
    boolean wantBuy(Card card) throws NotEnoughMoneyException, HaveMaxSameCardException, AlreadyBoughtCardException;

    /**
     * Metodo che sceglie la carta che comprerà un bot.
     * 
     * @return Card scelta con l'IA.
     */
    Card iaBuyCard();

    /**
     * Metodo per salvare una partita.
     * 
     * @param skin
     *            indice della skin.
     */
    void saveMatch(int skin);

    /**
     * Metodo per caricare una partita.
     */
    void loadMatch();

    /**
     * Metodo che serve alla GUI per caricare da file la skin.
     * 
     * @return int indice della skin.
     */
    int loadSkin();

    /**
     * Metodo che verifica l'esistenza di un salvataggio.
     * 
     * @return true se esiste il file save.txt, sennò false.
     */
    boolean checkExistMatch();

    /**
     * Metodo che verifica se un Player può tirare due dadi.
     * 
     * @return true il Player di turno possiede la Stazione Ferroviaria, sennò
     *         false.
     */
    boolean checkDoubleDice();
}
