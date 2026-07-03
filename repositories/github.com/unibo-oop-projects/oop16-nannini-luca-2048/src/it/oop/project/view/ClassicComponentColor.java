package it.oop.project.view;

import java.awt.Color;

/**
 * Enum for background and font color for components of 2048 view.
 * 
 */
public enum ClassicComponentColor implements GameColor {

    FRAME("#faf8ef", ClassicComponentColor.DARK_FONT), 
    BOARD("#bbada0", ClassicComponentColor.DARK_FONT), 
    BUTTON("#8f7a66", "#eee4da"), 
    SCORE("#bbada0", "#ffffff"), 
    SCORE_TITLE("#bbada0", "#e7e2d4");

    private static final String DARK_FONT = "#776e65";
    private static final String BRIGHT_FONT = "#f9f6f2";
    private static final String WIN_DIALOG_SATURATION = "#edc22e";
    private final Color bgColor;
    private final Color fontColor;

    private ClassicComponentColor(final String bgColor, final String fontColor) {
        this.bgColor = Color.decode(bgColor);
        this.fontColor = Color.decode(fontColor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getBgColor() {
        return bgColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Color getFontColor() {
        return fontColor;
    }

    /**
     * Returns a dark font color.
     * 
     * @return a dark font color.
     */
    public static Color getDarkFont() {
        return Color.decode(DARK_FONT);
    }

    /**
     * Returns a bright font color.
     * 
     * @return a bright font color.
     */
    public static Color getBrightFont() {
        return Color.decode(BRIGHT_FONT);
    }

    /**
     * Returns the saturation color for win dialog.
     * 
     * @return the saturation color for win dialog.
     */
    public static Color getWinDialogSaturation() {
        return Color.decode(WIN_DIALOG_SATURATION);
    }
}
