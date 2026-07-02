package view.utils;

import java.util.EnumMap;
import java.util.function.BiFunction;
import java.util.function.Function;

import constraints.DirOfMovement;
import model.entities.trafficlight.TrafficLight.Phases;
import model.environment.Point;
import view.utils.images.Tiles;

/**
 * Interface that defines utility methods for drawing objects on a image.
 */
public interface MappingUtils {

    /**
     * A map that associates to each phase of a traffic light a corresponding color.
     */
    EnumMap<Phases, Tiles> PHASE_TO_TILE = new EnumMap<Phases, Tiles>(Phases.class) {

        /**
         *
         */
        private static final long serialVersionUID = -7063313937288153002L;

        {
            put(Phases.GREEN, Tiles.GREEN_TRAFFIC_LIGHT);
            put(Phases.RED, Tiles.RED_TRAFFIC_LIGHT);
            put(Phases.YELLOW, Tiles.YELLOW_TRAFFIC_LIGHT);
        }
    };

    /**
     * @param phase the phase to be associated
     * @return the tile associated to the phase
     */
    static Tiles mapPhaseToColor(final Phases phase) {
        return PHASE_TO_TILE.get(phase);
    }

    /**
     * This function converts a pair of coordinates expressed in terms of rows and
     * columns of a square grid in a point of the Cartesian plane.
     *
     * @param position      coordinates expresses as rows and columns
     * @param gridDimension the dimension of the square grid
     * @return The translated point
     */
    static BiFunction<Point, Integer, Point> mapToGrid(final Point position, final int gridDimension) {
        return (pos, dim) -> {
            final int upperBound = dim - 1;
            final int midPoint = upperBound / 2;
            return Point.of(pos.getX() > midPoint ? upperBound - pos.getX() : upperBound - pos.getX(),
                    pos.getY() >= midPoint ? upperBound - pos.getY() : upperBound - pos.getY());
        };
    }

    /**
     * This function converts a point that expresses position in a logical grid in a
     * point that expresses a location in a grid of pixels.
     *
     * In other terms this function states where a point should be drawn on an
     * image.
     *
     * @param position   the point with coordinates expressed as integers
     * @param cellWidth  the width of the cells of the grid
     * @param cellHeight the height of the cells of the grid
     * @return The point with coordinates expressed in pixel
     */
    static Function<Point, Point> mapToPixel(final Point position, final int cellWidth, final int cellHeight) {
        return t -> Point.of(position.getX() * cellWidth, position.getY() * cellHeight);
    }

    /**
     * Used to put traffic lights at a minimum distance from the roads.
     *
     * @param sense  the sense associated to the traffic light
     * @param offset the amount of distance between the traffic light and the road
     * @return A point translated of coordinates determined by the sense given
     */
    static Point computeOffset(final DirOfMovement sense, final int offset) {
        Point translatedPosition;
        switch (sense) {
        case NORTH_SOUTH:
            translatedPosition = Point.of(-offset, offset);
            break;
        case SOUTH_NORTH:
            translatedPosition = Point.of(offset, -offset);
            break;
        case EAST_WEST:
            translatedPosition = Point.of(offset, offset);
            break;
        case WEST_EAST:
            translatedPosition = Point.of(-offset, -offset);
            break;
        default:
            throw new IllegalArgumentException("No valid direction of movement was selected");
        }
        return translatedPosition;
    }

}
