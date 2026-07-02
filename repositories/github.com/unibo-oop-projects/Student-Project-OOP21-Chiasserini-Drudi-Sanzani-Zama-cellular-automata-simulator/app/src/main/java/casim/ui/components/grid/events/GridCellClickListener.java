package casim.ui.components.grid.events;

import casim.ui.components.grid.CanvasGrid;
import javafx.scene.input.MouseEvent;

/**
 * The event listener used for the mouse click event on the {@link CanvasGrid}.
 */
//package-private
class GridCellClickListener extends AbstractMouseEventHandler {

    //package-private
    GridCellClickListener(final CanvasGrid grid) {
        super(grid);
    }

    @Override
    public void handle(final MouseEvent event) {
        final var coord = this.getCoordinatesFromEvent(event);
        final var cell = this.getCanvasGrid().getCell(coord);
        this.getCanvasGrid().onCellClick(event.getButton(), cell, coord);
    }
}
