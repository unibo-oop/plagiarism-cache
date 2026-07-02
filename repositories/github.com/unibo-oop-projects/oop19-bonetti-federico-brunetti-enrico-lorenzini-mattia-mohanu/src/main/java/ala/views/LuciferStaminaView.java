package ala.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
/**
 * LuciferStaminaView class.
 * 
 */
public class LuciferStaminaView extends StatusBarView {
    private static final double LUCIFER_STAMINA_WIDTH = 60;
    private static final double LUCIFER_STAMINA_HEIGHT = 10;
    private static final Color LUCIFER_STAMINA_COLOR = Color.BLUE;
    /**
     * Constructor.
     * 
     * @param layer
     * @param x
     * @param y
     * 
     */
    public LuciferStaminaView(final Pane layer, final double x, final double y) {
        super(layer, x, y, LUCIFER_STAMINA_WIDTH, LUCIFER_STAMINA_HEIGHT, LUCIFER_STAMINA_COLOR);
    }
}
