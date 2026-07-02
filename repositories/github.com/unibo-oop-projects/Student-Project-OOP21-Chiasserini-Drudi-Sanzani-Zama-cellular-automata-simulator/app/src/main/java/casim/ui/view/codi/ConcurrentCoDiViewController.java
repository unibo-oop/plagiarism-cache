package casim.ui.view.codi;

import casim.controller.automaton.CoDiControllerImpl;
import casim.core.AppManager;
import casim.model.codi.cell.CoDiCellState;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.utils.statecolormapper.StateColorMapper;
import casim.ui.view.ConcurrentAutomatonViewController;
import casim.utils.grid.Grid2D;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of CoDi's {@link ConcurrentAutomatonViewController}.
 */
public class ConcurrentCoDiViewController extends ConcurrentAutomatonViewController<CoDiCellState> implements CoDiLayerHandler {

    /**
     * Construct a new {@link ConcurrentCoDiViewController}.
     * 
     * @param appManager the {@link AppManager} holding the view.
     * @param controller the AutomatonController controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public ConcurrentCoDiViewController(final AppManager appManager, final CoDiControllerImpl controller,
            final CanvasGridImpl grid, final StateColorMapper<CoDiCellState> colorMapper) {
        super(appManager, controller, grid, colorMapper);
        CoDiViewUtils.showStartAlert(appManager.getContainer());
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.getView().addEventFilter(KeyEvent.KEY_PRESSED, this.changeLayerHandler());
    }

    @Override
    protected synchronized void updateView(final Grid2D<CoDiCellState> state) {
        synchronized (this) {
            super.updateView(state);
        }
    }

    @Override
    public EventHandler<KeyEvent> changeLayerHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    final var state = ((CoDiControllerImpl) ConcurrentCoDiViewController.this.getController())
                            .outputLayerLeftShift();
                    ConcurrentCoDiViewController.this.updateView(state);
                } else if (event.getCode() == KeyCode.D) {
                    final var state = ((CoDiControllerImpl) ConcurrentCoDiViewController.this.getController())
                            .outputLayerRightShift();
                    ConcurrentCoDiViewController.this.updateView(state);
               }
            }
        };
    }

}
