package view.menu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controller.leaderboardhandler.LeaderboardHandlerImpl;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import model.ship.PlayerShip.PlayerScore;
import view.AdaptableResolution;
import view.Sound;
import view.display.ScreenUtilities;
import view.image.Loader;
import view.image.ViewComponentUtilities;

/**
 * Handle the UI of a menu that shows the score obtained by
 * the leaderboard file.
 */
public class LeaderboardUIController implements Initializable, AdaptableResolution {

    // X and Y positions of the tableView in percentage relative to the stage.
    private static final double LEADERBOARD_TABLE_X = 50;
    private static final double LEADERBOARD_TABLE_Y = 60;
    private static final double LEADERBOARD_TABLE_TEXT_SIZE = 40;
    private static final double LEADERBOARD_TABLE_CELL_SIZE = 100;
    private static final double LEADERBOARD_TABLE_OFFSET = 100;
    // X and Y positions of the background image in percentage relative to the stage.
    private static final double BACKGROUND_X = 50;
    private static final double BACKGROUND_Y = 50;
    // X and Y positions of the backButton image in percentage relative to the stage.
    private static final double BACK_X = 4;
    private static final double BACK_Y = 7;
    @FXML 
    private Pane panel;
    @FXML
    private Rectangle background;
    @FXML
    private ImageView back;
    @FXML
    private ImageView backgroundImage;
    @FXML
    private TableView<PlayerScore> leaderboard;
    @FXML
    private TableColumn<PlayerScore, String> playerName;
    @FXML
    private TableColumn<PlayerScore, Number> playerScore;
    @FXML
    private TableColumn<PlayerScore, Number> levelBeaten;

    /**
     * Initialize the controller after its root element has been completely processed.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.playerName.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlayerName()));
        this.playerScore.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getTotalScore()));
        this.levelBeaten.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLevelBeaten()));
        this.leaderboard.setItems(getItems());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        ViewComponentUtilities.resizeAndReposition(this.panel, this.background);
        this.leaderboard.setPrefWidth(this.leaderboard.getPrefWidth() * ScreenUtilities.getCurrentScaleFactor() + LEADERBOARD_TABLE_OFFSET * ScreenUtilities.getCurrentScaleFactor());
        this.leaderboard.setPrefHeight(this.leaderboard.getPrefHeight() * ScreenUtilities.getCurrentScaleFactor() + LEADERBOARD_TABLE_OFFSET * ScreenUtilities.getCurrentScaleFactor());
        this.leaderboard.setFixedCellSize(LEADERBOARD_TABLE_CELL_SIZE * ScreenUtilities.getCurrentScaleFactor());
        final StringBuilder textSizeProperty = new StringBuilder();
        textSizeProperty.append("-fx-font-size: ").append(LEADERBOARD_TABLE_TEXT_SIZE * ScreenUtilities.getCurrentScaleFactor()).append(';');
        this.leaderboard.getColumns().forEach(column -> {
            column.setReorderable(false);
            column.setStyle(textSizeProperty.toString());
            column.setPrefWidth(column.getPrefWidth() * ScreenUtilities.getCurrentScaleFactor());
        });
        this.leaderboard.setPlaceholder(new HBox());
        ViewComponentUtilities.reposition(this.leaderboard, LEADERBOARD_TABLE_X, LEADERBOARD_TABLE_Y);
        ViewComponentUtilities.resizeAndReposition(this.backgroundImage, BACKGROUND_X, BACKGROUND_Y);
        ViewComponentUtilities.resizeAndReposition(this.back, BACK_X, BACK_Y);
    }

    /**
     * Return to the main menu.
     * @throws IOException - if an error occurs during loading
     */
    @FXML
    private void backtoMainMenu() throws IOException {
        ((MainMenuUIController) Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
    }

    /**
     * Applies an effect when the cursor is on the back item.
     */
    @FXML
    private void onBackMouseEntered() {
        Sound.play("mouseOver");
    }

    /**
     * Retrieve saved scores. 
     * @return an observable list containing all saved scores
     */
    private ObservableList<PlayerScore> getItems() {
        final ObservableList<PlayerScore> items = FXCollections.observableArrayList();
        new LeaderboardHandlerImpl().getLeaderboard().forEach(e -> items.add(e));
        return items;
    }

}
