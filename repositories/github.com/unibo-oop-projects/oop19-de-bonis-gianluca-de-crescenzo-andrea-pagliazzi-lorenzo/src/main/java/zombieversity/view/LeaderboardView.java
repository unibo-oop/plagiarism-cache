package zombieversity.view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import zombieversity.controller.core.GameState;
import zombieversity.model.score.Score;

/**
 * Scene that will show the data retrieved from the leaderboard.
 */
public class LeaderboardView implements Initializable {

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<Score> tableviewLeaderboard;

    @FXML
    private TableColumn<Score, Integer> colPosition;

    @FXML
    private TableColumn<Score, String> colNickname;

    @FXML
    private TableColumn<Score, Integer> colKills;

    @FXML
    private TableColumn<Score, String> colDuration;

    @FXML
    private Label lblLeaderboard;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnExit;

    private final ObservableList<Score> lb = FXCollections.observableArrayList();

    /**
     * Set up the layout and the data in the table view based on the leaderboard.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.setLayout();
        this.colPosition.setCellValueFactory(new PropertyValueFactory<Score, Integer>("position"));
        this.colNickname.setCellValueFactory(new PropertyValueFactory<Score, String>("nickname"));
        this.colKills.setCellValueFactory(new PropertyValueFactory<Score, Integer>("kills"));
        this.colDuration.setCellValueFactory(new PropertyValueFactory<Score, String>("timePlayed"));

        this.tableviewLeaderboard.setItems(this.lb);

        this.btnPlay.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                GameState.state = GameState.GameStateEnum.GAME;
                GameState.init = true;
                GameState.change = true;
            }
        });

        this.btnExit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                GameState.close = true;
            }
        });

    }

    private void setLayout() {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.root.setPrefWidth(screenSize.getWidth() / 2);
        this.root.setPrefHeight(screenSize.getHeight() / 2);

        Image backgroundImage;
        backgroundImage = new Image(getClass().getResourceAsStream("/backgroundNoTitle.jpg"), this.root.getWidth(),
                this.root.getHeight(), false, true);

        final BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);

        root.setBackground(new Background(background));
    }

    /**
     * @param data
     *          The data that will be show in the table view.
     */
    public final void setUpData(final List<Score> data) {
        this.lb.clear();
        this.lb.addAll(data);
    }

    /**
     * @param width
     *          Set the preferred width of the scene.
     */
    public final void setWidth(final double width) {
        this.root.setPrefWidth(width);
    }

    /**
     * @param height
     *          Set the preferred height of the scene.
     */
    public final void setHeight(final double height) {
        this.root.setPrefHeight(height);
    }

}
