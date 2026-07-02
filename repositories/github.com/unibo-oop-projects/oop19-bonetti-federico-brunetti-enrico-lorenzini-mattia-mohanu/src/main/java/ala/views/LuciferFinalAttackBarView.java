package ala.views;
/**
 * LuciferFinalAttackBarView class.
 * 
 */
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LuciferFinalAttackBarView extends StatusBarView {
    private static final double FINAL_ATTACK_BAR_WIDTH = 120;
    private static final double FINAL_ATTACK_BAR_HEIGHT = 10;
    private static final Color FINAL_ATTACK_BAR_COLOR = Color.YELLOW;
    /**
     * Constructor.
     * 
     * @param layer
     * @param x
     * @param y
     * 
     */
    public LuciferFinalAttackBarView(final Pane layer, final double x, final double y) {
        super(layer, x, y, FINAL_ATTACK_BAR_WIDTH, FINAL_ATTACK_BAR_HEIGHT, FINAL_ATTACK_BAR_COLOR);
    }
}
