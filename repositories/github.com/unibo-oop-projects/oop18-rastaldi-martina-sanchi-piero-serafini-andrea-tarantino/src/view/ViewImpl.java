package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.scenecontroller.SceneController;
import view.scenecontroller.SceneControllerImpl;
import view.sceneloader.SceneLoader;
import view.sceneloader.SceneLoaderImpl;
import view.utilities.Scenes;

/**
 *
 * Chiara Tarantino.
 *
 */
public class ViewImpl extends Application implements View {
    private Stage primaryStage;
    private SceneLoader sceneLoader;
    private final Stage secondaryStage;

    /**
     * Class constructor.
     */
    public ViewImpl() {
        super();
        this.secondaryStage = new Stage();
        this.secondaryStage.setResizable(false);
        this.secondaryStage.setTitle("RULEBOOK");
        this.secondaryStage.getIcons()
                .add(new Image(this.getClass().getResourceAsStream("/menuImages/initialMenuWallpaper.jpeg")));
    }

    @Override
    public final Stage getPrimaryStage() {
        return this.primaryStage;
    }

    @Override
    public final SceneLoader getSceneLoader() {
        return this.sceneLoader;
    }

    @Override
    public final Stage getSecondaryStage() {
        return this.secondaryStage;
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        SceneController sceneController;

        this.primaryStage = primaryStage;
        this.sceneLoader = new SceneLoaderImpl(this);
        sceneController = new SceneControllerImpl();
        sceneController.setSceneLoader(this.sceneLoader);

        this.secondaryStage.setX(0);
        this.secondaryStage.setY(0);

        this.primaryStage.setTitle("MINOTAURUS");
        this.primaryStage.setResizable(false);
        this.primaryStage.getIcons()
                .add(new Image(this.getClass().getResourceAsStream("/menuImages/initialMenuWallpaper.jpeg")));
        this.primaryStage.centerOnScreen();

        this.sceneLoader.load(Scenes.STARTMENU);
        this.primaryStage.sizeToScene();
        this.primaryStage.show();
    }
}
