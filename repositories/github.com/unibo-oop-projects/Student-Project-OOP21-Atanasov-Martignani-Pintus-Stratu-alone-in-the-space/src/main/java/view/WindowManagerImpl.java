package view;

import controller.gameSwitcher.SceneController;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
*
*/
public class WindowManagerImpl implements WindowManager {

    private final Stage mainStage;

    /**
     * Constructor.
     * 
     * @param sceneController
     */
    public WindowManagerImpl(final SceneController sceneController) {
        this.mainStage = sceneController.getStage();
        this.mainStage.setResizable(false);
    }

    @Override
    public void addGameMap(final GameMap gameMap) {
        final Scene scene = new Scene(new VBox(gameMap.getGameContainer()));
        gameMap.setScene(scene);
        gameMap.setStageReference(this.getStage());
    }

    @Override
    public Stage getStage() {

        return this.mainStage;
    }
}
