package ala.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class LuciferHealthView extends StatusBarView {
    private static final double LUCIFER_HEALTH_BAR_WIDTH = 100; //This width isn't vincolate to the effective life of the carachter.
    private static final double LUCIFER_HEALTH_BAR_HEIGHT = 10;
    private static final Color LUCIFER_HEALTH_COLOR = Color.GREEN;

    public LuciferHealthView(final Pane layer, final double x, final double y) {
        super(layer, x, y, LUCIFER_HEALTH_BAR_WIDTH, LUCIFER_HEALTH_BAR_HEIGHT, LUCIFER_HEALTH_COLOR);
    }
}
