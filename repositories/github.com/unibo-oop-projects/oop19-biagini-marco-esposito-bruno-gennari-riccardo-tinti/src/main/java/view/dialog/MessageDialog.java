package view.dialog;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;

//package-private
final class MessageDialog extends AbstractDialog {

    private Alert build(final DialogType type, final String title, final String header, final String description) {
        final Alert alert = new Alert(type.getAlertType());
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setResizable(true);

        if (description != null) {
            final TextArea area = new TextArea(description);
            area.setWrapText(true);
            area.setEditable(false);
            alert.getDialogPane().setContent(area);
        }

        return alert;
    }

    protected Optional<String> launch(final DialogType type, final String title, final String header, final String description) {
        build(type, title, header, description).showAndWait();
        return Optional.empty();
    }

}
