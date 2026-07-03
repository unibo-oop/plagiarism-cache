package view.controller;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import util.Pair;
import view.ViewManagerImpl;
import view.util.ViewStaticUtilities;

/**
 * Controller of the OptionsScreen FXML file.
 */
public class OptionsSceneController extends AbstractControllerFXML {

    @FXML private BorderPane contents;
    @FXML private ComboBox<String> resolutionComboBox;
    @FXML private ComboBox<String> fpsComboBox;
    @FXML private CheckBox godModeCheckBox;

    private static final Pair<Integer, Integer> LOW_RESOLUTION = new Pair<>(800, 450);
    private static final Pair<Integer, Integer> MEDIUM_RESOLUTION = new Pair<>(1280, 720);
    private static final Pair<Integer, Integer> HIGH_RESOLUTION = new Pair<>(1600, 900);

    @FXML
    private void applyButtonPressed() {
        final String selectedResolution = resolutionComboBox.getValue();
        if (selectedResolution.equals("800x450")) {
            ViewStaticUtilities.setSelectedResolution(LOW_RESOLUTION);
        } else if (selectedResolution.equals("1280x720")) {
            ViewStaticUtilities.setSelectedResolution(MEDIUM_RESOLUTION);
        } else if (selectedResolution.equals("1600x900")) {
            ViewStaticUtilities.setSelectedResolution(HIGH_RESOLUTION);
        }

        ViewStaticUtilities.setSelectedFPS(Integer.parseInt(fpsComboBox.getSelectionModel().getSelectedItem()));
        ViewStaticUtilities.setGodMode(godModeCheckBox.isSelected());

        ViewManagerImpl.get().resizeScene();
        super.closeAnimation(() -> ViewManagerImpl.get().popState());
    }

    @FXML
    private void exitButtonPressed() {
        super.closeAnimation(() -> ViewManagerImpl.get().popState());
    }

    @FXML
    private void initialize() {
        resolutionComboBox.setValue(ViewStaticUtilities.getSelectedResolution().getX() + "x" + ViewStaticUtilities.getSelectedResolution().getY());
        fpsComboBox.setValue(Integer.toString(ViewStaticUtilities.getSelectedFPS()));
        godModeCheckBox.setSelected(ViewStaticUtilities.isGodModeSelected());
    }

    @Override
    public Region getRoot() {
        return this.contents;
    }
}
