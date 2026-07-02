package gameselectionmenu.view;

import gameselectionmenu.controller.SelectionMenuController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;
import util.MenuVariablesUtils;

/**
 * SelectionMenuViewImpl is a class that implements the interface
 * SelectionMenuView. This class represents the graphical part of the player
 * selection menu.
 */
public class SelectionMenuViewImpl implements SelectionMenuView {

    private static final String BACKGROUND = "menu" + MenuVariablesUtils.SEPARATOR + "start"
            + MenuVariablesUtils.SEPARATOR + "background.png";
    private static final String START_GAME = "Start Game";
    private static final String SELECTION_FIRST_TEXT = "Player name: ";
    private static final String SELECTION_SECOND_TEXT = "Select race: ";
    private static final String SLIDER_FIRST_TEXT = "Select the number of players: ";
    private static final String PLAYER_BOX_BUTTON_TEXT = "Add Player";
    private static final String SLIDER_SECOND_TEXT = "Players: ";
    private static final String ALERT_TITLE = "The information entered is insufficient or incorrect.";
    private static final String ALERT_INFORMATION_TEXT = "Each player must have a name and a race, and their values must be different to all other players.";
    private static final double SPACING = 15;
    private static final double CHILDREN_SPACING = 10;
    private static final double PADDING = 30;
    private static final int SLIDER_WIDTH_REDUCER = 4;
    private static final int TEXTFIELD_WIDTH_REDUCER = 6;

    private SelectionMenuController controller;
    private final Stage stage;
    private final HBox playersBox;
    private final Button startGame;
    private boolean controllerSet;

    /**
     * Constructor.
     * 
     * @param screenDimension is a pair of double that represent the height and the
     *                        width of the screen.
     */
    public SelectionMenuViewImpl(final Pair<Double, Double> screenDimension) {
        this.stage = new Stage();
        this.stage.setHeight(screenDimension.getKey());
        this.stage.setWidth(screenDimension.getValue());
        this.stage.setMinHeight(MenuVariablesUtils.HEIGHT_MIN);
        this.stage.setMinWidth(MenuVariablesUtils.WIDTH_MIN);
        this.controllerSet = false;
        this.startGame = new Button(START_GAME);
        this.playersBox = new HBox(SPACING);
        stage.getIcons().add(new Image(MenuVariablesUtils.MENU_ICON));
    }

    /** {@inheritDoc} **/
    @Override
    public void setController(final SelectionMenuController controller) {
        if (this.controllerSet) {
            throw new IllegalStateException();
        }
        this.controllerSet = true;
        this.controller = controller;
    }

    /** {@inheritDoc} **/
    @Override
    public void draw() {
        final Scene scene = new Scene(this.createContent());
        viewUpdate();
        this.stage.setScene(scene);
        this.stage.centerOnScreen();
        this.stage.show();
    }

    private Parent createContent() {
        final BorderPane rootPane = new BorderPane();
        rootPane.setPrefSize(stage.getWidth(), stage.getHeight());
        final ImageView bg = new ImageView(BACKGROUND);
        bg.fitHeightProperty().bind(rootPane.heightProperty());
        bg.fitWidthProperty().bind(rootPane.widthProperty());
        this.startGame.setOnAction(e -> {
            controller.startGame(stage.getHeight(), stage.getWidth());
            this.stage.close();
        });
        this.startGame.setDisable(true);
        this.startGame.minWidthProperty().bind(rootPane.widthProperty());
        this.playersBox.setAlignment(Pos.CENTER);
        rootPane.setTop(createSlider());
        rootPane.setCenter(this.playersBox);
        rootPane.setBottom(this.startGame);
        rootPane.getChildren().add(bg);
        return rootPane;

    }

    private VBox createSlider() {
        // create a VBox
        final VBox root = new VBox();
        // create label
        final Label firstLabel = new Label(SLIDER_FIRST_TEXT);
        final Label secondLabel = new Label(SLIDER_SECOND_TEXT + controller.getCurrentPlayers());
        firstLabel.setFont(MenuVariablesUtils.MEDIUM_FONT);
        secondLabel.setFont(MenuVariablesUtils.MEDIUM_FONT);

        // set the color of the texts
        firstLabel.setTextFill(Color.BLACK);
        secondLabel.setTextFill(Color.BLACK);

        // create slider
        final Slider slider = new Slider(controller.getMinimumPlayers(), controller.getMaximumPlayers(),
                controller.getCurrentPlayers());

        // enable TickLabels and Tick Marks
        slider.setShowTickLabels(true); // enable numbers under ticks
        slider.setShowTickMarks(true); // show ticks
        slider.setSnapToTicks(true); // without intermediate values
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setMaxWidth(MenuVariablesUtils.WIDTH_DEFAULT / SLIDER_WIDTH_REDUCER);

        slider.valueProperty().addListener((obs, oldVal, newVal) -> {
            secondLabel.setText(SLIDER_SECOND_TEXT + Math.round(newVal.doubleValue()));
            controller.setCurrentPlayers((int) Math.round(newVal.doubleValue()));
            viewUpdate();
        });

        root.setPadding(new Insets(PADDING));
        root.setSpacing(CHILDREN_SPACING);
        root.getChildren().addAll(firstLabel, slider, secondLabel);
        root.setAlignment(Pos.CENTER);
        return root;
    }

    private VBox createPlayers() {
        final VBox root = new VBox();
        final Label firstLabel = new Label(SELECTION_FIRST_TEXT);
        final Label secondLabel = new Label(SELECTION_SECOND_TEXT);
        final Button button = new Button(PLAYER_BOX_BUTTON_TEXT);

        firstLabel.setFont(MenuVariablesUtils.LOWER_FONT);
        secondLabel.setFont(MenuVariablesUtils.LOWER_FONT);
        button.setFont(MenuVariablesUtils.LOWER_FONT);
        // set the color of the texts
        firstLabel.setTextFill(Color.BLACK);
        secondLabel.setTextFill(Color.BLACK);
        button.setTextFill(Color.BLACK);

        final TextField textField = new TextField();
        textField.setMaxWidth(MenuVariablesUtils.WIDTH_DEFAULT / TEXTFIELD_WIDTH_REDUCER);
        final ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(controller.getRaceNameList());
        button.setOnAction(e -> {
            if (controller.verify(playersBox.getChildren().indexOf(root), textField.getText(), comboBox.getValue())) {
                button.setVisible(false);
                textField.setDisable(true);
                comboBox.setDisable(true);
            } else {
                final Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle(ALERT_TITLE);
                alert.setHeaderText(null);
                alert.setContentText(ALERT_INFORMATION_TEXT);

                alert.showAndWait();
            }
            viewUpdate();
        });

        root.setPadding(new Insets(PADDING));
        root.setSpacing(CHILDREN_SPACING);
        root.getChildren().addAll(firstLabel, textField, secondLabel, comboBox, button);

        return root;
    }

    private void viewUpdate() {
        if (controller.getCurrentPlayers() > playersBox.getChildren().size()) {
            while (controller.getCurrentPlayers() > playersBox.getChildren().size()) {
                playersBox.getChildren().add(createPlayers());
            }
        } else if (controller.getCurrentPlayers() < playersBox.getChildren().size()) {
            playersBox.getChildren().remove(controller.getCurrentPlayers(), playersBox.getChildren().size());
        }
        if (controller.canStartGame()) {
            startGame.setDisable(false);
        } else {
            startGame.setDisable(true);
        }
    }

}
