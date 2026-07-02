package casim.ui.view.codi;

import casim.controller.automaton.AutomatonController;
import casim.controller.automaton.CoDiControllerImpl;
import casim.core.AppManager;
import casim.model.codi.cell.CoDiCellState;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.utils.statecolormapper.StateColorMapper;
import casim.ui.view.AutomatonViewController;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Implementation of CoDi's {@link AutomatonViewController}.
 */
public class CoDiViewController extends AutomatonViewController<CoDiCellState> implements CoDiLayerHandler {

    /**
     * Construct a new {@link CoDiViewController}.
     * 
     * @param appManager the {@link AppManager} holding the view.
     * @param controller the {@link AutomatonController} controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public CoDiViewController(final AppManager appManager, final AutomatonController<CoDiCellState> controller,
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
    public EventHandler<KeyEvent> changeLayerHandler() {
        return new EventHandler<KeyEvent>() {
            @Override
            public void handle(final KeyEvent event) {
                if (event.getCode().equals(KeyCode.A)) {
                    final var state = 
                            ((CoDiControllerImpl) CoDiViewController.this.getController()).outputLayerLeftShift();
                    CoDiViewController.this.updateView(state); 
                } else if (event.getCode().equals(KeyCode.D)) {
                    final var state = 
                            ((CoDiControllerImpl) CoDiViewController.this.getController()).outputLayerRightShift();
                    CoDiViewController.this.updateView(state);
               }
            }
        };
    }
}
