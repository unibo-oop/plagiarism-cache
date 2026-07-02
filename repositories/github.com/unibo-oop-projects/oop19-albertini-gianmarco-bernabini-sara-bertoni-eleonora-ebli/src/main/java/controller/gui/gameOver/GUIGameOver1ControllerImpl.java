package controller.gui.gameOver;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.gui.game.GUIGameControllerImpl;
import controller.gui.settings.GUISettingsController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.components.GameTimerImpl;
import model.components.Score;
import model.ranking.Leaderboard;
import model.ranking.LeaderboardImpl;
import model.ranking.Player;
import model.ranking.PlayerImpl;
import view.SceneManager;
import view.Scenes;

public class GUIGameOver1ControllerImpl implements Initializable, GUIGameOver1Controller {

    @FXML
    private ImageView image;

    @FXML
    private Label gameOverLbl;

    @FXML
    private VBox vrtBox;

    @FXML
    private Button reStartBtn;

    @FXML
    private Button menuBtn;

    @FXML
    private Button leaderboardBtn;

    @FXML
    private Button saveBtn;

    @FXML
    private Label timerLbl;

    @FXML
    private Label scoreLbl;

    @FXML
    private Label nameLbl;

    @FXML
    private TextField textText;

    @FXML
    private Tooltip infoName;

    private String nickname;

    private Score score;

    private GameTimerImpl timer;

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void saveBtnOnClickHandler() throws IOException {
        this.savePlayer();
        saveBtn.setDisable(true);
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void reStartBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.GAME.getNewScene());
        final GUIGameControllerImpl controller = Scenes.GAME.getController();
        final GUISettingsController controllerSettings = Scenes.SETTINGS.getController();
        if (controllerSettings != null) {
            controller.getEngine().getWorld().setSettings(controllerSettings.isSoundSettings());
        }
        controller.firstTry();
        saveBtn.setDisable(false);
        textText.clear();
        textText.setEditable(true);
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void menuBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.MENU.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void leaderBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.LEADERBOARD.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        textText.setPromptText("Default name : player#");
        final GUIGameControllerImpl controller = Scenes.GAME.getController();
        this.score = controller.getEngine().getWorld().getScore();
        this.timer = controller.getEngine().getWorld().getTimer();
        scoreLbl.setText(this.score.toString());
        timerLbl.setText(this.timer.toString());
    }

    /**
     * this method save the player's nickname in the leaderboard
     * the nickname must be of 10 char maximum, and can't start with a space.
     */
    private void savePlayer() {
        if (textText.getText() != null && !textText.getText().isEmpty()) {
            final String temp = textText.getText();
            if (!Character.isWhitespace(temp.charAt(0))) {
                nickname = textText.getText();
                if (textText.getLength() > 10) {
                    nickname = nickname.substring(0, 10);
                    textText.setText(nickname);
                }
            } else {
                textText.setText("player#");
                nickname = textText.getText();
            }

            textText.setEditable(false);
        } else {
            textText.setText("player#");
            nickname = textText.getText();
            textText.setEditable(false);
        }
        final Player player = new PlayerImpl(nickname, this.timer, this.score.getGlobalScore());
        final Leaderboard leaderboard = new LeaderboardImpl();
        leaderboard.addPlayer(player);
        player.getPosition();
    }

}
