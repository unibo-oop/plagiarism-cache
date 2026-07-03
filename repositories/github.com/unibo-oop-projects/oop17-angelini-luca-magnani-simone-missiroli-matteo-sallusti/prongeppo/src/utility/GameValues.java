package utility;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Simone
 * the class with the values we need in this game.
 */
public final class GameValues {

    /**
     * world height =/= effective pixel of world.
     */
    public static final int WORLDHEIGHT = 1500;
    /**
     * world width =/= effective pixel of world.
     */
    public static final int WORLDWIDTH = 1500;
    /**
     * ball's diameter.
     */
    public static final int BALL_DIMENSION = 60;
    /**
     * starting panel height.
     */
    public static final int PANELWIDTH = 1024;
    /**
     * starting panel width.
     */
    public static final int PANELHEIGHT = 768;

    /**
     * the height of bar.
     */
    public static final int BARY = 170;

    /**
     * the width of bar.
     */
    public static final int BARX = 25;

    /**
     * the largest font.
     */
    public static final int FONT_LARGE  = 70;

    /**
     *  a medium font dimension.
     */
    public static final int FONT_MEDIUM  = 50;

    /**
     *  a small font dimension.
     */
    public static final int FONT_SMALL  = 30;

    /**
     * an intrigant compromise between SMALL and SMALLEST font dimension.
     */
    public static final int FONT_SMALLER = 25;

    /**
     *  the smallest font dimension.
     */
    public static final int FONT_SMALLEST  = 15;

    /**
     * distance of bar from the boundary.
     */
    public static final int DISTANCEFROMBOUND = 15;
    /**
     * the largest button.
     */
    public static final int BUTTON_LARGE = 150;
    /**
     * the medium button.
     */
    public static final int BUTTON_MEDIUM = 100;
    /**
     * the smallest button.
     */
    public static final int BUTTON_SMALL = 70;
    /**
     * the possibility of create a pick up.
     */
    public static final double PICK_UP_PROBABILITY = 0.0025;
    /**
     * PU's diameter.
     */
    public static final int PU_DIMENSION = 35;
    /**
     * max pickUp number.
     */
    public static final int PU_MAX_NUM = 25;
    /**
     * score to win.
     */
    public static final int MAX_SCORE = 3;
    /**
     * the % minim distance to bound for pickup.
     */
    public static final double PU_DISTANCEBOUND = 0.1;
    /**
     * the index of last line in KeyBinding.txt.
     */
    public static final int LASTKEYBINDINGLINE = 7;

    /**
     * default int-code keys.
     */
    public static final List<Integer> DEFAULTKEYBINDING = new ArrayList<>(Arrays.asList(87, 83, 8, 10, 68, 67, 38, 40));
    /**
     * path for the keys.
     */
    public static final String KEYPATH = "keybinding.txt";
    /**
     * max init ball speed.
     */
    public static final int BALL_SPEED = 10;
    /**
     * the value of speed that is increased from PU_SPEEDUP.
     */
    public static final int PU_SPEEDINCREASE = 2;
    /**
     * the value of the y (maybe) added to ball in each bounce.
     */
    public static final int RANDOMBOUNCE_Y = 2;
    /**
     * return the starting value of static value audio.
     */
    public static final boolean ISSOUNDENABLED = true;
    /**
     *  return the number of movement between each zigzag movement.
     */
    public static final int ZIGZAG_TIME = 20;
    /**
     * the Y speed during zigzag Combo.
     */
    public static final int ZIGZAG_SPEED = 20;
    /**
     * the x speed multiplier during fast Combo.
     */
    public static final int FAST_SPEED = 5;
    /**
     * The dimension of the gifs on GameOverPanel.
     */
    public static final int GIF_DIMENSION = 200;

    /**
     * The List of all gif-String of gameoverpanel.
     */
    public static final List<String> GAMEOVER_LIST_STRING = new ArrayList<>(Arrays.asList("/yeah.gif", "/scrubs.gif", "/goku.gif", "/jack.gif", "/dog.gif", "/wow.gif", "/will.gif"));

    private GameValues() { }
}
