package ballblast.view.scenecontroller;
/**
 * Sample Skeleton for 'Leaderboard.fxml' Controller Class
 */

import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import com.google.common.collect.Lists;
import ballblast.controller.Controller;
import ballblast.model.data.RecordData;
import ballblast.view.View;
import ballblast.view.scenes.GameScenes;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 * 
 * The SceneController for leaderboard scene.
 * 
 */
public class LeaderboardSceneController extends AbstractSubSceneController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="player1label"
    private Label player1label; // Value injected by FXMLLoader

    @FXML // fx:id="score1label"
    private Label score1label; // Value injected by FXMLLoader

    @FXML // fx:id="player2label"
    private Label player2label; // Value injected by FXMLLoader

    @FXML // fx:id="player3label"
    private Label player3label; // Value injected by FXMLLoader

    @FXML // fx:id="player4label"
    private Label player4label; // Value injected by FXMLLoader

    @FXML // fx:id="player5label"
    private Label player5label; // Value injected by FXMLLoader

    @FXML // fx:id="player6label"
    private Label player6label; // Value injected by FXMLLoader

    @FXML // fx:id="player7label"
    private Label player7label; // Value injected by FXMLLoader

    @FXML // fx:id="player8label"
    private Label player8label; // Value injected by FXMLLoader

    @FXML // fx:id="score2label"
    private Label score2label; // Value injected by FXMLLoader

    @FXML // fx:id="score3label"
    private Label score3label; // Value injected by FXMLLoader

    @FXML // fx:id="score4label"
    private Label score4label; // Value injected by FXMLLoader

    @FXML // fx:id="score5label"
    private Label score5label; // Value injected by FXMLLoader

    @FXML // fx:id="score6label"
    private Label score6label; // Value injected by FXMLLoader

    @FXML // fx:id="score7label"
    private Label score7label; // Value injected by FXMLLoader

    @FXML // fx:id="score8label"
    private Label score8label; // Value injected by FXMLLoader

    @FXML // fx:id="player9label"
    private Label player9label; // Value injected by FXMLLoader

    @FXML // fx:id="player10label"
    private Label player10label; // Value injected by FXMLLoader

    @FXML // fx:id="score9label"
    private Label score9label; // Value injected by FXMLLoader

    @FXML // fx:id="score10label"
    private Label score10label; // Value injected by FXMLLoader

    @FXML // fx:id="backToMenuBtn"
    private Button backToMenuBtn; // Value injected by FXMLLoader

    @Override
    public final void init(final Controller controller, final View view) {
        super.init(controller, view);
        final List<Label> scores;
        final List<Label> users;
        final List<RecordData> recordList;

        recordList = this.getController().getLeaderboard().getRecordList();
        users = Lists.newArrayList(player1label, player2label, player3label, player4label, player5label,
                player6label, player7label, player8label, player9label, player10label);
        scores = Lists.newArrayList(score1label, score2label, score3label, score4label, score5label, score6label,
                score7label, score8label, score9label, score10label);

        for (int i = 0; i < 10; i++) {
            users.get(i).setText(recordList.get(i).getName().toUpperCase(Locale.ENGLISH));
            scores.get(i).setText(String.valueOf((recordList).get(i).getScore()));
        }
    }

    @Override
    public final GameScenes getNextScene() {
        return GameScenes.MENU;
    }

    @Override
    protected final GameScenes getPreviousScene() {
        return GameScenes.MENU;
    }
}
