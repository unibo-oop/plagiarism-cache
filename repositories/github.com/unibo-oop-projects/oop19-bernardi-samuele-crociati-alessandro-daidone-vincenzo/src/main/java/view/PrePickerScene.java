package view;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;

/**
 * Implementation of the scene dedicated to the match settings.
 */
public class PrePickerScene extends Scene {
    /**
     * 
     * @param root
     *            the parent used to load the window
     * @param arenaNames
     *            the list of the names of the arenas
     */
    public PrePickerScene(final Parent root, final List<String> arenaNames) {
        super(root);
        final Scene prePicker = root.getScene();
        /*
         * We are specifically searching for a ChoiceBox<String> so the warning can be
         * suppressed
         */
        @SuppressWarnings("unchecked")
        final ChoiceBox<String> arenaBox = (ChoiceBox<String>) prePicker.lookup("#arenaList");
        arenaBox.setItems(FXCollections.observableArrayList(arenaNames));
        arenaBox.setValue(arenaBox.getItems().get(0));
    }
}
