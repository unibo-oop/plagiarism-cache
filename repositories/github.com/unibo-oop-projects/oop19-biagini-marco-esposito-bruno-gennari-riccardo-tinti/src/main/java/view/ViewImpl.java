package view;

import java.util.Optional;
import javafx.stage.Stage;
import view.dialog.DialogLauncher;
import view.dialog.DialogType;
import view.scene.SceneLoader;
import view.scene.SceneLoaderImpl;
import view.scene.SceneName;

/**
 * Concrete implementation of tha application's view.
 */
public final class ViewImpl implements View {

    private final SceneLoader sceneLoader;

    /**
     * the constructor of this class.
     * @param stage - the application's stage.
     */
    public ViewImpl(final Stage stage) {
        sceneLoader = new SceneLoaderImpl(stage);
        loadScene(SceneName.MAIN);
        stage.setTitle("Battleships");
        stage.show();
    }

    @Override
    public void loadScene(final SceneName name) {
        sceneLoader.switchScene(name);
    }

    @Override
    public Optional<String> launchDialog(final DialogType type, final String title, final String header, final String description) {
        return DialogLauncher.launchDialog(type, title, header, description);
    }

}
