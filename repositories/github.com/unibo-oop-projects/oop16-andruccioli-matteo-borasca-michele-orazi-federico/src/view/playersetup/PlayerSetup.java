package view.playersetup;

import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXSnackbar;

import javafx.application.Application;
import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import utils.enumerations.Color;
import view.ViewImpl;

/**
 * 
 * PlayerSetup is a class containing start() method that start the player selection view.
 * This class includes all components of GUI, such as:
 * <ul>
 * <li>The HBox containing all nodes responsible of defining all player properties (name, color, type), 
 * <li>The players table in which are displayed all players that will actually play the game
 * <li>The StackPane containing map chooser and {@link #startGame} button
 * </ul>
 * <p>
 *  @see HeaderPanel
 *  @see PlayersTable
 */
public class PlayerSetup extends Application {

    private static final double WIDTH = 900;
    private static final double HEIGHT = 600;

    private static final double INSETS = 15.0;
    private static final double SPACING = 15.0;
    private static final long SNACKBAR_TIMEOUT = 1000;

    private static final String BATTLE_ICON_PATH = "/images/battle.png";
    private static final double BATTLE_ICON_SIZE = 32;
 
    private static final int MAX_N_PLAYERS = 6;
    private static final int MIN_N_PLAYERS = 3;

    private final BorderPane mainPane = new BorderPane();
    private final TreeItem<TempPlayer> root = new TreeItem<>(new TempPlayer("Players", null, false, null));
    private final PlayersTable playerView = new PlayersTable(root); 
    private final HeaderPanel headerPane = new HeaderPanel(mainPane);
    private final JFXSnackbar snackBar = new JFXSnackbar(mainPane);
    private final JFXButton startGame = new JFXButton();
    private final JFXComboBox<MapLabel> mapChooser = new JFXComboBox<>();

    @Override
    public void start(final Stage stage) {
        final Scene scene = new Scene(mainPane, PlayerSetup.WIDTH, PlayerSetup.HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Risiko!!");
        scene.getStylesheets().add("/Style.css");

        /* Add 'addBtn' Button Listener */
        registerAddButtonListener();

        /* Add 'remove player' Listener */
        registerRemovePlayerListener();

        /* Footer Panel */
        final StackPane footerPanel = new StackPane();
        final HBox container = new HBox(SPACING);
        final ImageView startGameIcon = new ImageView(PlayerSetup.class.getResource(BATTLE_ICON_PATH).toExternalForm());
        mapChooser.getItems().add(MapLabel.getClassicMap());

        startGameIcon.setFitHeight(BATTLE_ICON_SIZE);
        startGameIcon.setFitWidth(BATTLE_ICON_SIZE);
        startGame.setGraphic(startGameIcon);
        mapChooser.setPromptText("Select a map.");
        //mapChooser.getStyleClass().add("combo-box");
        container.getChildren().addAll(mapChooser, startGame);
        footerPanel.getChildren().add(container);
        footerPanel.setAlignment(Pos.CENTER);
        container.setAlignment(Pos.CENTER);
        StackPane.setMargin(container, new Insets(INSETS));

        startGame.getStyleClass().add("startgame-button");

        /* Add 'startGame' Button Listener */
        registerStartButtonListener();

        /* Add nodes to main panel */
        mainPane.setTop(headerPane);
        mainPane.setCenter(playerView);
        mainPane.setBottom(footerPanel);

        /* Add icon */
        final Image icon = new Image(PlayerSetup.class.getResource("/images/icon.png").toExternalForm());
        stage.getIcons().add(icon);

        stage.setResizable(false);
        stage.show();
    }

    private void addTempPlayer(final String name, final Paint color, final Boolean isAI, final Color value) {
        root.getChildren().add(new TreeItem<>(new TempPlayer(name, color, isAI, value)));
        playerView.refresh();
    }

    private void registerAddButtonListener() {
        headerPane.getAddBtn().setOnMouseClicked(e -> {
            if (!(headerPane.getTextField().getText().isEmpty()) && headerPane.getColorPicker().getColor().isPresent()) {
                if (root.getChildren().stream().anyMatch(p -> p.getValue().getName().equals(headerPane.getTextField().getText()))) {
                    snackBar.show("Name already existing!", 1000);
                } else {
                    addTempPlayer(headerPane.getTextField().getText(), headerPane.getColorPicker().getColor().get(), headerPane.getCheckBox().isSelected(), headerPane.getColorPicker().getValue().get());
                    headerPane.getTextField().setText("");
                    headerPane.getColorPicker().disableButton();
                    headerPane.getColorPicker().reset();
                    headerPane.getCheckBox().setSelected(false);
                }
            } else {
                if (headerPane.getTextField().getText().isEmpty()) {
                    snackBar.show("Please insert a valid name!", 1000);
                } else 
                if (!headerPane.getColorPicker().getColor().isPresent()) {
                    snackBar.show("Please select a valid color!", 1000);
                }
            }
        if (root.getChildren().size() == (MAX_N_PLAYERS)) {
            headerPane.getAddBtn().setDisable(true);
        }
    });
 }

    private void registerStartButtonListener() {
        startGame.setOnMouseClicked(e -> {
            if (root.getChildren().size() < MIN_N_PLAYERS) {
                snackBar.show("Please insert " + MIN_N_PLAYERS +  " players!", SNACKBAR_TIMEOUT);
            } else {
                if (mapChooser.getValue() == null) {
                    mapChooser.setValue(MapLabel.getClassicMap());
                }
                ViewImpl.getIstance().setGameMap(mapChooser.getValue());
                ViewImpl.getIstance().getController().initialization(root.getChildren().stream().filter(p -> p.isLeaf()).map(m -> m.getValue().toTriple()).collect(Collectors.toList()), mapChooser.getValue().getMapType());
                ((Stage) ((JFXButton) e.getSource()).getScene().getWindow()).close();
            }
        });
    }

    private void registerRemovePlayerListener() {
        root.getChildren().addListener((ListChangeListener<? super TreeItem<TempPlayer>>) c -> {
            while (c.next()) {
                if (c.wasRemoved()) {
                    headerPane.getColorPicker().refreshButtonList(c.getRemoved().get(0).getValue().getColorValue());
                    headerPane.getAddBtn().setDisable(false);
                }
            }
        });
    }
}
