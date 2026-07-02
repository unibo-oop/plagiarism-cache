package view.scene;

import java.net.URL;
import java.util.Collections;
import java.util.List;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import model.player.Player;

/**
 *
 * @author Andrea Manoni
 *
 */
public class VictoryScreenController extends AbstractController {

    private static final String PANETOT = "paneTot";
    private SceneMainController mainController;
    private final ObservableList<Player> playersList;
    private Button btnMenu;
    private ImageView menu;
    private URL imgURL;
    @FXML
    private TableView<Player> tblViewScore;
    @FXML
    private TableColumn<Player, String> nameColumn;
    @FXML
    private TableColumn<Player, Integer> killColumn;
    @FXML
    private TableColumn<Player, Integer> scoreColumn;
    @FXML
    private TableColumn<Player, Integer> rankingColumn;
    /* DIALOG */
    @FXML
    private Pane paneDialog;

    @FXML
    private Button btnYes;
    @FXML
    private Button btnNo;
    @FXML
    private ImageView yesButton;
    @FXML
    private ImageView noButton;
    @FXML
    private Rectangle rectExitScreen;
    /* END DIALOG */

    @Override
    public final void setMainApp(final SceneMainController mainController) {
        this.mainController = mainController;
        btnMenu = (Button) getNodeNested(mainController, "btnMenu", PANETOT);
        menu = (ImageView) getNodeNested(mainController, "Menu", PANETOT);
        btnMenu.setLayoutY(mainController.getResolution().getY() - btnMenu.getPrefHeight() - 10);
        menu.setLayoutY(btnMenu.getLayoutY());
        btnMenu.setLayoutX(menu.getLayoutX());
        setListener();
        setImages();
        setDialog();
        initialize();
    }

    private void setListener() {

        btnMenu.setOnAction(e -> mainController.sceneMenu());
        btnMenu.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = VictoryScreenController.class.getResource("/victory_back_on.png");
            menu.setImage(new Image(imgURL.toString()));
        });
        btnMenu.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = VictoryScreenController.class.getResource("/victory_back_off.png");
            menu.setImage(new Image(imgURL.toString()));
        });
        mainController.getPrimaryStage().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if (e.getCode().equals(KeyCode.ESCAPE)) {
                if (paneDialog.isVisible()) {
                    closeDialog();
                    e.consume();
                } else {
                    openDialog();
                    e.consume();
                }
            }
        });
        mainController.getPrimaryStage().setOnCloseRequest(e -> {
            openDialog();
            e.consume();
        });
    }

    /**
     * Constructor with playerList as input.
     *
     * @param playersList
     *            playerList
     */
    public VictoryScreenController(final List<Player> playersList) {
        this.playersList = FXCollections.observableArrayList(playersList);
    }

    private void setImages() {
        imgURL = VictoryScreenController.class.getResource("/victory_back_off.png");
        menu.setImage(new Image(imgURL.toString()));
        imgURL = VictoryScreenController.class.getResource("/victory_first.png");
        ((ImageView) getNodeNested(mainController, "posto1", PANETOT)).setImage(new Image(imgURL.toString()));
        imgURL = VictoryScreenController.class.getResource("/victory_second.png");
        ((ImageView) getNodeNested(mainController, "posto2", PANETOT)).setImage(new Image(imgURL.toString()));
        imgURL = VictoryScreenController.class.getResource("/victory_third.png");
        ((ImageView) getNodeNested(mainController, "posto3", PANETOT)).setImage(new Image(imgURL.toString()));
    }

    /* DIALOG */
    /**
     * Describes the action of pressing the button "Yes".
     */
    @FXML
    public void actionYesDialog() {
        mainController.closePrimaryStage();
    }

    /**
     * Describes the action of pressing the button "No".
     */
    @FXML
    public void actionNoDialog() {
        paneDialog.setVisible(false);
        rectExitScreen.setVisible(false);
    }

    private void setImagesDialog() {
        imgURL = VictoryScreenController.class.getResource("/dialog_yes_off.png");
        yesButton.setImage(new Image(imgURL.toString()));
        imgURL = VictoryScreenController.class.getResource("/dialog_yes_on.png");
        noButton.setImage(new Image(imgURL.toString()));
    }

    private void setListenerDialog() {
        btnYes.setOnAction(e -> actionYesDialog());
        btnNo.setOnAction(e -> actionNoDialog());
        btnYes.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = VictoryScreenController.class.getResource("/dialog_yes_on.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnYes.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = VictoryScreenController.class.getResource("/dialog_yes_off.png");
            yesButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_ENTERED, e -> {
            imgURL = VictoryScreenController.class.getResource("/dialog_no_on.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
        btnNo.addEventFilter(MouseEvent.MOUSE_EXITED, e -> {
            imgURL = VictoryScreenController.class.getResource("/dialog_no_off.png");
            noButton.setImage(new Image(imgURL.toString()));
        });
    }

    private void initialize() {

        setRankingOfPlayers();
        tblViewScore.setItems(playersList);
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        killColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getScoreManager().getKill()).asObject());
        scoreColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getScoreManager().getScore()).asObject());
        rankingColumn.setCellValueFactory(
                cellData -> new SimpleIntegerProperty(cellData.getValue().getScoreManager().getRanking()).asObject());

        for (int j = 0; j < 3; j++) {
            if (playersList.size() > j) {
                ((Label) getNodeNested(mainController, "label" + j, PANETOT)).setText(playersList.get(j).getName());
                ((Label) getNodeNested(mainController, "label" + j, PANETOT)).setTextAlignment(TextAlignment.CENTER);
            } else {
                ((Label) getNodeNested(mainController, "label" + j, PANETOT)).setText("");
                ((Rectangle) getNodeNested(mainController, "rec" + j, PANETOT)).setVisible(false);
            }
        }
    }

    private void setRankingOfPlayers() {
        Collections.sort(playersList, (p1, p2) -> p2.getScoreManager().getScore() - p1.getScoreManager().getScore());
        for (int i = 0; i < playersList.size(); i++) {
            playersList.get(i).getScoreManager().setRanking(i + 1);
        }
    }

    private void setDialog() {
        setImagesDialog();
        setListenerDialog();
    }

    private void openDialog() {
        paneDialog.setVisible(true);
        rectExitScreen.setVisible(true);
    }

    private void closeDialog() {
        paneDialog.setVisible(false);
        rectExitScreen.setVisible(false);
    }
}
