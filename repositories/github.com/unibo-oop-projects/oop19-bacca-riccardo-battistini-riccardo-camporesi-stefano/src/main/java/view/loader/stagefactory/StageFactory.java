package view.loader.stagefactory;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageFactory {

    /**
     * {@inheritDoc}
     * @throws IOException if loader can't load new windows
     * @return an object used to load a settings dialog
     */
    public final StageLoader wizardLoader() throws IOException {
        return () -> {
            final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/choiceDialog.fxml"));
            final Stage choiceStage = new Stage();
            choiceStage.setTitle("Please select those parameters");
            choiceStage.setScene(new Scene(root, choiceStage.getHeight(), choiceStage.getWidth()));
            choiceStage.setResizable(false);
            choiceStage.show();
        };
    }

    /**
     * @return an object used to load an about dialog.
     * @throws IOException
     */
    public final StageLoader aboutLoader() throws IOException {
        return () -> {
            final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/aboutDialog.fxml"));
            final Stage aboutStage = new Stage();
            aboutStage.setTitle("About this project");
            aboutStage.setScene(new Scene(root, aboutStage.getHeight(), aboutStage.getWidth()));
            aboutStage.setResizable(false);
            aboutStage.show();
        };
    }

    /**
     * @return an object used to load an about dialog.
     * @throws IOException
     */
    public final StageLoader manualLoader() throws IOException {

        return () -> {
            final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layouts/user_manual.fxml"));
            final Stage aboutStage = new Stage();
            aboutStage.setTitle("User Manual");
            aboutStage.setScene(new Scene(root, aboutStage.getHeight(), aboutStage.getWidth()));
            aboutStage.setResizable(false);
            aboutStage.show();
        };
    }

}
