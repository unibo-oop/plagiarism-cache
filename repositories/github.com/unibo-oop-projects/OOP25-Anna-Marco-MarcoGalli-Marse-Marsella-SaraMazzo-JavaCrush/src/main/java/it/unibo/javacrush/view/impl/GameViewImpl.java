package it.unibo.javacrush.view.impl;

import java.net.URL;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.javacrush.common.AppEventType;
import it.unibo.javacrush.common.CellType;
import it.unibo.javacrush.common.GameState;
import it.unibo.javacrush.common.Position;
import it.unibo.javacrush.common.PowerUpNumber;
import it.unibo.javacrush.controller.api.AppController;
import it.unibo.javacrush.controller.api.Event;
import it.unibo.javacrush.controller.api.GameController;
import it.unibo.javacrush.view.api.GameView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Implementation of the {@link GameView} interface.
 */
public class GameViewImpl implements GameView {

    private static final String SELECT_STYLE = "-fx-border-color: red; -fx-border-width: 3px; -fx-border-radius: 5;";
    private static final int CELL_SIZE = 40;
    private static final int CELL_IMG_SIZE = 30;
    private static final int PADDING_SIZE = 20;
    private static final int GOAL_IMG_SIZE = 25;
    private static final int QUIT_BUTTON_WIDTH = 80;
    private static final int PU_WIDTH = 200;
    private static final int PU_HEIGHT = 40;
    private static final int DURATION = 10;

    private final Map<CellType, Image> cellTypeImages = new EnumMap<>(CellType.class);
    private final Map<Button, Position> gridMap = new HashMap<>();
    private Set<Position> hint = new HashSet<>();
    private final BorderPane root;
    private final GridPane grid;
    private final VBox powerUpBox;
    private final VBox quitBox;
    private final HBox topBar;
    private GameController controller;
    private AppController appController;
    private Label movesLabel;
    private HBox goalsContainer;
    private Button selectedCell;
    private Button selectedPowerUp;
    private boolean isAnimating;
    private final Service<Integer> ser;

