package view;

import controller.OverlayViewController;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;
import util.MenuVariablesUtils;
import javafx.scene.control.ButtonType;

/**
 * The view responsible of creating alerts and other things to be showed.
 */
public class OverlayView {

    private OverlayViewController controller;

    /**
     * @param cont the controller of this view
     */
    public void setController(final OverlayViewController cont) {
        this.controller = cont;
    }

    /**
     * The dialog to be called when the game is finished.
     * 
     * @param playerName the name of the winner
     */
    public void endGameDialog(final String playerName) {
        this.controller.setEndGameDialog(playerName);
        final Alert alert = new Alert(AlertType.NONE);
        final FlowPane pane = new FlowPane();
        final Text win = new Text(this.controller.getText());
        win.setFont(MenuVariablesUtils.MEDIUM_FONT);
        alert.setTitle("Congratulations!");
        pane.getChildren().add(win);
        pane.setPadding(new Insets(10));
        alert.getButtonTypes().add(ButtonType.FINISH);
        alert.getDialogPane().getChildren().add(pane);
        alert.showAndWait();
    }

}
