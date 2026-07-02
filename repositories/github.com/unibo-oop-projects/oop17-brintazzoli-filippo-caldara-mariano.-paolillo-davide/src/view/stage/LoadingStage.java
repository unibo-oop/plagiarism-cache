package view.stage;

import java.io.IOException;

import controller.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.controller.LoadController;
import view.controller.ViewController;
import view.scene.SceneChanger;
import view.utility.ViewUtils;

/**
 * Concrete implementation of the {@link SceneChanger} interface. It manages the
 * loading level stage.
 */
public class LoadingStage implements SceneChanger {

    private LoadController loader;

    @Override
    public final void setStage(final double width, final double height, final Controller controller) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/javaFX/FadePlay.fxml"));
        final Parent root = loader.load();
        this.loader = loader.getController();
        this.loader.init(controller);
        final Scene scene = new Scene(root, width, height);
        scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
        final Stage stage = ViewUtils.getStage();
        stage.centerOnScreen();
        stage.hide();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public final ViewController getController() {
        return this.loader;
    }

}
