package ala.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * BossHealthView class.
 * 
 */
public class BossHealthView extends StatusBarView {
    private static final double BOSS_HEALTH_WIDTH = 1000;
    private static final double BOSS_HEALTH_HEIGHT = 20;
    private static final Color BOSS_HEALTH_COLOR = Color.RED;

    /**
     * Constructor.
     * 
     * @param layer
     * @param x
     * @param y
     * 
     */
    public BossHealthView(final Pane layer, final double x, final double y) {
        super(layer, x, y, BOSS_HEALTH_WIDTH, BOSS_HEALTH_HEIGHT, BOSS_HEALTH_COLOR);
    }
}
