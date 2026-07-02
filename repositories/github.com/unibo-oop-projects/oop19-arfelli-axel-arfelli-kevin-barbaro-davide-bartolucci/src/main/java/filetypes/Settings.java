package filetypes;

import javafx.util.Pair;

/**
 * This interface describes the type "Settings",
 * providing various methods to change and obtain
 * the internal fields, used to store the preferred window size.
 */
public interface Settings {

    /**
     * @return window size as a Pair, made of width and height.
     */
    Pair<Integer, Integer> getWindowSize();

    /**
     * @return the default window size as a Pair, made of width and height.
     */
    Pair<Integer, Integer> getDefaultWindowSize();

    /**
     * Resets Settings to its default values.
     */
    void setToDefaultSettings();

    /**
     * Sets the width and height variables values according to input values.
     * @param width the screen width to be set, in pixels
     * @param height the screen height to be set, in pixels
     */
    void setWindowSize(int width, int height);

    /**
     * @return the screen width, in pixels.
     */
    int getWidth();

    /**
     * @return the screen height, in pixels.
     */
    int getHeight();

    /**
     * Sets the width value to input value.
     * @param width the screen width to be set, in pixels
     */
    void setWidth(int width);

    /**
     * Sets the height value to input value.
     * @param height the screen height to be set, in pixels
     */
    void setHeight(int height);

}
