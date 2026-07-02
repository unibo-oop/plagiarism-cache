package view.select_player;

import java.util.Optional;

import controller.player_selection.PlayerSelectionUIControllerImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import view.PasswordInputDialog;

/**
 * Implementation of the UI of the selection of the player. 
 */
public final class SelectionPlayerUIImpl implements SelectionPlayerUI {
    private PlayerSelectionUIControllerImpl controller;
    /**
     * List of saved players.
     */
    @FXML private ListView<String> playerList;

    @Override
    @FXML
    public void initialize() {
        controller = new PlayerSelectionUIControllerImpl(this);
        update();
    }

    @Override
    public void update() {
        playerList.getItems().setAll(controller.getPlayerNameList());
        playerList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @Override
    @FXML
    public void playerAnonymous() {
        controller.setName("Anonymous");
        final String pin = createPasswordInputDialog("Anonymous");
        confirmPin(pin);
    }

    @Override
    @FXML
    public void addNewPlayer() {
        final TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add new player");
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setContentText("Please enter your nick name:");

        final Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            controller.addPlayer(result.get());
        }
    }

    @Override
    @FXML
    public void chosePlayer() {
        final String name = playerList.getSelectionModel().getSelectedItem();
        controller.setName(name);
        final String pin = createPasswordInputDialog(name);
        confirmPin(pin);
    }

    @Override
    public String createPasswordInputDialog(final String name) {
        return PasswordInputDialog.createPasswordInputDialog("Set a PIN", "Set a PIN for this match for the player " + name);
    }

    @Override
    public void errorMessage(final String message) {
        final Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(message);

        alert.showAndWait();
    }

    @Override
    public void reset() {
        update();
    }
    /**
     * Sets the pin into the controller.
     * @param pin to insert
     */
    private void confirmPin(final String pin) {
        if (!pin.equals("")) {
            controller.setPassword(pin);
            controller.confirmData();
        }
    }
}
