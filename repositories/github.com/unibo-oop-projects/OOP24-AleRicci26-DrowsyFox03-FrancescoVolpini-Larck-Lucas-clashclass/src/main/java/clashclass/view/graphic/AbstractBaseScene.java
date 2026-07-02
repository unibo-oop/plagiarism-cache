package clashclass.view.graphic;

import clashclass.commons.GameConstants;

/**
 * Abstract class containing all the basic methods and variables for a BaseScene.
 */
public abstract class AbstractBaseScene implements BaseScene {
    private static final double WINDOW_WIDTH = GameConstants.SCREEN_WIDTH;
    private static final double WINDOW_HEIGHT = GameConstants.SCREEN_HEIGHT;
    private final Window window;
    private Graphic graphics;

    /**
     * Constructs the scene.
     *
     * @param window the window reference
     */
    protected AbstractBaseScene(final Window window) {
        this.window = window;
    }

    /**
     * @return the window
     */
    public Window getWindow() {
        return window;
    }

    /**
     * @return the width of the window
     */
    public final double getWindowWidth() {
        return WINDOW_WIDTH;
    }

    /**
     * @return the height of the window
     */
    public final double getWindowHeight() {
        return WINDOW_HEIGHT;
    }

    /**
     * Gets the graphics.
     *
     * @return the graphics
     */
    protected Graphic getGraphics() {
        return graphics;
    }

    /**
     * Sets the graphics.
     *
     * @param graphics the graphics
     */
    protected void setGraphics(final Graphic graphics) {
        this.graphics = graphics;
    }
}
