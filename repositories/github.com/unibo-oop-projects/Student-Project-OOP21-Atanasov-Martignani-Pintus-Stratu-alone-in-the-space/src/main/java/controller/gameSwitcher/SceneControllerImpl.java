package controller.gameSwitcher;

import java.io.IOException;

import controller.gameEngine.GameAnimation;
import controller.inputController.InputController;
import controller.inputController.InputControllerImpl;
import controller.ranking.Ranking;
import controller.ranking.RankingImpl;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 */
public class SceneControllerImpl implements SceneController {

    private Stage windowManager;
    private GameAnimation gameEngine;
    private InputController inputController;
    private Ranking ranking;

    public SceneControllerImpl(final Stage windowManager) throws IOException {
        this.windowManager = windowManager;
        this.ranking = new RankingImpl();
        this.inputController = new InputControllerImpl(new Scene(new Group()));
        this.windowManager.show();
        // soundManager
    }

    @Override
    public void switchToMainMenu() throws IOException {
        final Scene scene = this.getSceneFromFxml("fxml/MainMenu.fxml", new MainMenuController(this));
        this.inputController.changeScene(scene);
        this.windowManager.setScene(scene);
    }

    @Override
    public void switchToGame(String name) throws IOException {
        this.gameEngine = new GameAnimation(this);
        this.gameEngine.setPlayerName(name);
        final Scene scene = this.gameEngine.getGameMap().getGameContainer().getScene();
        this.windowManager.setScene(scene);
        this.inputController.changeScene(scene);
        this.gameEngine.start();
    }

    @Override
    public void switchToScores() throws IOException {
        final Scene scene = this.getSceneFromFxml("fxml/Scores.fxml", new ScoresController(this));
        this.inputController.changeScene(scene);
        this.windowManager.setScene(scene);
        this.windowManager.show();
    }

    @Override
    public void switchToNickname() throws IOException {
        final Scene scene = this.getSceneFromFxml("fxml/Nickname.fxml", new NicknameController(this));
        this.inputController.changeScene(scene);
        this.windowManager.setScene(scene);
    }

    @Override
    public void switchToEndMenu(int scores) throws IOException {
//        this.gameEngine.stop();
        final Scene scene = this.getSceneFromFxml("fxml/EndMenu.fxml", new EndGameController(this, scores));
        this.inputController.changeScene(scene);
        this.windowManager.setScene(scene);
    }

    @Override
    public void switchToControls() throws IOException {
        final Scene scene = this.getSceneFromFxml("fxml/Controls.fxml", new ControlsController(this));
        this.inputController.changeScene(scene);
        this.windowManager.setScene(scene);
    }

    @Override
    public void quit() {
        Platform.exit();
        System.exit(0);
    }

    private Scene getSceneFromFxml(final String path, final BasicFXMLController controller) throws IOException {
        // final FXMLLoader loader = new
        // FXMLLoader(getClass().getClassLoader().getResource(path));
        final FXMLLoader loader = new FXMLLoader(Thread.currentThread().getContextClassLoader().getResource(path));
        loader.setController(controller);
        return new Scene(loader.load());
    }

    @Override
    public Stage getStage() {
        return this.windowManager;
    }

    @Override
    public Ranking getRanking() {
        return this.ranking;
    }

    @Override
    public InputController getInputController() {
        return this.inputController;
    }
}
