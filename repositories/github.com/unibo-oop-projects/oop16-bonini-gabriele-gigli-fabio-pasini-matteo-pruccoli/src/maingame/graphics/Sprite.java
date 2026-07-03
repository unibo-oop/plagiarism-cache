package maingame.graphics;

import java.awt.Dimension;

/** Interfaccia Sprite. */

public interface Sprite {

    /**
     * Ritorna i pixels della sprite.
     * 
     * @return il vettore di pixels.
     */
    int[] getPixels();

    /**
     * Imposta i pixels della sprite.
     * 
     * @param pixels
     *            vettore che contiene i valori dei pixels.
     */
    void setPixels(int... pixels);

    /**
     * Ritorna la dimensione della sprite.
     * 
     * @return dimension(lunghezze e larghezza) di sprite.
     */
    Dimension getDimension();

    /**
     * Getter dello sheet.
     * 
     * @return lo sheet di Sprite.
     */
    SpriteSheet getSheet();

    /**
     * Setter dello sheet.
     * 
     * @param sheet
     *            foglio SpriteSheet della sprite.
     */
    void setSheet(SpriteSheet sheet);

}