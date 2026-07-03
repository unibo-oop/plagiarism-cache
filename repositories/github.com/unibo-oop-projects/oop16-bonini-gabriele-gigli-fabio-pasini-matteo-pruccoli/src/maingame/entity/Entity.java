package maingame.entity;

import java.awt.Rectangle;

import maingame.entity.item.Item;
import maingame.entity.mob.Mob;
import maingame.graphics.Sprite;
import maingame.level.Level;
import util.Vector2;

/**
 * Interfaccia genereca di un'entità.
 */
public interface Entity {

    /**
     * aggiorna entità.
     */
    void update();

    /**
     * imposta la rimozione di un entità.
     */
    void setRemove();

    /**
     * ritorna la posizione dell'entità.
     * 
     * @return vettore inerente alla posizione dell'entità
     */
    Vector2<Integer> getPosition();

    /**
     * Setta la posizione dell'entità.
     * 
     * @param position
     *            vettore inerente alla posizione dell'entità
     */
    void setPosition(Vector2<Integer> position);

    /**
     * ritorna la sprite relativa all'oggetto entità.
     * 
     * @return sprite entità
     */
    Sprite getSprite();

    /**
     * ritorna se l'entità deve essere rimossa.
     * 
     * @return true se è da rimuovere
     */
    boolean isRemoved();

    /**
     * inizializza l'entità all'interno del livello.
     * 
     * @param level
     *            il livello nel quale va inizializzata
     */
    void init(Level level);

    /**
     * ritorna la tile con cui collido dato un certo offset.
     * 
     * @param movement
     *            offset
     * @return true se collido
     */
    boolean tileCollision(Vector2<Integer> movement);

    /**
     * ritorna il mob con il quale collido.
     * 
     * @param position
     *            posizione entità
     * @param projectile
     *            se l'entità e un proiettile
     * @return il mob con quale collido
     */
    Mob mobCollision(Vector2<Integer> position, boolean projectile);

    /**
     * ritorna l'oggetto con il quale collido.
     * 
     * @param position
     *            posizione entità
     * @return oggetto con quale collido
     */
    Item itemCollision(Vector2<Integer> position);

    /**
     * @return hitbox dell'entità
     */
    Rectangle getHitbox();
}
