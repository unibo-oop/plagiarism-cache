package it.unibo.memory.model;

public class Game {

    // I campi — lo "stato" della partita
    private final int totalPairs; // quante coppie ci sono in totale (non cambia mai)
    private int moves;            // quante mosse hai fatto
    private int matchedPairs;     // quante coppie hai trovato
    private boolean gameOver;     // la partita è finita?


/**
     * Costruttore di default (Lab 02 - Fase 1.2).
     * Avvia una partita standard da 8 coppie.
     */
    public Game() {
        this(8); // Uso di this per evitare stato inconsistente (Fase 1.6)
    }


    // Inizia - nasce una nuova partita
    public Game(final int totalPairs) {
        this.totalPairs = totalPairs; // salviamo il totale delle coppie
        this.moves = 0;
        this.matchedPairs = 0;
        this.gameOver = false;
    }

    // Aggiunge una mossa
    public void addMove() {
        this.moves++;
    }

    // Segna una coppia trovata e controlla se la partita è finita
    public void addMatchedPair() {
        this.matchedPairs++;                         //trovo una coppia in più
        if (this.matchedPairs == this.totalPairs) {  // confronto le coppie trovate, se sono uguali al totale delle coppie, le ho trovate tutte?
            this.gameOver = true;                    // si!, fine partita
            
        }
    }

    // Controlla se la partita è finita
    public boolean isGameOver() {
        return this.gameOver;
    }

    // Dimmi quante mosse ho fatto
    public int getMoves() {
        return this.moves;
    }

    // Dimmi quante coppie ho trovato
    public int getMatchedPairs() {
        return this.matchedPairs;
    }
}