package casim.ui.components.grid.events;

import casim.ui.components.grid.CanvasGrid;
import casim.utils.coordinate.Coordinates2D;
import casim.utils.coordinate.CoordinatesUtil;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

/**
 * Abstract mouse event handler for {@link CanvasGrid}.
 */
//package-private
abstract class AbstractMouseEventHandler implements EventHandler<MouseEvent> {

    private final CanvasGrid grid;

    //package-private
    AbstractMouseEventHandler(final CanvasGrid grid) {
        this.grid = grid;
    }

    /**
     * Get the cell {@link Coordinates2D} from the mouse event.
     * 
     * @param event the mouse event.
     * @return a {@link Coordinates2D} contaning the cell coordinates.
     */
    protected Coordinates2D<Integer> getCoordinatesFromEvent(final MouseEvent event) {
        return CoordinatesUtil.of(
            (int) (event.getX() / this.grid.getCellSize()), (int) (event.getY() / this.grid.getCellSize()));
    }

    /**
     * Get the canvas grid.
     * 
     * @return the {@link CanvasGrid} related to the {@link AbstractMouseEventHandler}.
     */
    protected CanvasGrid getCanvasGrid() {
        return this.grid;
    }
}
