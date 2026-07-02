package it.unibo.shoot.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;
import it.unibo.shoot.model.*;

/**
 * Gestisce l'input hardware da tastiera per il controllo direzionale del giocatore.
 * Agisce come Controller nel pattern MVC: intercetta gli eventi nativi AWT e ne delega 
 * la traduzione vettoriale al Modello.
 * Utilizza una strategia a Polling Bufferizzato tramite HashSet per eludere l'input lag 
 * e il delay di ripetizione del sistema operativo, garantendo fluidità omnidirezionale.
 */
public class PlayerController implements KeyListener {

    private PlayerModel model;
    private Game game; 
    
    /** Buffer di stato con tempo di accesso e inserimento garantito in O(1). */
    private Set<Integer> pressedKeys = new HashSet<>();

    public PlayerController(PlayerModel model, Game game) {
        this.model = model;
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Bypassato intenzionalmente: l'hardware polling richiede l'uso esclusivo dei KeyCode grezzi.
    }

    /**
     * Traduce i tasti attualmente attivi nel buffer in un vettore di direzione 
     * risultante (dx, dy) e lo inietta nel PlayerModel.
     * * VINCOLO DI STATO: L'esecuzione è subordinata allo stato globale del motore. 
     * Se la FSM globale si trova in STATE.GAME_OVER, il polling vettoriale viene abortito
     * per prevenire manipolazioni fisiche (movimenti zombie) ad entità "morte".
     */
    public void update() {
        if (game.getGameState() == STATE.GAME_OVER) {
            return;
        }
    
        float dx = 0;
        float dy = 0;

        if (pressedKeys.contains(KeyEvent.VK_W)) dy--;
        if (pressedKeys.contains(KeyEvent.VK_S)) dy++;
        if (pressedKeys.contains(KeyEvent.VK_A)) dx--;
        if (pressedKeys.contains(KeyEvent.VK_D)) dx++;
        
        model.setVelocity(dx, dy);
    }
}
