package view.scenecreator;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utility.SystemPropertiesHelper;

/**
 * This class creates the view of the Main Menu.
 */
public class MainMenu {

    /**
     * @param stage
     *          the stage of the main menu
     */
    public MainMenu(final Stage stage) {
        try {
            final BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("/view/scenes/Menu.fxml"));
            root.setPrefWidth((double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION);
            root.setPrefHeight((double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            final VBox buttons = (VBox) root.getChildren().get(1);
            buttons.setSpacing((double) SystemPropertiesHelper.DEFAULT_SPACING);
            final Scene scene = new Scene(root, (double) SystemPropertiesHelper.PREFERRED_WIDTH_RESOLUTION, (double) SystemPropertiesHelper.PREFERRED_HEIGHT_RESOLUTION);
            scene.getStylesheets().add(getClass().getResource("/view/style/MainStyle.css").toExternalForm());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
