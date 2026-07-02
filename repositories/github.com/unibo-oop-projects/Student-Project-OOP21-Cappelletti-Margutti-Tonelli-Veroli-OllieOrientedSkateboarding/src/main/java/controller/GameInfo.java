package controller;

/**
 * 
 * Class that holds the game informations.
 *
 */
public class GameInfo {

    private static final double SCREEN_WIDTH = 854.0;
    private static final double SCREEN_HEIGHT = 480.0;

    /**
     * Gets the width of the game screen.
     * @return the width of the game screen.
     */
    public double getWidth() {
        return SCREEN_WIDTH;
    }

    /**
     * Gets the height of the game screen.
     * @return the height of the game screen.
     */
    public double getHeight() {
        return SCREEN_HEIGHT;
    }

}
