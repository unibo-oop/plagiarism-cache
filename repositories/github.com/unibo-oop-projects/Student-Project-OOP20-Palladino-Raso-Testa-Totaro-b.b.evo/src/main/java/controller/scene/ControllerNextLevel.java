package controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import controller.game.GameLoop;
import controller.utilities.GUILayout;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import model.leaderboard.Player;
import model.mapeditor.Level;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalStyle;
import view.PersonalViews;

/**
 * Manage the NextLevel scene.
 * Display when finish a standard level
 */
public class ControllerNextLevel implements Initializable, GUILayout {

    @FXML
    private SplitPane mainPanel;

    @FXML
    private Label lblLevel;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblScore;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnNext;

    /**
     * 
     * Initialize the window with default settings adapted to the monitor resolution.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.mainPanel.setMinWidth(ScreenUtilities.SCREEN_WIDTH);
        this.mainPanel.setMaxWidth(ScreenUtilities.SCREEN_WIDTH);
        this.mainPanel.setMinHeight(ScreenUtilities.SCREEN_HEIGHT);
        this.mainPanel.setMaxHeight(ScreenUtilities.SCREEN_HEIGHT);

        this.backToMenu();
    }

    /**
     * 
     * @param event when click the button menu the user return to the Main Menu
     */
    @FXML
    private void backToMenu() {
      //MenuButton return to menu
        this.btnMenu.setOnAction(event -> FXMLMenuController.switchScene((Stage) this.mainPanel.getScene().getWindow(), PersonalViews.SCENE_MAIN_MENU, PersonalStyle.DEFAULT_STYLE, 
                ScreenUtilities.SCREEN_WIDTH, ScreenUtilities.SCREEN_HEIGHT, true));
    }

    /**
     * 
     * Start a new gameLoop thread with the next level set before in the gameLoop.
     */
    @FXML
    public void goToNextLevel() {

        final Scene scene = btnNext.getScene();
        final Thread engine = new Thread(new GameLoop(scene));
        engine.setDaemon(true);
        engine.start();
    }

    /**
     * @param level the current level to display.
     * @param player to retrieve the information about score and lives.
     */
    public void update(final Level level, final Player player) {
        this.lblLevel.setText(level.getLevelName());
        this.lblLives.setText("YOUR LIVES: " + player.getLife());
        this.lblScore.setText("YOUR SCORE: " + player.getScore());
    }
}
