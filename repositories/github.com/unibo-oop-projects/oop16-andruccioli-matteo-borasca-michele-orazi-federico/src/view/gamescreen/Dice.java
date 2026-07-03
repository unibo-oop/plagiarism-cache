package view.gamescreen;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * 
 * Class representation of a dice.
 *
 */
public class Dice extends ImageView {

    private static final double WIDTH = 64;
    private final int value;

    /**
     * 
     * Class constructor.
     * 
     * @param v
     *          Value of dice to display.
     * 
     */
    public Dice(final int v) {
        this.value = v;
        this.setImage(new Image(getClass().getResource("/images/dice" + v + ".png").toExternalForm()));
    }

    /**
     * 
     * Getter of a dice.
     * 
     * @param v
     *          The value of a dice. 
     *
     * @return
     *          The dice. 
     *
     */
    public int getValue(final double v) {
        return this.value;
    }

    /**
     * 
     * Getter of a dice.
     * 
     * @return
     *          The value of a dice. 
     *
     */
    public static double getWidth() {
        return WIDTH;
    }
}
