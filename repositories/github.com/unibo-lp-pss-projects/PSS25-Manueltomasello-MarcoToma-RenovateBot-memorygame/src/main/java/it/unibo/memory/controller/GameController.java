package it.unibo.memory.controller;

import javax.swing.Timer;
import it.unibo.memory.model.Board;
import it.unibo.memory.model.Card;
import it.unibo.memory.model.Game;
import it.unibo.memory.view.StatoPanel;

public class GameController {

    private final Board board;
    private final Game game;
    private final Runnable onGameOver;
    private final Runnable onBoardChanged;
    private Card firstCard;
    private boolean waiting;
    private StatoPanel statoPanel; // Riferimento al componente di visualizzazione delle statistiche

    public GameController(final Board board, final Game game, final Runnable onBoardChanged, final Runnable onGameOver) {
        this.board = board;
        this.game = game;
        this.onBoardChanged = onBoardChanged;
        this.onGameOver = onGameOver;
        this.firstCard = null;
        this.waiting = false;
    }

    /**
     * Collega lo StatoPanel al controller per aggiornare mosse e punteggio.
     */
    public void setStatoPanel(StatoPanel panel) {
        this.statoPanel = panel;
    }

    public void onCardClicked(final int position) {
        if (waiting) return;

        Card clicked = board.getCard(position);
        if (clicked.isFaceUp() || clicked.isMatched()) return;

        clicked.flip();

        if (firstCard == null) {
            // PRIMA CARTA GIRATA
            firstCard = clicked;
            onBoardChanged.run();
        } else {
            // SECONDA CARTA GIRATA
            game.addMove(); // Incrementa mosse nel modello
            
            // Aggiorno la logica di visualizzazione dello stato del gioco
            aggiornaInterfacciaManu();

            if (clicked.equals(firstCard)) {
                // COPPIA TROVATA
                clicked.setMatched(true);
                firstCard.setMatched(true);
                game.addMatchedPair(); // Incrementa coppie nel modello
                
                
                aggiornaInterfacciaManu();
                
                resetTurn();
                onBoardChanged.run();

                if (game.getMatchedPairs() == board.getDifficulty().totalPairs()) {
                    if (this.statoPanel != null) {
                        this.statoPanel.setStato("VITTORIA!");

                    }
                    onGameOver.run();
                    
                }
            } else {
                // COPPIE DIVERSE
                onBoardChanged.run();
                waiting = true;
                //Imposto un tempo per evitare che l'utente clicca più carte che siano fuori dalla coppia
                Timer timer = new Timer(500, e -> {
                    clicked.flip();
                    firstCard.flip();
                    resetTurn();
                    onBoardChanged.run();
                });
                timer.setRepeats(false);
                timer.start();
            }
        }
    }

    /**
     * Metodo di supporto per inviare i dati corretti allo StatoPanel
     */
    private void aggiornaInterfacciaManu() {
        if (this.statoPanel != null) {
            this.statoPanel.setMosse(game.getMoves());
            this.statoPanel.setCoppie(game.getMatchedPairs(), board.getDifficulty().totalPairs());
        }
    }

    private void resetTurn() {
        this.firstCard = null;
        this.waiting = false;
    }

    public boolean isGameOver() { return game.isGameOver(); }
    public int getMoves() { return game.getMoves(); }
    public int getMatchedPairs() { return game.getMatchedPairs(); }

    
}
