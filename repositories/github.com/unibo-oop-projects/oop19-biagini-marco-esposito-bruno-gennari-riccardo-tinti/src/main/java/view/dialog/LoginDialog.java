package view.dialog;

import java.util.Optional;

import javafx.geometry.Insets;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;

//package-private
final class LoginDialog extends AbstractDialog {

    private Dialog<String> build(final DialogType type, final String title, final String header, final String description) {

        final Dialog<String> dialog = new Dialog<>();
        dialog.setTitle(title);
        dialog.setHeaderText(header);

        final ButtonType loginButtonType = new ButtonType("OK", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        final PasswordField password = new PasswordField();
        password.setPromptText("your password here...");

        dialog.getDialogPane().setContent(password);
        dialog.getDialogPane().setPadding(new Insets(30, 30, 30, 30));

        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == loginButtonType) {
                return password.getText();
            }
            return null;
        });

        return dialog;
    }

    protected Optional<String> launch(final DialogType type, final String title, final String header, final String description) {
        return build(type, title, header, description).showAndWait();
    }

}
