package model.effects;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Represents a the contract for a generic effect.
 */
public interface Effect extends Serializable {
    // long serialVersionUID = 1L;

    /**
     * @param source source image
     * @return modified image
     */
    BufferedImage apply(BufferedImage source);

    /**
     * @return effect name
     */
    String getEffectName();

    /**
     * @param name effect name
     */
    void setEffectName(String name);

    /**
     * @return enum with the effect type
     */
    EffectType getEffectType();

}
