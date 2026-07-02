package controller.gui.game;

import java.net.URL;
import java.util.ResourceBundle;
import controller.Input.KeyListenerController;
import controller.Input.KeyListenerControllerImpl;
import controller.core.GameEngineImpl;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.GameBoard;
import view.SceneManager;

/**
 * The controller related to the main.fxml GUI.
 *
 */
public final class GUIGameControllerImpl implements GUIGameController, Initializable {

    @FXML
    private ImageView img;

    @FXML
    private Label timerLbl;

    @FXML
    private Label scoreLbl;

    @FXML
    private VBox vrtBox;

    @FXML
    private BorderPane borderPane;

    private KeyListenerController keyList;
    private GameEngineImpl engine;

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEngineImpl getEngine() {
        return engine;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void firstTry() {
        final GameBoard gameboard = new GameBoard(vrtBox.getWidth(), vrtBox.getHeight());
        borderPane.setCenter(gameboard);
        this.engine.mainLoop(scoreLbl, timerLbl, gameboard);
        final Stage stage = (Stage) this.scoreLbl.getScene().getWindow();
        this.keyList.initialize(this.engine.getWorld(), stage.getScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void pauseBtnOnClickHandler() {
        this.engine.getWorld().getGameState().setPause(true);
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        SceneManager.setSceneBackground(img);
        this.engine = new GameEngineImpl();
        this.keyList = new KeyListenerControllerImpl();
    }
}
