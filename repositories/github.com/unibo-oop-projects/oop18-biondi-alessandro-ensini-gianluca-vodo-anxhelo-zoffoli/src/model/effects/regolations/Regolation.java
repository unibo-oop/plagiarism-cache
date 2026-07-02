package model.effects.regolations;

import java.awt.Color;
import model.effects.Effect;

/**
 * Represents a the contract for a generic regolation.
 */
public interface Regolation extends Effect {

    /**
     * Change a single pixel. This method is used by apply.
     * 
     * @param source pixel
     * @return modified pixel
     */
    Color changePixel(Color source);

    /**
     * @return index of regolation level.
     */
    int getIndex();

    /**
     * @param inputIndex is regolation level that O wont.
     * @return modified object itself
     */
    Regolation setIndex(int inputIndex);
}
