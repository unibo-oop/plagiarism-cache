package maingame.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import util.Vector2;

/** Interfaccia Screen. */

public interface Screen {

    /**
     * Dà immagine al gioco, colorando i pixel con quelli della sprite.
     * 
     * @param position
     *            posizione in coordinate di dove renderizzare la sprite.
     * @param sprite
     *            sprite da cui prendere il colore dei pixel.
     * @param intensity
     *            intensità del colore della sprite (normale corrisponde a
     *            1.0).
     * @param damaged
     *            booleano per colorare di bianco nel caso di mob colpito.
     * @param fixed
     *            booleano per casi di render senza offset, cioè fissi sullo
     *            schermo (come Hud).
     */
    void render(Vector2<Integer> position, Sprite sprite, double intensity, boolean damaged, boolean fixed);

    /**
     * Render dell'Hud(heads-up display), cioè della salute espressa in cuori
     * del player, nonchè dei proiettili o oggetti raccolti.
     * 
     * @param health
     *            salute attuale del player.
     * @param maxHealth
     *            salute massima del player.
     */
    void renderHud(int health, int maxHealth);

    /**
     * Imposta l'offset di Screen.
     * 
     * @param offset
     *            offset in formato di vettore a doppia coordinata(x,y).
     */
    void setOffset(Vector2<Integer> offset);

    /**
     * Ritorna i pixel colorati da Screen.
     * 
     * @return un vettori di interi, i pixel.
     * 
     */
    int[] getPixels();

    /**
     * Ritorna i pixel originali.
     * 
     * @return un vettori di interi, i pixel.
     * 
     */
    int[] getOriginalPixels();

    /**
     * Setta i pixel con una chiamata dall'esterno.
     * 
     * @param i
     *            indice del pixel.
     * @param color
     *            valore del colore.
     */
    void setPixels(int i, int color);

    /**
     * Ritorna la dimensione di Screen.
     * 
     * @return (Dimension)la lunghezza x e larghezza y di Screen.
     */
    Dimension getDimension();

    /**
     * Render sullo schermo delle varie scritte grafiche.
     * 
     * @param g
     *            Grafica buffered di Game.
     */
    void renderDraws(Graphics g);

    /**
     * Ritorna se il gioco va terminato (dopo l'animazione).
     * 
     * @return booleano fine gioco.
     */
    boolean isEndGame();

    /**
     * Ritorna true se piove.
     * 
     * @return booleano pioggia attiva.
     */
    boolean isRain();

    /**
     * Ritorna true se tuona.
     * 
     * @return booleano tuono attivo.
     */
    boolean isThunder();

    /**
     * Setta pioggia.
     * 
     * @param rain
     *            booleano pioggia attiva.
     */
    void setRain(boolean rain);

    /**
     * Setta endGame a true o false.
     * 
     * @param b
     *            booleano per setting
     */
    void setEndGame(boolean b);

    /**
     * Setta booleano per animazione di +Exp.
     * 
     * @param b
     *            true o false per sapere se deve avviare l'animazione
     * @param exp
     *            exp ricevuta
     */
    void setExpAnim(boolean b, int exp);

    /** Fa un reset dello screen. */
    void reset();

}