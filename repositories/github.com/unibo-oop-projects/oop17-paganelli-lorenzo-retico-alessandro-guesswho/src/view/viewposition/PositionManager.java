package view.viewposition;

import java.awt.Dimension;
import java.awt.Point;
import java.util.Collections;
import java.util.Iterator;
import java.util.stream.Stream;

import controller.gameoptions.Modality;
import utilities.Utilities;

/**
 * 
 * Utility class that allows to position PlayerView according to game's modality.
 *
 */
public final class PositionManager {

    private static Iterator<ViewPosition> positions = Collections.emptyIterator();

    private PositionManager() {
    }
    /**
     * Create the different positions according to the actual game's modality.
     * @param modality the game's modality
     */
    public static void createPositions(final Modality modality) {
        Utilities.requireNonNull(modality);
        if (modality != Modality.SIMULATION) {
            positions = (modality == Modality.SINGLE_PLAYER  ? Stream.of(ViewPosition.CENTER).iterator() : Stream.of(ViewPosition.LEFT, ViewPosition.RIGHT).iterator()); 
        }
    }
    /**
     * Obtain the Point to place the PlayerView.
     * @param childrenDimension the dimension of the PlayerView
     * @return the Point
     */
    public static Point getPoint(final Dimension childrenDimension) {
        Utilities.requireNonNull(childrenDimension);
        if (!positions.hasNext()) {
            throw new UnsupportedOperationException();
        }
        return getSpecificPosition(childrenDimension, positions.next());
    }
    /**
     * Obtain the Point to place a Component in a specific ViewPosition.
     * @param childrenDimension the dimension of the component
     * @param position the ViewPosition
     * @return the Point
     */
    public static Point getSpecificPosition(final Dimension childrenDimension, final ViewPosition position) {
        Utilities.requireNonNull(childrenDimension, position);
        final Point newPoint = new Point(position.getPoint());
        newPoint.translate((position.getDimension().width - childrenDimension.width) / 2, (position.getDimension().height - childrenDimension.height) / 2);
        return newPoint;
    }

}
