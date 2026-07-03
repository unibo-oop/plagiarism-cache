package maingame.level.tile;

import maingame.graphics.Sprite;
import util.Vector2;

/** Interfaccia Tile. */
public interface Tile {

    /**
     * Funzione eseguita 60 volte al secondo, gestisce la ligica delle tile.
     */
    void update();

    /**
     * Renderizza le tile in una certa posizione e con una certa luminosità.
     * 
     * @param position
     *            Posizione in cui renderizzare la tile.
     * @param luminosity
     *            Moltiplicatore di luminosità.
     */
    void render(Vector2<Integer> position, double luminosity);

    /**
     * Ritorna lo stato di solidità della tile.
     * 
     * @return True se la tile è solida, false altrimenti.
     */
    boolean isSolid();

    /**
     * Ritorna lo stato di nuotabilità della tile.
     * 
     * @return True se la tile è nuotabile, false altrimenti.
     */
    boolean isSwimmable();

    /**
     * Ritorna lo stato di emissività della tile.
     * 
     * @return True se la tile è emissiva, false altrimenti.
     */
    boolean isEmitter();

    /**
     * Setta lo stato di nuotabilità della tile.
     * 
     * @return tile.
     */
    Tile makeSwimmable();

    /**
     * Setta lo stato di solidità della tile.
     * 
     * @return tile.
     */
    Tile makeSolid();

    /**
     * Setta lo stato di emissività della tile.
     * 
     * @param lightRadius
     *            Raggio emissivo della tile.
     * @return tile.
     */
    Tile makeEmitter(int lightRadius);

    /**
     * Ritorna la sprite associata alla tile.
     * 
     * @return sprite che viene visualizzata quando la tile viene renderizzata.
     */
    Sprite getSprite();

    /**
     * Ritorna il nome associato alla tile.
     * 
     * @return Denominazione della tile.
     */

    String getName();

    /**
     * Setta la sprite della tile.
     * 
     * @param sprite
     *            Sprite da associare alla tile.
     */

    void setSprite(Sprite sprite);

    /**
     * Ritorna il colore che deve essere usato nel file di creazione del livello
     * per associare una posizione ad una tile.
     * 
     * @return Colore associato alla tile.
     */

    int getLevelColor();

    /**
     * Ritorna il raggio emissivo della tile.
     * 
     * @return Raggio emissivo.
     */
    int getLightRadius();

}