package uicontrollers;

import java.io.IOException;

import controller.ControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

/**
 * Implementation of the view controller class dedicated to the game settings.
 */
public final class PrePickerController {

    @FXML
    private ChoiceBox<String> arenaList;

    @FXML
    private TextField teamSizeBox;

    @FXML
    private Button btnConfirm;

    @FXML
    private void confirmButtonAction() { // NOPMD
        ControllerImpl.getInstance().setTeamSize(Integer.parseInt(this.teamSizeBox.getText()));
        try {
            ControllerImpl.getInstance()
                    .chooseArenaMap(this.arenaList.getSelectionModel().getSelectedItem().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ControllerImpl.getInstance().loadHeroImages();
    }
}
