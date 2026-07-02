package view.navy_setup;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import controller.navy_setup.NavySetupUIController;
import controller.navy_setup.NavySetupUIControllerImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
/**
 * {@link NavySetupUI} using javafx framework.
 */
public final class NavySetupUIImpl implements NavySetupUI {

    /**
     * A grid that contains a set of {@link ComboBox}.
     */
    @FXML
    private GridPane gridPane;
    /**
     * Used to set the gird's size.
     */
    @FXML
    private ComboBox<Integer> gridSizeSelector;
    /**
     * The reset {@link Button}.
     */
    @FXML
    private Button buttonReset;
    /**
     * the confirmation {@link Button}.
     */
    @FXML
    private Button buttonConfirm;

    /**
     * The {@link ComboBox} list contained inside the grid pane.
     * This list is runtime generated and permits to select the number 
     *          of {@link Ship} for each size.
     */
    private final List<ComboBox<Integer>> comboBoxList = new ArrayList<>();

    /**
     * The available options for the combobox.
     */
    private ObservableList<Integer> comBoxOption;

    /**
     * The associated controller.
     */
    private final NavySetupUIController controller = new NavySetupUIControllerImpl(this);
    /**
     * built the graphic components with the informations that obtain in real time.
     */
    @FXML
    public void initialize() {
        comBoxOption = FXCollections.observableArrayList(IntStream.rangeClosed(0, 10).mapToObj(Integer::new).collect(Collectors.toList()));
        IntStream.range(0, controller.getShipSize()).forEach(currentSize -> {
            final ComboBox<Integer> cb = new ComboBox<>();
            cb.setOnAction(e -> controller.inputData(getSizeList(), getGridSize()));
            comboBoxList.add(cb);
            cb.getItems().setAll(comBoxOption);
            cb.getSelectionModel().select(0);
            GridPane.setMargin(cb,  new Insets(10, 0, 10, 0));
            gridPane.add(new Label("# ship of size " + (currentSize + 1)), 0, currentSize + 1);
            gridPane.add(cb, 1, currentSize + 1);
        });
        gridSizeSelector.setOnAction(e -> controller.inputData(getSizeList(), getGridSize()));
        buttonConfirm.setDisable(true);
    }
    /**
     * reset all fields.
     */
    @FXML
    public void reset() {
        comboBoxList.forEach(comboBox -> comboBox.getSelectionModel().select(0));
        disableConfirm();
    }
    /**
     * interaction with the bottom "confirm".
     */
    @FXML
    public void confirm() {
        controller.setDimension(getSizeList(), getGridSize());
    }

    private List<Integer> getSizeList() {
        return comboBoxList.stream()
                           .map(comboBox -> comboBox.getSelectionModel().getSelectedItem())
                           .collect(Collectors.toList());
    }

    private int getGridSize() {
        return gridSizeSelector.getSelectionModel().getSelectedItem();
    }

    @Override
    public void enableConfirm() {
        buttonConfirm.setDisable(false);
    }

    @Override
    public void disableConfirm() {
        buttonConfirm.setDisable(true);
    }
    @Override
    public void update() {
    }
}
