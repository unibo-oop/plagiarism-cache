package view.board;

import java.util.List;

/**
 * This class menage the coordinates for the entire tileline, same coordinates are used to place players (meeples) on
 * the table tile line.
 */
public interface Coordinates {
    /**
     * This method return a list of point to places all the tile and to guide the placing of players (meeple) on the
     * tile line.
     * 
     * @return A list of 2D point.
     */
    List<Point2D> getCoordinates();

    /**
     * This method return the point where to place the boat.
     * 
     * @return A 2D point.
     */
    Point2D getBoatCoordinates();

    /**
     * This method set the point where to place the boat.
     * 
     * @param point
     *                  The point where to put the boat.
     */
    void setBoatCoordinates(Point2D point);

}
