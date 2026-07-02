package net.pokemonbt.controller.view_controller;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Utilities for the view controllers.
 */
public final class ViewUtilities {
    private static final double SHADOW_Y_OFFSET = 15.0;
    private static final double SHADOW_OPACITY = 0.25;

    private ViewUtilities() {
    }

    /**
     * Stops backgound from going off the round border of a generic Pane, following
     * a
     * custom value.
     * 
     * @param element the target Pane
     * @param value   the custom value
     * @param <T>     the view element
     */
    public static <T extends Region> void setupClipping(final T element, final double value) {
        if (element != null) {
            final Rectangle clipRectangle = new Rectangle();

            clipRectangle.widthProperty().bind(element.widthProperty());
            clipRectangle.heightProperty().bind(element.heightProperty());

            clipRectangle.setArcWidth(value);
            clipRectangle.setArcHeight(value);

            element.setClip(clipRectangle);
        }
    }

    /**
     * Adds dropdown shadow to the passed view element.
     * 
     * @param element the target element.
     */
    public static void addShadow(final Node element) {
        final DropShadow shadow = new DropShadow();

        shadow.setOffsetY(SHADOW_Y_OFFSET);
        shadow.setColor(Color.rgb(0, 0, 0, SHADOW_OPACITY));

        element.setEffect(shadow);
    }

    /**
     * Hides a Parent element from the view.
     * 
     * @param element the target element
     */
    public static void hideParent(final Parent element) {
        element.setVisible(false);
        element.setManaged(false);
    }

    /**
     * Shows an hidden Parent element.
     * 
     * @param element the target element
     */
    public static void showParent(final Parent element) {
        element.setVisible(true);
        element.setManaged(true);
    }

}