    /**
     * Constructor of the class.
     */
    public GameViewImpl() {
        this.root = new BorderPane();
        this.root.setPadding(new Insets(PADDING_SIZE));

        final String bgpath = GameViewImpl.class.getResource("/gameBackground.png").toExternalForm();
        this.root.setStyle("-fx-background-image: url('" + bgpath + "'); "
                 + "-fx-background-size: cover; "
                 + "-fx-background-position: center; "
                 + "-fx-background-repeat: no-repeat;");

        this.grid = new GridPane();
        this.grid.setAlignment(Pos.CENTER);
        this.grid.setMaxSize(GridPane.USE_PREF_SIZE, GridPane.USE_PREF_SIZE);
        this.grid.setStyle("-fx-background-color: white; " 
            + "-fx-background-radius: 10; " 
            + "-fx-padding: 5;");

        this.root.setCenter(this.grid);

        this.powerUpBox = new VBox(PADDING_SIZE / 2);
        this.powerUpBox.setAlignment(Pos.CENTER_RIGHT);
        this.powerUpBox.setPadding(new Insets(0, PADDING_SIZE / 2, 0, PADDING_SIZE / 2));

        this.quitBox = new VBox(PADDING_SIZE * 2);
        this.quitBox.setAlignment(Pos.BOTTOM_LEFT);
        this.quitBox.setPadding(new Insets(0, PADDING_SIZE, 0, PADDING_SIZE));

        this.topBar = new HBox(PADDING_SIZE / 2);
        this.topBar.setAlignment(Pos.CENTER);
        this.topBar.setPadding(new Insets(0, 0, PADDING_SIZE, 0));
        this.root.setTop(this.topBar);

        for (final CellType type : CellType.values()) {
            final String path = "/" + type.toString().toLowerCase(Locale.ROOT) + ".png";
            try {
                final URL imageUrl = GameViewImpl.class.getResource(path);
                if (imageUrl != null) {
                    this.cellTypeImages.put(type, new Image(imageUrl.toExternalForm()));
                } else {
                    throw new IllegalStateException("Attenzione, immagine non trovata: " + path);
                }
            } catch (final IllegalArgumentException e) {
                throw new IllegalStateException("Errore caricamento immagine: " + path, e);
            }
        }

        this.isAnimating = false;
        this.ser = this.initService();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView() {

        this.movesLabel.setText("Moves left: " + this.controller.getMovesLeft());
        this.movesLabel
        .setStyle("-fx-font-size: 16px; -fx-background-color: #f0f0f0; -fx-padding: 5 10 5 10; -fx-background-radius: 5;");

        this.goalsContainer.getChildren().clear();
        final Map<CellType, Integer> goals = this.controller.getGoals();
        for (final var goal : goals.entrySet()) {
            final CellType type = goal.getKey();
            final int amount = goal.getValue();
            final int currentAmount = this.controller.getGoalsProgress().getOrDefault(type, 0);

            final Label goalLabel = new Label(" " + currentAmount + "/" + amount);
            goalLabel
            .setStyle("-fx-font-size: 16px; -fx-background-color: #f0f0f0; -fx-padding: 5 10 5 10; -fx-background-radius: 5;");
            if (type != null && this.cellTypeImages.containsKey(type)) {
                final ImageView goalImg = new ImageView(this.cellTypeImages.get(type));
                goalImg.setFitWidth(GOAL_IMG_SIZE);
                goalImg.setFitHeight(GOAL_IMG_SIZE);
                goalImg.setPreserveRatio(true);
                goalLabel.setGraphic(goalImg);
            }
            this.goalsContainer.getChildren().add(goalLabel);
        }

        for (final var e : gridMap.entrySet()) {
            final Button bt = e.getKey();
            final Position pos = e.getValue();

            final CellType type = this.controller.getCellTypeAtPos(pos);
            if (type != null && this.cellTypeImages.containsKey(type)) {
                final ImageView img = new ImageView(this.cellTypeImages.get(type));
                img.setFitWidth(CELL_IMG_SIZE); 
                img.setFitHeight(CELL_IMG_SIZE);
                img.setPreserveRatio(true);

                bt.setGraphic(img);
                bt.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 1px;");
            } else {
                bt.setGraphic(null);
                bt.setStyle("-fx-background-color: transparent; -fx-border-color: #cccccc; -fx-border-width: 1px;");
            }

            if (bt.equals(this.selectedCell)) {
                bt.setStyle(SELECT_STYLE);
            }

            if (!this.isAnimating && this.hint.contains(pos)) {
                bt.setStyle("-fx-background-color: green; -fx-border-width: 5px; -fx-border-radius: 5;");
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void quitLevel() {
        final Event quitEvent = new Event() {

                @Override
                public AppEventType type() {
                    return AppEventType.EXIT_GAME;
                }

                @Override
                public Optional<Integer> id() {
                    return Optional.empty();
                }

            };
            this.appController.notifyEvent(quitEvent);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressFBWarnings(
        value = "EI_EXPOSE_REP", 
        justification = "JavaFX requires returning the actual Node instance to attach it to the Scene graph."
        + " Defensive copying is not applicable for UI components."
    )
    @Override
    public Parent getView() {
        return this.root;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setController(final GameController cont, final AppController appCont) {
        this.controller = cont;
        this.appController = appCont;
        this.setUpGame();
    }

    private void setUpGame() {

        this.grid.getChildren().clear();
        this.gridMap.clear();
        this.powerUpBox.getChildren().clear();
        this.quitBox.getChildren().clear();
        this.topBar.getChildren().clear();

        this.movesLabel = new Label();

        this.goalsContainer = new HBox(PADDING_SIZE);
        this.goalsContainer.setAlignment(Pos.CENTER);

        this.topBar.getChildren().addAll(goalsContainer, movesLabel);

        for (int i = 0; i < controller.getBoardCols(); i++) {
            for (int j = 0; j < controller.getBoardRows(); j++) {
                final Position pos = new Position(i, j);
                final Button bt = new Button();
                bt.setPrefSize(CELL_SIZE, CELL_SIZE);
                bt.setMinSize(CELL_SIZE, CELL_SIZE);
                bt.setMaxSize(CELL_SIZE, CELL_SIZE);
                this.grid.add(bt, i, j);
                this.gridMap.put(bt, pos);

                bt.setOnAction(e -> {
                    if (this.isAnimating) {
                        return;
                    }

                    final boolean isActionValid = this.controller.hit(pos);

                    if (this.selectedPowerUp != null) {
                        this.selectedPowerUp.setStyle("");
                        this.selectedPowerUp = null;
                        this.controller.resetPowerUpSelection();
                    } else {
                        if (this.selectedCell == null) {
                            bt.setStyle(SELECT_STYLE);
                            this.selectedCell = bt;
                        } else if (this.selectedCell.equals(bt)) {
                            bt.setStyle("");
                            this.selectedCell = null;
                            this.updateView();
                        } else {
                            this.selectedCell.setStyle("");
                            this.selectedCell = null;
                        }

                    }

                    if (isActionValid) {
                        this.isAnimating = true;

                        this.updateView();
                        this.controller.handleMatches();
                        this.updateView();
                        this.hint.clear();

                        final Timeline timeline = new Timeline();

                        final KeyFrame stallAlertFrame = new KeyFrame(Duration.seconds(0.5), ev -> {

                            if (this.controller.isStall()) {
                                Platform.runLater(this::stallAlert);
                            }
                        });

                        final KeyFrame frame = new KeyFrame(Duration.seconds(0.3), event -> {

                            final boolean isFalling = this.controller.applyGravity();

                            this.updateView();

                            if (!isFalling) {
                                timeline.stop();
                                this.isAnimating = false;
                                Platform.runLater(this::timer);
                                Platform.runLater(this::checkStateGame);

                            }
                        });

                        timeline.getKeyFrames().add(stallAlertFrame);
                        timeline.getKeyFrames().add(frame);
                        timeline.setCycleCount(Timeline.INDEFINITE);
                        timeline.play();

                        this.updateView();
                    }

                });
            }
        }

        final Button powerUp1 = new Button("Hammer");
        powerUp1.setPrefSize(PU_WIDTH, PU_HEIGHT);
        powerUp1.setOnAction(e -> {
            final boolean isAvailable = this.controller.selectPowerUp(PowerUpNumber.SINGLECELL.ordinal());

            if (isAvailable) {
                if (this.selectedCell != null) {
                    this.selectedCell.setStyle("");
                    this.selectedCell = null;
                }
                if (this.selectedPowerUp != null) {
                    this.selectedPowerUp.setStyle("");
                    powerUp1.setStyle("");
                    this.selectedPowerUp = null;
                    this.controller.resetPowerUpSelection();
                } else {
                    powerUp1.setStyle(SELECT_STYLE);
                    this.selectedPowerUp = powerUp1;
                }
            }
        });

        final Button powerUp2 = new Button("Rocket");
        powerUp2.setPrefSize(PU_WIDTH, PU_HEIGHT);
        powerUp2.setOnAction(e -> {
            final boolean isAvailable = this.controller.selectPowerUp(PowerUpNumber.ROW.ordinal());

            if (isAvailable) {
                if (this.selectedCell != null) {
                    this.selectedCell.setStyle("");
                    this.selectedCell = null;
                }
                if (this.selectedPowerUp != null) {
                    this.selectedPowerUp.setStyle("");
                    powerUp2.setStyle("");
                    this.selectedPowerUp = null;
                    this.controller.resetPowerUpSelection();
                } else {
                    powerUp2.setStyle(SELECT_STYLE);
                    this.selectedPowerUp = powerUp2;
                }
            }
        });
        final Button powerUp3 = new Button("Magic Bomb");
        powerUp3.setPrefSize(PU_WIDTH, PU_HEIGHT);
        powerUp3.setOnAction(e -> {
            final boolean isAvailable = this.controller.selectPowerUp(PowerUpNumber.TYPE.ordinal());

            if (isAvailable) {
                if (this.selectedCell != null) {
                    this.selectedCell.setStyle("");
                    this.selectedCell = null;
                }
                if (this.selectedPowerUp != null) {
                    this.selectedPowerUp.setStyle("");
                    powerUp3.setStyle("");
                    this.selectedPowerUp = null;
                    this.controller.resetPowerUpSelection();
                } else {
                    powerUp3.setStyle(SELECT_STYLE);
                    this.selectedPowerUp = powerUp3;
                }
            }
        });

        this.powerUpBox.getChildren().addAll(powerUp1, powerUp2, powerUp3);
        this.root.setRight(this.powerUpBox);

        final Button quit = new Button("Quit");
        quit.setPrefWidth(QUIT_BUTTON_WIDTH);
        quit.setPrefHeight(PU_HEIGHT);
        quit.setOnAction(e -> this.controller.quitLevel());

        this.quitBox.getChildren().add(quit);
        this.root.setLeft(quitBox);

        this.timer();
        this.updateView();
    }

    private void checkStateGame() {
        final GameState state = this.controller.updateGameState();

        if (state == GameState.WON) {

            final Alert alert = new Alert(AlertType.INFORMATION);
            this.ser.cancel();
            alert.setTitle("VICTORY!!");
            alert.setContentText("Congratulations! You have won the game.");
            alert.showAndWait();

            this.quitLevel();

        } else if (state == GameState.LOST) {

            final Alert alert = new Alert(AlertType.ERROR);
            this.ser.cancel();
            alert.setTitle("GAME OVER");
            alert.setContentText("Sorry! You have lost the game.");
            alert.showAndWait();

            this.quitLevel();

        }
    }

    private void stallAlert() {

        final Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("STALL");
        alert.setContentText("The board were in stall, the cells have been refreshed.");
        alert.setOnShown(e -> this.isAnimating = true);
        alert.setOnHidden(e -> this.isAnimating = false);
        alert.showAndWait();
        this.ser.restart();
    }

    private Service<Integer> initService() {
        return new Service<>() {
            @Override
            protected Task<Integer> createTask() {
                return new Task<>() {
                    @Override
                    protected Integer call() throws Exception {
                        int sec;
                        for (sec = 0; sec < DURATION; sec++) {
                            if (isCancelled()) {
                                updateMessage("Cancelled");
                                break;
                            }
                            updateMessage("Seconds " + sec);
                            updateProgress(sec, DURATION);

                            try {
                                Thread.sleep(1000);
                            } catch (final InterruptedException interrupted) {
                                if (isCancelled()) {
                                    updateMessage("Cancelled");
                                    break;
                                }
                            }
                        }
                        return sec;
                    }
                };
            }
        };
    }

    private void timer() {

        if (!this.isAnimating) {
            this.hint.clear();
            this.ser.restart();
        }

        this.ser.setOnSucceeded(event -> {
            if (!this.controller.isStall()) {
                this.hint = this.controller.getHint();
            }
            this.updateView();
        });

    }

}
