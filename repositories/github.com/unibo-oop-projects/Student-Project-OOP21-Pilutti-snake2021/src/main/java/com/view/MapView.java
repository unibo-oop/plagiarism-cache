package main.java.com.view;

import java.awt.geom.Rectangle2D;

/**
 * Interface that models the view for the game's map.
 */
public interface MapView {

    /**
     * @return the instance of the {@link AppleView}.
     */
    AppleView getAppleView();

    /**
     * @return the instance of the {@link SnakeView}
     */
    SnakeView getSnakeView();

    /**
     * @return return the bounding {@link Rectangle2D} of the game's map.
     */
    Rectangle2D getMapBounds();

    /**
     * Populates the Map of (Position, Position) containing all the screen coordinates for the cells.
     * This method needs to be called after the view is rendered on screen.
     */
    void populateCells();
}
