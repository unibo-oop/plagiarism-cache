package maingame.graphics;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import util.Vector2Impl;

/** Classe AnimatedSprite. */

public class AnimatedSprite extends SpriteImpl {

    private int frame;
    private Sprite sprite;
    private int rate;
    private int time;
    private final int length;
    private int count;

    /**
     * Costruttore per sprite animati.
     * 
     * @param dimension
     *            dimensione sprite.
     * @param length
     *            lunghezza del vettore di sprite.
     * @param rate
     *            frequenza di update dello sprite animato.
     * @param sheet
     *            SpriteSheet dello sprite animato.
     */
    public AnimatedSprite(final Dimension dimension, final int length, final int rate, final SpriteSheet sheet) {
        super(dimension);
        this.setSheet(sheet);
        this.length = length;
        this.rate = rate;
        sprite = sheet.getSprites()[0];
    }

    /**
     * Aggiornamento dell' animazione della sprite animata.
     * 
     */
    public void update() {
        time++;
        if (time % rate == 0) {
            if (frame >= length - 1) {
                count++;
                frame = 0;
            } else {
                frame++;
            }
            sprite = getSheet().getSprites()[frame];
        }
    }

    /**
     * Crea una lista di Animated Sprite.
     * 
     * @param animationTypeCount
     *            numero di tipi di animazioni.
     * @param dimensions
     *            dimensioni dei vettori degli sprite animati.
     * @param rates
     *            rates dei vettori degli sprite animati.
     * @param sheet
     *            sheet da cui crea la lista.
     * @return la lista di sprite animati.
     */
    public static List<AnimatedSprite> createAnimation(final int animationTypeCount, final Dimension[] dimensions,
            final int[] rates, final SpriteSheet sheet) {
        final List<AnimatedSprite> animation = new ArrayList<>();
        for (int i = 0; i < animationTypeCount; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                animation.add(new AnimatedSprite(new Dimension(16, 16), (int) dimensions[i].getHeight(), rates[i],
                        new SpriteSheetImpl(sheet, new Vector2Impl<Integer>(i2, getAnimationIndex(i, dimensions)),
                                dimensions[i], new Dimension(16, 16))));
            }
        }
        return animation;
    }

    private static int getAnimationIndex(final int index, final Dimension[] dimensions) {
        int ind = 0;
        for (int i = 0; i < index; i++) {
            ind += dimensions[i].getHeight();
        }
        return ind;
    }

    /**
     * Ritorna la sprite.
     * 
     * @return la sprite.
     */
    public Sprite getSprite() {
        return sprite;
    }

    /**
     * Ritorna il conteggio di quante volte ha fatto l'update.
     * 
     * @return l'intero count.
     */
    public int getCount() {
        return count;
    }

    /**
     * Resetta il conteggio di quante volte ha fatto l'update.
     * 
     */
    public void resetCount() {
        count = 0;
    }

    /**
     * Imposta la frequenza di aggiornamento.
     * 
     * @param frames
     *            Frequenza.
     */
    public void setFrameRate(final int frames) {
        rate = frames;
    }

    /**
     * Ritorna l'indice dell'animazione attuale.
     * 
     * @return l'intero frame.
     */
    public int getFrame() {
        return frame;
    }

    /**
     * Imposta a un indice del vettore degli sprite animati.
     * 
     * @param index
     *            indice
     */
    public void setFrame(final int index) {
        sprite = getSheet().getSprites()[index];
    }

    @Override
    public int[] getPixels() {
        return sprite.getPixels();
    }

}
