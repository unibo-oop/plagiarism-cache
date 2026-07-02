package model.effects.tools;

import model.effects.Effect;
import model.effects.EffectType;

/**
 * Represents the contract for a generic regular cutting.
 */
public interface Cut extends Effect {

    /**
     * @param right value to cut from the right edge of image to the left
     */
    void setRight(double right);

    /**
     * @param left value to cut from the left edge of image to the right
     */
    void setLeft(double left);

    /**
     * @param low value to cut from the low edge of image to the top
     */
    void setLow(double low);

    /**
     * @param top value to cut from the top edge of image to the low
     */
    void setTop(double top);

    /**
     * @return value cutted from the right edge of image to the left
     */
    double getRight();

    /**
     * @return left value cutted from the left edge of image to the right
     */
    double getLeft();

    /**
     * @return low value cutted from the low edge of image to the top
     */
    double getLow();

    /**
     * @return top value cutted from the top edge of image to the low
     */
    double getTop();

    /**
     * Allow to preserve reference about every specific cutting effect generated.
     * 
     * @return {@link EffectType} this has to be "return EffectType.Cut;"
     */
    EffectType getEffectType();

}
