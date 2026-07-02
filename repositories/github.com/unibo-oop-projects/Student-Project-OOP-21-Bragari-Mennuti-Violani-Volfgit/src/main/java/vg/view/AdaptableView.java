package vg.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import vg.controller.Controller;
import vg.utils.DimensionUtils;
import vg.utils.NoSuchControllerException;
import vg.view.gameBoard.GameBoard;
import java.io.IOException;
import java.util.Optional;

/**
 * A view that resize and correctly puts elements on the screen
 * based on the resolution.
 * It keeps logic controller ({@link Controller}) and javafx scene ({@link Scene}).
 * @param <T> ViewController type
 */
public class AdaptableView<T> implements View<T> {
    private Scene scene;
    private Optional<SceneController> sceneController;
    /**
     * JavaFX controller loaded from file associated to file fxml.
     */
    private final T viewController;

    protected AdaptableView(final String resName) {
        FXMLLoader loader = new FXMLLoader(GameBoard.class.getResource(resName));
        try {
            this.scene = new Scene(loader.load(),
                    DimensionUtils.DEFAULT_WIDTH,
                    DimensionUtils.DEFAULT_HEIGHT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.viewController = loader.getController();
    }

    @Override
    public void setSceneController(final SceneController sceneController) {
        this.sceneController = Optional.of(sceneController);
    }

    @Override
    public SceneController getSceneController() throws NoSuchControllerException {
        if (this.sceneController.isPresent()) {
            return this.sceneController.get();
        } else {
            throw new NoSuchControllerException();
        }
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public T getViewController() {
        if (this.viewController != null) {
            return this.viewController;
        } else {
            throw new NoSuchControllerException();
        }
    }

}
