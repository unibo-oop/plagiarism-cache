package casim.ui.view;

import casim.controller.automaton.AutomatonController;
import casim.core.AppManager;
import casim.ui.components.grid.CanvasGridImpl;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.AlertBuilderImpl;
import casim.ui.utils.statecolormapper.StateColorMapper;
import casim.utils.ViewUtils;
import casim.utils.grid.Grid2D;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * A generic view controller for an automaton.
 * 
 * @param <T> the type of the cell state that the view has to represent.
 */
public class AutomatonViewController<T> {
    private static final String NO_NEXT_STEP = "No next step available.";

    @FXML
    private VBox automatonView;

    @FXML
    private Label statsLbl;

    @FXML
    private Button nextBtn;

    @FXML
    private Button exitBtn;

    @FXML
    private AnchorPane automatonPane;

    private final StackPane root = new StackPane();
    private final CanvasGridImpl grid;
    private final AppManager appManager;
    private final StateColorMapper<T> colorMapper;
    private final AutomatonController<T> controller;

    /**
     * Build a new {@link AutomatonViewController}.
     *
     * @param appManager the {@link AppManager} of the application.
     * @param controller the {@link AutomatonController} controlling the view.
     * @param grid the {@link CanvasGridImpl} to be drawn.
     * @param colorMapper the {@link StateColorMapper} that translates cell states to colors.
     */
    public AutomatonViewController(final AppManager appManager, final AutomatonController<T> controller,
            final CanvasGridImpl grid, final StateColorMapper<T> colorMapper) {
        this.appManager = appManager;
        this.controller = controller;
        this.grid = grid;
        this.colorMapper = colorMapper;
        this.grid.setCells(this.controller.getGrid().map(this.colorMapper::toColor));
    }

    /**
     * Handle the "exit button" click.
     * 
     * @param event click event.
     */
    @FXML
    protected void onExitBtnClick(final ActionEvent event) {
        this.getContainer().popPage();
    }

    /**
     * Handle the "next button" click.
     *
     * @param event click event.
     */
    @FXML
    protected void onNextBtnClick(final ActionEvent event) {
        this.render();
        this.updateStats();
    }

    /**
     * Allows to disable next button, used in automatic view controllers.
     */
    protected void disableNextBtn() {
        this.nextBtn.setDisable(true);
    }

    /**
     * Initialize the view components.
     */
    @FXML
    protected void initialize() {
        ViewUtils.fitToAnchorPane(this.automatonView);
        this.automatonPane.getChildren().add(root);
        root.getChildren().add(this.grid);
        ViewUtils.fitToAnchorPane(root);
        this.automatonPane.widthProperty().addListener(this::onSizeChange);
        this.automatonPane.heightProperty().addListener(this::onSizeChange);
    }

    /**
     * Renders the new frame and updates the stats.
     */
    protected void render() {
        if (this.controller.hasNext()) {
            this.updateGrid();
        } else {
            new AlertBuilderImpl()
            .buildDefaultInfo(NO_NEXT_STEP, this.getContainer().getOwner())
            .show();
        }
    }

    private void updateGrid() {
        final var state = this.controller.next();
        this.setCellsAndDraw(state);
    }

    private void setCellsAndDraw(final Grid2D<T> state) {
        this.grid.setCells(state.map(this.colorMapper::toColor));
        Platform.runLater(() -> this.grid.draw()); 
    }

    /**
     * Update the simulation's stats.
     */
    private void updateStats() {
        final var s = this.controller.getStats();
        Platform.runLater(() -> this.statsLbl.setText(s.toString()));
    }

    /**
     * Set the {@link CanvasGridImpl}, draw it and update the stats.
     * 
     * @param state the grid of state to map to a {@link CanvasGridImpl}.
     */
    protected void updateView(final Grid2D<T> state) {
        this.setCellsAndDraw(state);
        this.updateStats();
    }

    /**
     * Get the {@link PageContainer} containing the view.
     * 
     * @return the {@link PageContainer}.
     */
    protected PageContainer getContainer() {
        return this.appManager.getContainer();
    }

    /**
     * Get the {@link AutomatonController} of the view.
     * 
     * @return the {@link AutomatonController}.
     */
    protected AutomatonController<T> getController() {
        return this.controller;
    }

    /**
     * Return the {@link Node} which contains the view.
     * 
     * @return the {@link Node} which contains the view.
     */
    protected Node getView() {
        return this.automatonView;
    }

    /**
     * Handler for the size change property of the view.
     * 
     * @param obs the value to observe.
     * @param oldVal the old value.
     * @param newVal the new value.
     */
    protected void onSizeChange(final ObservableValue<? extends Number> obs, final Number oldVal, final Number newVal) {
        this.grid.handleSizeChange(this.automatonPane.getWidth(), this.automatonPane.getHeight());
        final var sideX = this.grid.getCellSize() * this.grid.getColumns();
        this.root.setTranslateX((this.automatonPane.getWidth() - sideX) / 2);
    }
}
