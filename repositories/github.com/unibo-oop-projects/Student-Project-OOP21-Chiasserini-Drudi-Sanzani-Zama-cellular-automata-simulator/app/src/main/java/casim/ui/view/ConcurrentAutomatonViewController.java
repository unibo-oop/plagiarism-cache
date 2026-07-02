package casim.ui.view;

import casim.controller.automaton.AutomatonController;
import casim.core.AppManager;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.utils.statecolormapper.StateColorMapper;
import casim.utils.AbstractWorker;
import casim.utils.Result;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

/**
 * A generic view controller for the automata that allows automatic simulation.
 * 
 * @param <T> the type of the cell state that the view has to represent.
 */
public class ConcurrentAutomatonViewController<T> extends AutomatonViewController<T> {
    private static final int STEP_DELAY_MS = 100;

    private final AbstractWorker automaticStepThread;

    /**
     * Build a new {@link ConcurrentAutomatonViewController}.
     *
     * @param appManager the {@link AppManager} holding the view.
     * @param controller the {@link AutomatonController} controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public ConcurrentAutomatonViewController(final AppManager appManager, final AutomatonController<T> controller,
            final CanvasGridImpl grid, final StateColorMapper<T> colorMapper) {
        super(appManager, controller, grid, colorMapper);
        this.automaticStepThread = this.getAutomaticStepWorker();
    }

    @Override
    protected void onExitBtnClick(final ActionEvent event) {
        synchronized (this) {
            this.automaticStepThread.stop();
            this.getContainer().setOnClose(() -> { });
            super.onExitBtnClick(event);
        }
    }

    @Override
    protected void onNextBtnClick(final ActionEvent event) {
        synchronized (this) {
            super.onNextBtnClick(event);
        }
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.automaticStepThread.start();
        this.getContainer().setOnClose(() -> this.automaticStepThread.stop());
        this.disableNextBtn();
    }

    @Override
    protected void onSizeChange(final ObservableValue<? extends Number> obs, final Number oldVal, final Number newVal) {
        synchronized (this) {
            super.onSizeChange(obs, oldVal, newVal);
        }
    }

    private AbstractWorker getAutomaticStepWorker() {
        return new AbstractWorker() {
            @Override
            public void run() {
                while (this.isRunning() && ConcurrentAutomatonViewController.this.getController().hasNext()) {
                    ConcurrentAutomatonViewController.this.onNextBtnClick(null);
                    Result.executeTask(() -> Thread.sleep(STEP_DELAY_MS));
                }
                this.setStopped(true);
            }
        };
    }
}
