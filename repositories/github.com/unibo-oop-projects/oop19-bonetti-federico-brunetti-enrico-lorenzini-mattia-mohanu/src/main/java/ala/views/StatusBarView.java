package ala.views;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/**
 * 
 * Status bar, class that create the a bar for every important value to potray.
 */
public class StatusBarView {
    //Attributes:
    private Rectangle bar;
    private double width;
    private double height;

    //Constructor:
    /**
     * @param layer
     * @param x
     * @param y
     * @param width
     * @param height
     * @param color
     */
    public StatusBarView(final Pane layer, final double x, final double y, final double width, final double height, final Color color) {
        this.bar = new Rectangle();
        this.bar.setWidth(width);
        this.bar.setHeight(height);

        this.bar.relocate(x, y);
        this.bar.setFill(color); 

        layer.getChildren().add(this.bar);
    }

    //Getters&Setters:
    public final Rectangle getStatusBar() {
        return this.bar;
    }

    public final double getWidth() {
        return width;
    }

    public final void setWidth(final double width) {
        this.width = width;
    }

    public final double getHeight() {
        return height;
    }

    public final void setHeight(final double height) {
        this.height = height;
    }
}
