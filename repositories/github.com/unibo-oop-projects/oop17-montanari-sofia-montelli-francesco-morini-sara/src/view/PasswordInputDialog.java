package view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 * This class permits to show a PIN input dialog that return the read string.
 */
public final class PasswordInputDialog {

    private PasswordInputDialog() {
    }
 
    /**
     * Create the input dialog and wait for a result.
     * @param title is the title
     * @param text is the body
     * @return the provided input
     */
    public static String createPasswordInputDialog(final String title, final String text) {
        /**
         * Create the custom dialog.
         */
        final Dialog<String> dialog = new Dialog<>();
        final GridPane grid = new GridPane();
        final StackPane stackPane = new StackPane();
        final PasswordField password = new PasswordField();
        final TextField textField = new TextField();
        final CheckBox checkBox = new CheckBox("Show password");
        final ButtonType okButtonType = new ButtonType("OK", ButtonData.OK_DONE);


        dialog.setTitle(title);
        dialog.setHeaderText(text);

        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);
        final Node okButton = dialog.getDialogPane().lookupButton(okButtonType);
        okButton.setDisable(true);
        final MyChangeListener changeListener = new MyChangeListener(password, textField, okButton);


        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10, 100, 10, 10));

        password.setPromptText("PIN");

        password.textProperty().addListener(changeListener);
        textField.textProperty().addListener(changeListener);

        checkBox.setOnAction(tick -> stackPane.getChildren().get(stackPane.getChildren().size() - 1).toBack());

        grid.add(new Label("Please enter your PIN (Max 4 numbers):"), 0, 0);
        grid.add(stackPane, 1, 0);
        stackPane.getChildren().add(textField);
        stackPane.getChildren().add(password);
        grid.add(checkBox, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.showAndWait();
        return password.getText();
    }
}

class MyChangeListener implements ChangeListener<String> {

    private final PasswordField passwordField;
    private final TextField textField;
    private final Node okButton;
   /**
    * @param passwordField
    * @param textField
    * @param okButton
    */
    MyChangeListener(final PasswordField passwordField, final TextField textField, final Node okButton) {
        this.passwordField = passwordField;
        this.textField = textField;
        this.okButton = okButton;
    }

    public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
        textField.setText(isNumber(newValue) && newValue.length() <= 4 || newValue.length() == 0 ? newValue : oldValue);
        passwordField.setText(textField.getText());
        if (textField.getText().length() == 0) {
            okButton.setDisable(true);
        } else {
            okButton.setDisable(false);
        }
    }

    private boolean isNumber(final String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        } 
    }
}
