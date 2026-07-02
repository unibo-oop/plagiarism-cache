package view.board;

import java.util.List;

/**
 * 
 *
 */

public class CoordinatesForBoard {

    private final Coordinates coordinate;

    private final Point2D boatCoordinate;

    /**
     * @param template The Template used for create. 
     */
    public CoordinatesForBoard(final Coordinates template) {
        this.coordinate = template;
        this.boatCoordinate = template.getBoatCoordinates();
    }

    /**
     * @return The list of point 2d coordinates.
     */
    public List<Point2D> getCoordinates() {
        return this.coordinate.getCoordinates();
    }

    /**
     * @return The boat Coordinates
     */
    public Point2D getBoatCoordinates() {
        return this.boatCoordinate;
    }

}
