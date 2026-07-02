package view;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import controller.SudokuGameHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import utilities.Difficulty;
import utilities.Scenes;

/**
 * 
 * Controller for HomeScene.
 *
 */

public class HomeControllerImpl implements SceneController {

    private View view;
    private SudokuGameHandler handler;
    private final ObservableList<Difficulty> options = FXCollections.observableArrayList(Stream.of(Difficulty.values())
                                                                                         .collect(Collectors.toList()));

    @FXML
    private ComboBox<Difficulty> cmbBox;

    @FXML
    private Button resumeGameBtn;

    @FXML
    public final void initialize() {
        cmbBox.setItems(options);
    }

    @FXML
    public final void newGameBtn() throws IOException {
       if (cmbBox.getSelectionModel().isEmpty()) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Choose difficulty");
            alert.showAndWait();
        } else {
            handler.newGame(cmbBox.getSelectionModel().getSelectedItem());
        }
    }


    @FXML
    public final void resumeGameBtn() throws IOException {
        handler.resumeGame();
    }

    @FXML
    public final void settingsBtn() throws IOException {
        view.setScene(Scenes.SETTINGS);
    }

    @Override
    public final void setSudokuGameHandler(final SudokuGameHandler handler) {
        this.handler = handler;
        resumeGameBtn.setDisable(!handler.previousGameExist());

    }

    @Override
    public final void setView(final View view) {
        this.view = view;
    }
}
