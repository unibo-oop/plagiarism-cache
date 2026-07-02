package view.scene;

import java.io.PrintWriter;
import java.io.StringWriter;
import application.Battleships;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.dialog.DialogType;

//package private
final class LayoutLoaderFXML implements LayoutLoader {

    @Override
    public Parent load(final String resourcePath) {
        Parent res = null;
        try {
            res = FXMLLoader.load(ClassLoader.getSystemResource(resourcePath));
        } catch (Exception e) {
            Battleships.getController().launchDialog(DialogType.ERROR, "An Exception has occurred",
                    "Application encountered a critical error while reading files from disk", getStringFromStackTrace(e));
            throw new IllegalStateException();
        }
        return res;
    }

    private String getStringFromStackTrace(final Exception e) {
        final StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

}
