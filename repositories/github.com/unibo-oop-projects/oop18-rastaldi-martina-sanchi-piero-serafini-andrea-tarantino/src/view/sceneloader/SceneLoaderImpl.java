package view.sceneloader;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.Region;
import javafx.stage.Screen;
import javafx.stage.Stage;
import view.View;
import view.alert.AlertWindow;
import view.alert.AlertWindowImpl;
import view.scenecontroller.SceneController;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino.
 * A class that represents a scene loader.
 *
 */
public class SceneLoaderImpl implements SceneLoader {
    private final Stage stage;
    private final Stage secondaryStage;
    private final AlertWindow alert;
    private final View view;
    private boolean splitScreen;

    /**
     *
     * @param view
     *            view
     */
    public SceneLoaderImpl(final View view) {
        this.stage = view.getPrimaryStage();
        this.secondaryStage = view.getSecondaryStage();
        this.alert = new AlertWindowImpl("Exit", "Sei sicuro di voler uscire?", "yesNo");
        this.view = view;
        this.splitScreen = false;
    }

    @Override
    public final Stage getStage() {
        return this.stage;
    }

    @Override
    public final void load(final Scenes scene) throws IOException {
        final Region root;
        final FXMLLoader file = new FXMLLoader();

        file.setLocation((this.getClass().getResource(scene.getPath())));
        root = file.load();

        final SceneController controller = file.getController();
        controller.setSceneLoader(this.view.getSceneLoader());

        if ((scene != Scenes.RULEBOOK) && (scene != Scenes.RULEBOOKPAGE2)) {
            this.stage.setScene(new Scene(root));
            this.stage.setOnCloseRequest(e -> {
                if (this.alert.showAndWait().get().getButtonData() == ButtonData.YES) {
                    Runtime.getRuntime().exit(0);
                }
                e.consume();
                this.stage.sizeToScene();
            });
        } else {
            this.splitScreen = true;
            this.secondaryStage.setScene(new Scene(root));
            this.secondaryStage.setOnCloseRequest(e -> {
                this.splitScreen = false;
                this.secondaryStage.close();
            });
            this.secondaryStage.sizeToScene();
            this.secondaryStage.show();
        }

        this.updateStagePosition();
    }

    private void updateStagePosition() {
        if (this.splitScreen) {
            final Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
            this.stage.setX(primScreenBounds.getWidth() - this.stage.getWidth());
            this.stage.setY(0);
        }

    }

}
