package view.scene;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This class is used to switch Scenes on the active Stage.
 */
public final class SceneLoaderImpl implements SceneLoader {

    private final LayoutLoader layoutLoader = new LayoutLoaderFXML();
    private final Stage stage;
 
    /**
     * @param stage - the active stage of the application.
     */
    public SceneLoaderImpl(final Stage stage) {
        this.stage = stage;
    }

    /**
     * This method switches the active Scene to the one passed as parameter.
     * @param nextScene - the name of the Scene you want to load.
     */
    public void switchScene(final SceneName nextScene) {
        final String fileName = nextScene.getLayoutName();
        final String fileExtension = ".fxml";
        stage.setScene(new Scene(layoutLoader.load(fileName + fileExtension)));
    }

}