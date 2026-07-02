package model.effects.tools;

import model.effects.Effect;
import model.effects.EffectType;

/**
 * Represents the contract for a generic regular rotation effect.
 */

public interface Rotate extends Effect {
    /**
     * @param degree this value has to make reference only respect fixed image
     *               center
     */
    void setAngle(double degree);

    /**
     * @return angle that image rotated
     */
    double getAngle();

    /**
     * Allow to preserve reference about every specific cutting effect generated.
     * 
     * @return {@link EffectType} this has to be "return EffectType.Rotate;"
     */
    EffectType getEffectType();
}
