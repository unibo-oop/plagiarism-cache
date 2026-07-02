package view.scenecreator;

import controller.scenes.PauseMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utility.SystemPropertiesHelper;

/**
 * This class creates the view of the Pause Menu.
 */
public class PauseMenu {

    /**
     * Constructor of the class.
     * @param time
     *          time left to end the Game Day.
     */
    public PauseMenu(final long time) {
        try {
            final BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/scenes/Pause.fxml"));
            root.setPrefWidth((double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION);
            root.setPrefHeight((double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            final VBox buttons = (VBox) root.getChildren().get(0);
            buttons.setSpacing((double) SystemPropertiesHelper.DEFAULT_SPACING);
            final Scene scene = new Scene(root, (double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION / 4, (double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION / 4);
            final Stage stage = new Stage();
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            PauseMenuController.setRemainingTime(time);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
