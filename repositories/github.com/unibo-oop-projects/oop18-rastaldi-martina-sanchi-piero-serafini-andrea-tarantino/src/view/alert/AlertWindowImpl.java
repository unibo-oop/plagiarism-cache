package view.alert;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * Chiara Tarantino.
 * A class that represents an Alert Window.
 *
 */
public class AlertWindowImpl implements AlertWindow {

    private static final int ALERTWIDTH = 400;
    private static final int ALERTHEIGHT = 150;
    private final Alert alert;

    /**
     *
     * @param title
     *            The title displayed at the top of the window.
     * @param headerText
     *            The text displayed in the alert window.
     * @param type
     *            A string that identify if the alert contains yes/no buttons or
     *            just ok button.
     */
    public AlertWindowImpl(final String title, final String headerText, final String type) {
        final Stage stage;
        this.alert = type.equals("yesNo") ? new Alert(AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO)
                : new Alert(AlertType.WARNING, "", ButtonType.OK);
        this.alert.setHeaderText(headerText);
        this.alert.setTitle(title);
        this.alert.getDialogPane().setPrefWidth(ALERTWIDTH);
        this.alert.getDialogPane().setPrefHeight(ALERTHEIGHT);
        stage = (Stage) this.alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(this.getClass().getResourceAsStream(
                type.equals("yesNo") ? "/menuImages/alertIconQuestion.png" : "/menuImages/alertIconExclamation.png")));
        this.alert.getDialogPane().getStylesheets().add("style/stylesheet.css");
    }

    @Override
    public final Optional<ButtonType> showAndWait() {
        return this.alert.showAndWait();
    }
}
