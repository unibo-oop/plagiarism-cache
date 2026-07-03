package it.unibo.memory.model;

import java.util.Objects;

/*Questa classe rappresenta la carta, unità principale del gioco.*/
public class Card {

    private final int symbol;
    private boolean faceUp;
    private boolean matched;

    /**
     * Costruttore a zero argomenti (Richiesto da Lab 02 - Fase 1.2).
     * Inizializza con un simbolo di default usando 'this'.
     */
    public Card() {
        this(0); // Richiama il costruttore principale
    }

    /**
     * Creo una nuova carta con il simbolo dato.
     * La carta inizia coperta e non abbinata.
     */
    public Card(final int symbol) {
        this.symbol = symbol;
        this.faceUp = false;
        this.matched = false;
    }

    public int getSymbol() {
        return symbol;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public boolean isMatched() {
        return matched;
    }

    public void flip() {
        this.faceUp = !this.faceUp;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
        // Se la carta fa parte di una coppia trovata, deve rimanere a faccia in su
        if (matched) {
            this.faceUp = true;
        }
    }

    // --- LOGICA DI CONFRONTO (come da richiesta) ---
    
    @Override
    public boolean equals(Object obj) {
        // 1. Se sto confrontando la carta esattamente con se stessa
        if (this == obj) return true;
        
        // 2. Se l'altro valore è nullo\vuoto non è una Carta
        if (obj == null || getClass() != obj.getClass()) return false;
        
        // 3. Trasformo l'oggetto in "Card" e guardo se il simbolo è lo stesso
        Card otherCard = (Card) obj;
        return this.symbol == otherCard.symbol;
    }

    // Regola d'oro di Java: se riscrivi equals, riscrivi anche hashCode
    @Override
    public int hashCode() {
        return Objects.hash(symbol);
    }
}