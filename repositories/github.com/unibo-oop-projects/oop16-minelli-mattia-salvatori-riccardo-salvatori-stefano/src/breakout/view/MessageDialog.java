package breakout.view;

import java.util.Optional;

import breakout.view.graphics.Fonts;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;

/**
 * A dialog that shows up when the user click on the menu button.
 */
public class MessageDialog {

    private static final Font TEXT_FONT = Fonts.ADVANCED_FONT.get(35);
    private static final Font BUTTON_FONT = Fonts.ADVANCED_FONT.get(20);

    private final Alert alert;

    /**
     * @param message
     *            the message to show
     * @param buttons
     *            the available buttons
     * @param width
     *            the width of the dialog box
     * @param height
     *            the height of the fialog box
     */
    public MessageDialog(final String message, final double width, final double height,
            final ButtonType... buttons) {
        this.alert = new Alert(AlertType.NONE, message, buttons);
        this.alert.setHeaderText(null);
        this.alert.setGraphic(null);
        this.alert.getDialogPane().setPrefWidth(width);
        this.alert.getDialogPane().setPrefHeight(height);
        this.alert.initStyle(StageStyle.UNDECORATED);
        this.alert.getDialogPane().getStylesheets().clear();
        this.alert.getDialogPane().getStylesheets().add("stylesheet.css");
        this.alert.getDialogPane().setId("grey_black_radial");
        this.alert.getDialogPane().getChildren().forEach(child -> {
            if (child instanceof Label) {
                ((Label) child).setFont(TEXT_FONT);
                ((Label) child).setTextFill(Color.WHITE);
                ((Label) child).setTextAlignment(TextAlignment.CENTER);
            } else if (child instanceof ButtonBar) {
                ((ButtonBar) child).getButtons().forEach(button -> ((Button) button).setFont(BUTTON_FONT));
            }
        });
    }

    /**
     * Shows the dialog and waits for the user response.
     * 
     * @return An Optional that contains the button pressed by the user
     */
    public Optional<ButtonType> showAndWait() {
        return this.alert.showAndWait();
    }

}
