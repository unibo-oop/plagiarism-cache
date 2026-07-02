package it.unibo.oop.supermario.screens;

import com.badlogic.gdx.Gdx;


/**
 *this class is used for handling the skins.
 */
public class GameConstant {

    /**
     * the resolution of the screen.
     */
    public static final int WIDTH_RESOLUTION = 1200;
    /**
     * the resolution of the screen.
     */
    public static final int HEIGHT_RESOLUTION = 624;
    /**
     * contains the path of the json file.
     */
    public static final String SKIN = "skin/glassy-ui.json";
    /**
     * contains the path of the atlas file.
     */
    public static final String SKIN_ATLAS = "skin/glassy-ui.atlas";
    /**
     * the width of the screen.
     */
    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    /**
     * the height of the screen.
     */
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();
    /**
     * the orizontal center of the screen.
     */
    public static final int CENTER_X = SCREEN_WIDTH / 2;
    /**
     * the vertical center of the screen.
     */
    public static final int CENTER_Y = SCREEN_HEIGHT / 2;
    /**
     * 
     */
    public static final int COL_WIDTH = SCREEN_WIDTH / 8;
    /**
     * 
     */
    public static final int ROW_HEIGHT = SCREEN_HEIGHT / 8;
}
