package view.board;

import java.util.ArrayList;
import java.util.List;

/**
 * This is an abstract class that manage the coordinates to create the board representation of tile line.
 */
public abstract class AbstractCoordinates implements Coordinates {
    /**
     * This List store Point2D that are using in construction of the line of tile.
     */
    private final List<Point2D> listOfCoordinates = new ArrayList<Point2D>();
    /**
     * This Point2D store the place where to put the boat on the board.
     */
    private transient Point2D boatPoint2D;

    /**
     * The class constructor.
     * 
     * @param lenght
     *                   The lenght of tiles line.
     */
    public AbstractCoordinates(final Integer lenght) {

        this.populateCoordinates(lenght);
    };

    /**
     * This method should populate the coordinates in the list of coordinates. Should create a number of 2d point equals
     * to the lenght parameter, in doing this should be remember that for a better view the 2dpoint should have a
     * certain distance between each other.
     * 
     * @param lenght
     *                   The number of 2DPoint to istantiate.
     */
    protected abstract void populateCoordinates(Integer lenght);

    @Override
    public final List<Point2D> getCoordinates() {
        return this.listOfCoordinates;
    }

    @Override
    public final void setBoatCoordinates(final Point2D point) {
        this.boatPoint2D = point;
    }

    @Override
    public final Point2D getBoatCoordinates() {
        return this.boatPoint2D;
    }
}
