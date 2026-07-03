package it.unibo.memory.model;

// Rappresenta i possibili stati della partita
public enum GameState {
    WAITING,    // aspetto il primo click
    FIRST_CARD, // ho girato la prima carta
    GAME_OVER   // partita finita
}