package controller.gui.leaderboard;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import model.ranking.Leaderboard;
import model.ranking.LeaderboardImpl;
import model.ranking.Player;
import view.SceneManager;
import view.Scenes;

/**
 * The controller related to the main.fxml GUI.
 *
 */
public final class GUILeaderboardControllerImpl implements GUILeaderboardController, Initializable {

    @FXML
    private ImageView img;

    @FXML
    private ScrollPane leaderboardPane;

    @FXML
    private VBox infoScoreDialog;

    @FXML
    private Button infoBtn;

    @FXML
    private Tooltip tooltip;

    @FXML
    private TableView<Player> leaderboardTable;

    @FXML
    private TableColumn<Player, Integer> positionClmn;

    @FXML
    private TableColumn<Player, String> nameClmn;

    @FXML
    private TableColumn<Player, String> timeClmn;

    @FXML
    private TableColumn<Player, Integer> scoreClmn;

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void backBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.MENU.getNewScene());
    }

    /**
     * {@inheritDoc}
     */
    @FXML
    @Override
    public void infoBtnOnClickHandler() throws IOException {
        if (leaderboardPane.isVisible()) {
            leaderboardPane.setVisible(false);
            infoScoreDialog.setVisible(true);
            infoBtn.setText("Back to leaderboard");
            tooltip.setText("Click me if you wanna return to the leaderboard ;)");
        } else {
            leaderboardPane.setVisible(true);
            infoScoreDialog.setVisible(false);
            infoBtn.setText("Info");
            tooltip.setText("Click me if you wanna get a higher score ;)");
        }
    }

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        SceneManager.setSceneBackground(img);
        positionClmn.setCellValueFactory(new PropertyValueFactory<>("position"));
        nameClmn.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        timeClmn.setCellValueFactory(new PropertyValueFactory<>("gameTimer"));
        scoreClmn.setCellValueFactory(new PropertyValueFactory<>("finalScore"));
        leaderboardTable.setItems(this.getPlayers());
    }

    private ObservableList<Player> getPlayers() {
        final ObservableList<Player> playersList = FXCollections.observableArrayList();
        final Leaderboard leaderboard = new LeaderboardImpl();
        playersList.addAll(leaderboard.getSortedPlayersList());
        return playersList;
    }
}
