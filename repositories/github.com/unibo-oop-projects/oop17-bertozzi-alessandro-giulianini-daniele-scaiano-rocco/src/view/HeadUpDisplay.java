package view;

import java.util.List;

import javax.swing.JPanel;

import game.Player;

/**
 * Head-up-display.
 */
public abstract class HeadUpDisplay extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -6087089490408447208L;
    /**
     * Max value for a RGB color.
     */
    public static final int MAX_RGB_VALUE = 255;
    /**
     * Font proportions.
     */
    public static final int FONT_PROPORTION = 25;

    /**
     * Render the display.
     * @param list the player/s
     * @param score the current score
     * @param level the current level
     */
    public abstract void render(List<Player> list, int score, int level);
}
