package zengine.constants;

import java.awt.Color;

/**
 * This enum is a list of colors that are passed as argument of some particular
 * drawing functions.
 */
public enum ZengineColor {

    // colors

    /**
     * Constant representing the color white.
     */
    C_WHITE(Color.white),

    /**
     * Constant representing the color light-gray, same as C_LIGHTGREY.
     */
    C_LIGHTGRAY(Color.lightGray),

    /**
     * Constant representing the color light-grey, same as C_LIGHTGRAY.
     */
    C_LIGHTGREY(Color.lightGray),

    /**
     * Constant representing the color gray, same as C_GREY.
     */
    C_GRAY(Color.gray),

    /**
     * Constant representing the color grey, same as C_GRAY.
     */
    C_GREY(Color.gray),

    /**
     * Constant representing the color dark-gray, same as C_DARKGREY.
     */
    C_DARKGRAY(Color.darkGray),

    /**
     * Constant representing the color dark-grey, same as C_DARKGRAY.
     */
    c_DARKGREY(Color.darkGray),

    /**
     * Constant representing the color black.
     */
    C_BLACK(Color.black),

    /**
     * Constant representing the color blue.
     */
    C_BLUE(Color.blue),

    /**
     * Constant representing the color cyan.
     */
    C_CYAN(Color.cyan),

    /**
     * Constant representing the color green.
     */
    C_GREEN(Color.green),

    /**
     * Constant representing the color magenta.
     */
    C_MAGENTA(Color.magenta),

    /**
     * Constant representing the color orange.
     */
    C_ORANGE(Color.orange),

    /**
     * Constant representing the color pink.
     */
    C_PINK(Color.pink),

    /**
     * Constant representing the color red.
     */
    C_RED(Color.red),

    /**
     * Constant representing the color yellow.
     */
    C_YELLOW(Color.yellow),;

    private Color value = Color.white;

    ZengineColor(final Color color) {
        this.value = color;
    }

    /**
     * returns the Color described by this enum.
     * 
     * @return the Color described by this enum
     */
    public Color getValue() {
        return value;
    }
}
