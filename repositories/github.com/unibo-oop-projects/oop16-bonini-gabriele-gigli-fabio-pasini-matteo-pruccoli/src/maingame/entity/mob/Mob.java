package maingame.entity.mob;

import maingame.entity.Entity;
import maingame.graphics.Sprite;
import util.Vector2;

/**
 * Interfaccia di un menu generico.
 */
public interface Mob extends Entity {
    /**
     * . Funzione che permette al Mob di muoversi
     * 
     * @param pos
     *            vettore con coordinate del mob
     * @param knockback
     *            contraccolpo
     */
    void move(Vector2<Integer> pos, boolean knockback);

    /**
     * . chiama la sprite del corpo del mob in base alla luce e se è colpito
     * 
     * variabile per l'editor
     */
    void renderBodies();

    /**
     * . chiama la sprite della testa del mob in base alla luce e se è colpito
     * 
     * variabile per l'editor
     */
    void renderHeads();

    /**
     * 
     * @return la sprite animata
     */
    Sprite getAnimationSprite();

    /**
     * 
     * @return il offset della x per il render
     */
    int getRenderXOffset();

    /**
     * 
     * @return la vita del mob
     */
    int getHealth();

    /**
     * . setter per la vita
     * 
     * @param health
     *            intero per la vita
     */
    void setHealth(int health);

    /**
     * . setter se il mob è stato danneggiato
     * 
     * @param isDamaged
     *            boolean per sapere se danneggiato
     */
    void setDamaged(boolean isDamaged);

    /**
     * . setter per il tempo in cui è danneggiato
     * 
     * @param timer
     *            intero per il tempo di danneggiamento
     */
    void setDamagedTimer(int timer);

    /**
     * . setter per il danno corpo a corpo del mob
     * 
     * @param danno
     *            intero del danno
     */
    void setDamage(int danno);

    /**
     * setter della skin del player.
     * 
     * @param skin
     *            skin da usare
     */
    void setSkin(int skin);

    /**
     * 
     * @return ritorna il colore per spawn
     */
    int getLevelColor();

    /**
     * 
     * @return il nome player per level e editor
     */
    String getName();

    /**
     * @return se si puo nuotare sopra
     */
    boolean isCanSwim();

    /**
     * 
     * @return la skin del player
     */
    int getSkin();

    /**
     * Ritorna l'ultimo movimento effettuato dal mob.
     * 
     * @return Movimento del ciclo update precedente.
     * 
     */
    Vector2<Integer> getLastMovement();

    /**
     * Setta l'ultimo movimento effettuato dal mob.
     * 
     * @param movement
     *            Movimento del mob.
     * 
     */
    void setLastMovement(Vector2<Integer> movement);

    /**
     * Verifica la collisione con u oggetto solido.
     * 
     * @param movement
     *            Movimento.
     * @return True se sulla tile c'e un oggetto solido.
     */
    boolean solidTileCollision(Vector2<Integer> movement);

}
