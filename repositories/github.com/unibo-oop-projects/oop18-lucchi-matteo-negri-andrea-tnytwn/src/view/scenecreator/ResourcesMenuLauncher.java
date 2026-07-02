package view.scenecreator;

import controller.scenes.ResourcesMenuController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
//import model.game.GameImpl;

/**
 * This class creates the view of the Resources Menu.
 */
public class ResourcesMenuLauncher {

    /**
     * @param stage
     *          the stage of the main menu
     */
    public ResourcesMenuLauncher(final Stage stage) {
        try {
            final FXMLLoader loader =  new FXMLLoader(getClass().getResource("/view/scenes/ResourcesMenu.fxml"));
            final ResourcesMenuController controller = new ResourcesMenuController();
            loader.setController(controller);
            final AnchorPane root = (AnchorPane) loader.load();
            final Scene scene = new Scene(root, 600, 380);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.setOnCloseRequest(e -> { 
                controller.stopRefreshTimer();
            });
        } catch (Exception e) {
                e.printStackTrace();
        }
    }
}
