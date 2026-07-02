package view.image;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import view.display.ScreenUtilities;

/**
 * Provides methods for resizing and repositioning graphics.
 */
public final class ViewComponentUtilities {

    private ViewComponentUtilities() {
    }

    /**
     * Resize and reposition a node.
     * @param <E> Node type
     * @param node The node to be resized and repositioned
     * @param x The relative X position (as a percentage)
     * @param y The relative Y position (as a percentage)
     */
    public static <E extends Region> void resizeAndReposition(final E node, final double x, final double y) {
        resize(node);
        reposition(node, x, y);
    }

    /**
     * Resize and reposition an ImageView.
     * @param image The ImageView to be resized and repositioned
     * @param x The relative X position (as a percentage)
     * @param y The relative Y position (as a percentage)
     */
    public static void resizeAndReposition(final ImageView image, final double x, final double y) {
        resize(image);
        reposition(image, x, y);
    }

    /**
     * Resize and reposition the panel and background in case the full screen mode has been set and the current resolution is not supported.
     * @param panel Panel
     * @param background Background
     */
    public static void resizeAndReposition(final Pane panel, final Rectangle background) {
        if (ScreenUtilities.isFullscreen()) {
            panel.setStyle("-fx-background-color: #000000");
            panel.setLayoutX((ScreenUtilities.getScreenWidth() - ScreenUtilities.getCurrentWidth()) / 2);
            panel.setLayoutY((ScreenUtilities.getScreenHeight() - ScreenUtilities.getCurrentHeight()) / 2);
        } else {
            panel.setLayoutX(0);
            panel.setLayoutY(0);
        }
        background.setWidth(ScreenUtilities.getCurrentWidth());
        background.setHeight(ScreenUtilities.getCurrentHeight());
    }

    /**
     * Resize a node.
     * @param <E> Node type
     * @param node The node to be resized
     */
    public static <E extends Region> void resize(final E node) {
        node.setScaleX(ScreenUtilities.getCurrentScaleFactor());
        node.setScaleY(ScreenUtilities.getCurrentScaleFactor());
    }

    /**
     * Resize an ImageView.
     * @param image The ImageView to be resized
     */
    public static void resize(final ImageView image) {
        image.setScaleX(ScreenUtilities.getCurrentScaleFactor());
        image.setScaleY(ScreenUtilities.getCurrentScaleFactor());
    }

    /**
     * Reposition a node.
     * @param <E> Node type
     * @param node The node to be repositioned
     * @param x The relative X position (as a percentage)
     * @param y The relative Y position (as a percentage)
     */
    public static <E extends Region> void reposition(final E node, final double x, final double y) {
        node.setLayoutX(ScreenUtilities.getPercXPos(x, node.getPrefWidth()));
        node.setLayoutY(ScreenUtilities.getPercYPos(y, node.getPrefHeight()));
    }

    /**
     * Reposition an ImageView.
     * @param image The ImageView to be repositioned
     * @param x The relative X position (as a percentage)
     * @param y The relative Y position (as a percentage)
     */
    public static void reposition(final ImageView image, final double x, final double y) {
        image.setLayoutX(ScreenUtilities.getPercXPos(x, image));
        image.setLayoutY(ScreenUtilities.getPercYPos(y, image));
    }

}
