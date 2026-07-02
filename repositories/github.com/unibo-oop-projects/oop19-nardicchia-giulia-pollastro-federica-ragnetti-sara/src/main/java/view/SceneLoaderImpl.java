package view;

import java.io.IOException;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import view.controllers.FxController;

/**
 * Implementation of the {@link SceneLoader}.
 *
 */
public class SceneLoaderImpl implements SceneLoader {

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<FxController> loadScene(final String filePath) {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(filePath));
        try {
            final Parent root = loader.load(this.getClass().getResourceAsStream(filePath));
            final FxController controller = loader.<FxController>getController();
            controller.setRoot(root);
            return Optional.of(controller);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
