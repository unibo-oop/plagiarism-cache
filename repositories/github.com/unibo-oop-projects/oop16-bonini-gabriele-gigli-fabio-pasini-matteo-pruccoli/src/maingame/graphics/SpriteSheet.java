package maingame.graphics;

import java.awt.Dimension;

/** Interfaccia SpriteSheet. */
public interface SpriteSheet {

    /**
     * Ritorna i pixels dello SpriteSheet.
     * 
     * @return il vettore di pixels.
     */
    int[] getPixels();

    /**
     * Ritorna tutte le sprites dello SpriteSheet.
     * 
     * @return il vettore di sprites.
     */
    Sprite[] getSprites();

    /**
     * Ritorna la dimensione dello SpriteSheet.
     * 
     * @return dimension(lunghezza e larghezza) dello SpriteSheet.
     */
    Dimension getDimension();

}