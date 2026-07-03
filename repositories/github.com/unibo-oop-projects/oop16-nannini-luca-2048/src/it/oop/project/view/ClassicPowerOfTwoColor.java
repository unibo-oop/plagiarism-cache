package it.oop.project.view;

import java.awt.Color;

import com.google.common.base.Optional;

/**
 * Enum for background and font color for 2048 tiles.
 *
 */
public enum ClassicPowerOfTwoColor implements GameColor {

    EMPTY(Optional.absent(), "#cdc1b4", ClassicComponentColor.getDarkFont()), 
    ONE(Optional.of(1), "#eee4da", ClassicComponentColor.getDarkFont()), 
    TWO(Optional.of(2), "#ede0c8", ClassicComponentColor.getDarkFont()), 
    THREE(Optional.of(3), "#f2b179", ClassicComponentColor.getBrightFont()), 
    FOUR(Optional.of(4), "#f59563", ClassicComponentColor.getBrightFont()), 
    FIVE(Optional.of(5), "#f67c5f", ClassicComponentColor.getBrightFont()), 
    SIX(Optional.of(6), "#f65e3b", ClassicComponentColor.getBrightFont()), 
    SEVEN(Optional.of(7), "#edcf72", ClassicComponentColor.getBrightFont()), 
    EIGHT(Optional.of(8), "#edcc61", ClassicComponentColor.getBrightFont()), 
    NINE(Optional.of(9), "#edc850", ClassicComponentColor.getBrightFont()), 
    TEN(Optional.of(10), "#edc53f", ClassicComponentColor.getBrightFont()), 
    ELEVEN(Optional.of(11), "#edc22e", ClassicComponentColor.getBrightFont()), 
    GREATER(Optional.of(12), "#3c3a32", ClassicComponentColor.getBrightFont());

    private final Optional<Integer> power;
    private final Color bgColor;
    private final Color fontColor;

    private ClassicPowerOfTwoColor(final Optional<Integer> power,
            final String bgColor, final Color fontColor) {
        this.power = power;
        this.bgColor = Color.decode(bgColor);
        this.fontColor = fontColor;
    }

    /**
     * Returns the power of two that this enum represents.
     * 
     * @return the power of two that this enum represents.
     */
    public Optional<Integer> getPower() {
        return this.power;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBgColor() {
        return this.bgColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getFontColor() {
        return this.fontColor;
    }

}
