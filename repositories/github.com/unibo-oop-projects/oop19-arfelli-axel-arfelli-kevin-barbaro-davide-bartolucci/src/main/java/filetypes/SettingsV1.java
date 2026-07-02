package filetypes;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.Serializable;

import javafx.util.Pair;

/**
 * A simple implementation of the Settings interface.
 * Its purpose is to store user preferences and it
 * is going to be saved and loaded from file.
 */
public class SettingsV1 implements Serializable, Settings {

    private static final long serialVersionUID = -1312112427979123670L;
    //private static final int DEFAULT_WIDTH = 1280;
    //private static final int DEFAULT_HEIGHT = 720;

    /**
     * The custom window size.
     */
    private Pair<Integer, Integer> customWindowSize;
    /**
     * The default window size.
     */
    private final Pair<Integer, Integer> defaultWindowSize;
    /**
     * The custom window width.
     */
    private int width;
    /**
     * The custom window height.
     */
    private int height;

    /**
     * This constructor initializes screenSize by obtaining 
     * the user's actual screen size, sets the default window size to
     * be 1/2 of the screen size and sets it as the  custom window size.
     */
    public SettingsV1() {
        final Dimension tmpScreenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Pair<Integer, Integer> screenSize = new Pair<>(tmpScreenSize.width, tmpScreenSize.height);
        this.defaultWindowSize = new Pair<>(screenSize.getKey() / 2, screenSize.getValue() / 2);
        this.setToDefaultSettings();
    }

    @Override
    public final int getWidth() {
        return width;
    }

    @Override
    public final int getHeight() {
        return height;
    }

    @Override
    public final void setWidth(final int width) {
        this.width = width;
        this.setWindowSize(this.width, this.height);
    }

    @Override
    public final void setHeight(final int height) {
        this.height = height;
        this.setWindowSize(this.width, this.height);
    }

    @Override
    public final Pair<Integer, Integer> getWindowSize() {
        return this.customWindowSize;
    }

    @Override
    public final void setWindowSize(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.customWindowSize = new Pair<>(width, height);
    }

    @Override
    public final Pair<Integer, Integer> getDefaultWindowSize() {
        return this.defaultWindowSize;
    }

    @Override
    public final void setToDefaultSettings() {
        this.customWindowSize = this.defaultWindowSize;
        this.width = this.defaultWindowSize.getKey();
        this.height = this.defaultWindowSize.getValue();
    }

}
