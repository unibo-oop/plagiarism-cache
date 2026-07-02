package view;

import java.io.IOException;
import java.util.List;
import com.google.common.base.Optional;
import controller.SudokuGameHandler;
import controller.SudokuGameHandlerImpl;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Pair;
import utilities.Scenes;

/**
 * 
 * The implementation of View.
 *
 */

public class ViewImpl implements View {

    private static final String TITLE = "SuDoKu";
    private final Stage stage;
    private final SudokuGameHandler gameHandler;
    private GameController gameController;
    private Scenes actualScene;

    /**
     * 
     * @param stage of the view
     */
    public ViewImpl(final Stage stage) {
        this.stage = stage;
        this.gameHandler = new SudokuGameHandlerImpl(this);
    }

    @Override
    public final void init() {
        this.setScene(Scenes.HOME);
        this.actualScene = Scenes.HOME;
        this.stage.getIcons().add(new Image(ClassLoader.getSystemResource("images/light/sudoku_icon.png").toExternalForm()));
        this.stage.setTitle(TITLE);
        this.stage.show();
        this.stage.setMinWidth(stage.getWidth());
        this.stage.setMinHeight(stage.getHeight());
    }

    @Override
    public final void setScene(final Scenes scene) {
        try {
            final FXMLLoader root = scene.getLoader();
            final Scene newScene = new Scene(root.load());
            if (scene.equals(Scenes.GAME)) {
                this.gameController = root.getController();
                this.gameController.setSudokuGameHandler(this.gameHandler);
                this.gameController.setView(this);
            } else {
                final SceneController controller = root.getController();
                controller.setSudokuGameHandler(this.gameHandler);
                controller.setView(this);
            }
            newScene.getStylesheets().add(ClassLoader.getSystemResource(this.gameHandler.getSettings().getTheme().getCss(scene)).toExternalForm());
            this.stage.setScene(newScene);
            this.actualScene = scene;
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public final void boardUpdate(final List<Pair<Optional<Integer>, Boolean>> list) {
        if (this.actualScene.equals(Scenes.GAME)) {
            this.gameController.viewUpdate(list);
        }
    }
}
