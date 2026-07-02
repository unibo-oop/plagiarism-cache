package it.unibo.shoot.model;

import java.awt.Graphics;
import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import it.unibo.shoot.view.PlayerView;
import it.unibo.shoot.controller.PlayerController;
import it.unibo.shoot.loader.SpriteSheet;
import it.unibo.shoot.GameObjects.GameObject;
import it.unibo.shoot.audio.Sound;

/**
 * Entità principale del giocatore e nodo di interfaccia con il Game Engine.
 * Funge da aggregatore (Facade Pattern) per il sistema MVC interno, incapsulando 
 * Model, View e Controller, e proiettandoli come un singolo GameObject coerente 
 * all'interno dell'Handler globale.
 */
public class Player extends GameObject {

    private PlayerModel model;
    private PlayerView view;
    private PlayerController controller;
    private Handler handler;
    Game game;

    /**
     * Costruisce il giocatore e innesca le dipendenze del suo ecosistema MVC interno.
     * * @param x Coordinata logica iniziale X.
     * @param y Coordinata logica iniziale Y.
     * @param id Identificatore per il sistema di collisione.
     * @param canvas Contesto di rendering per la registrazione del listener hardware.
     * @param ss Asset grafico (SpriteSheet) fornito dal loader.
     * @param handler Riferimento al gestore globale per interrogare le entità vicine.
     * @param game Riferimento al motore per innescare transizioni di stato globali.
     */
    public Player(int x, int y, ID id, Canvas canvas, SpriteSheet ss, Handler handler, Game game) {
        super(x, y, id, ss);
        this.handler = handler;
        
        // Inizializzazione isolata delle componenti MVC
        this.model = new PlayerModel(x, y, 5.0, 100); 
        this.view = new PlayerView(model, ss); 
        this.controller = new PlayerController(model, game);
        this.game = game;

        // Registrazione hardware diretta sul canvas
        canvas.addKeyListener(controller);
    }

    /**
     * Sincronizza lo stato logico e fisico ad ogni iterazione del Game Loop (60 TPS).
     * VINCOLO DI STATO: Agisce come barriera FSM. Se il PlayerModel notifica la morte 
     * dell'entità, il metodo forza una transizione globale in STATE.GAME_OVER e 
     * interrompe l'esecuzione impedendo ulteriori calcoli vettoriali.
     */
    @Override
    public void tick() {
        if (model.isDead()) {
            game.setGameState(STATE.GAME_OVER);
            game.getSound().play(Sound.SoundType.GAME_OVER);
            return; 
        }
        
        controller.update(); 
        model.updatePosition();
        model.updateAnimation(); 
        
        this.x = (int) model.getX();
        this.y = (int) model.getY();
        this.velX = model.getVelX();
        this.velY = model.getVelY();

        collision();
    }

    /**
     * Risolve le intersezioni fisiche tramite AABB (Axis-Aligned Bounding Box).
     * COMPORTAMENTI SPECIFICI:
     * - Ostacoli Solidi (Block): Applica un rollback vettoriale per negare la compenetrazione.
     * - Risorse (Crate): Applica la rimozione sicura (List Shift Compensation) decrementando
     * l'indice di iterazione (i--) per prevenire il salto di entità adiacenti.
     * - Ostili (Enemy): Delega il calcolo dei danni al Modello, affidandosi al suo cooldown.
     */
    public void collision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if (tempObject.getId() == ID.Block) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    // Rollback del vettore per impedire l'attraversamento
                    this.x -= velX;
                    this.y -= velY;

                    model.setX(this.x);
                    model.setY(this.y);
                    model.setVelocity(0, 0);
                }
            }

            if (tempObject.getId() == ID.Crate) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    game.ammo += 50;
                    handler.removeObject(tempObject);
                    i--; // Compensazione dinamica per lo shift della lista
                }
            }

            if (tempObject.getId() == ID.Enemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    model.takeDamage(1); 
                }
            }
        }
    }

  
    // METODI DI DELEGA MVC
    

    public void takeDamage(int amount){
        model.takeDamage(amount);
    }

    @Override
    public void render(Graphics g) {
        view.render((Graphics2D) g);
    }

    @Override
    public Rectangle getBounds() {
        return model.getHitbox();
    }
    
    public double getSpeed() {
        return model.getSpeed();
    }

    public void setSpeed(double speed) {
        model.setSpeed(speed);
    }

    public int getMaxHealth() {
        return model.getMaxHealth();
    }

    public void setMaxHealth(int maxHealth) {
        model.setMaxHealth(maxHealth);
    }
    
    public void setHealth(int Health) {
        model.setHealth(Health);
    }

    public void heal(int amount) {
        model.heal(amount);
    }

    public double getDamageMultiplier() {
        return model.getDamageMultiplier();
    }

    public void setDamageMultiplier(double damageMultiplier) {
        model.setDamageMultiplier(damageMultiplier);
    }

    public double getDodgeChance() {
        return model.getDodgeChance();
    }

    public void setDodgeChance(double dodgeChance) {
        model.setDodgeChance(dodgeChance);
    }

    public double getPickupRange() {
        return model.getPickupRange();
    }

    public void setPickupRange(double pickupRange) {
        model.setPickupRange(pickupRange);
    }
    
    public int getHealth() {
        return model.getHealth();
    }
    
    public Game getGame() {
        return this.game; 
    }
}
