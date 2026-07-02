package casim.ui.components.menu.automaton.config;

import casim.core.AppManager;
import casim.model.Automata;
import casim.model.codi.CoDiConfig;
import casim.ui.components.page.PageContainer;
import casim.ui.utils.AlertBuilderImpl;
import casim.utils.Result;
import casim.utils.ViewUtils;
import casim.utils.automaton.config.BaseConfig;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller class for the fxml AutomatonConfigMenu.
 */
public class AutomatonConfigController {

    /**
     * Wrong menu error message.
     */
    protected static final String WRONG_MENU = "Wrong Configuration Menu.";
    private static final String NO_PREVIOUS_PAGE = "Can't go back.";
    private static final String NO_MODES_SET = "Please select a run mode";
    private static final String WRONG_SIZE = "Insert a valid integer number";
    private static final String AUTOMATIC = "Automatic";
    private static final String MANUAL = "Manual";
    private static final String CODI_SIZE = "To run codi and have good performance select a size <= 60";

    @FXML
    private VBox configView;

    @FXML
    private TextField sizeField;

    @FXML
    private ChoiceBox<String> modeSelector;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private HBox extension;

    private final AppManager appManager;
    private final Automata automata;

    /**
     * Construct a new {@link AutomatonConfigController}.
     * 
     * @param appManager the application's manager.
     * @param automata the automata.
     */
    public AutomatonConfigController(final AppManager appManager, final Automata automata) {
        this.appManager = appManager;
        this.automata = automata;
    }

    /**
     * Get the {@link PageContainer} where the menu is.
     * 
     * @return the {@link PageContainer} holding the menu.
     */
    public PageContainer getContainer() {
        return this.appManager.getContainer();
    }

    /**
     * Initialize the controller.
     */
    @FXML
    protected void initialize() {
        ViewUtils.fitToAnchorPane(this.configView);
        final ObservableList<String> names = FXCollections.observableArrayList(AUTOMATIC, MANUAL);
        this.modeSelector.setItems(names);
        if (this.automata.equals(Automata.CODI)) {
            new AlertBuilderImpl().buildDefaultInfo(CODI_SIZE, this.getContainer().getOwner())
            .showAndWait();
        }
    }

    /**
     * Add a {@link Node} to the view.
     * Permits (also to the subclasses) to add a component to the view.
     * 
     * @param node the component to add
     */
    protected void addExtension(final Node node) {
        this.extension.getChildren().add(node);
    }

    /**
     * Return the automata.
     * 
     * @return the automata.
     */
    protected Automata getAutomata() {
        return this.automata;
    }

    /**
     * Return the size on the input text field.
     * 
     * @return the size as string.
     */
    protected String getSizeText() {
        return this.sizeField.getText();
    }

    /**
     * Returns whether the automatic mode is set or not.
     * 
     * @return true if the automatic mode is set, false otherwise.
     */
    protected boolean isAutomatic() {
        return AUTOMATIC.equals(this.modeSelector.getValue());
    }

    /**
     * Returns the config object of the automaton.
     * 
     * @return the config object.
     */
    protected BaseConfig getConfig() {
        final var isAutomatic = this.isAutomatic();
        final int size = Integer.parseInt(this.getSizeText());

        switch (this.getAutomata()) {
            case CODI:
                return (BaseConfig) new CoDiConfig(size, size, size, isAutomatic);
            case RULE110:
            case WATOR:
                return new BaseConfig(size, size, isAutomatic);
            default:
                throw new UnsupportedOperationException(WRONG_MENU);
        }
    }

    @FXML
    private void onBackBtnClick(final ActionEvent event) { // NOPMD: called by JavaFX
        final var res = this.getContainer().popPage();
        if (res.isError()) {
            new AlertBuilderImpl().buildDefaultError(NO_PREVIOUS_PAGE, this.getContainer().getOwner());
        }
    }

    @FXML
    private void onNextBtnClick(final ActionEvent event) { // NOPMD: called by JavaFX
        if (this.checkInput()) {
            final var config = this.getConfig();
            final var res = this.appManager.showSimulation(this.getAutomata(), config);
            if (res.isError()) {
                new AlertBuilderImpl().buildDefaultError(
                    "Error starting the simulation.", this.getContainer().getOwner());
            }
        }
    }

    private boolean checkInput() {
        final var alertBuilder = new AlertBuilderImpl();
        final var size = Result.executeSupplier(() -> Integer.parseInt(this.sizeField.getText()));
        if (size.isError()) {
            alertBuilder.buildDefaultError(WRONG_SIZE, this.getContainer().getOwner())
                .showAndWait();
            return false;
        }
        if (this.modeSelector.getSelectionModel().isEmpty()) {
            alertBuilder.buildDefaultError(NO_MODES_SET, this.getContainer().getOwner())
                .showAndWait();
            return false;
        }
        return true;
    }

}
