package view.game;

import controller.base.BaseController;
import controller.game.GameController;
import controller.game.GameControllerImpl;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import view.ViewSettings;

/**
 * This is the implementation of {@link GameView} interface.
 */
public class GameViewImpl implements GameView {

    private static final double NANO_TO_BASE = 1.0e9;
    private static final double GAMECONTAINER_SIZE = ViewSettings.BOARD_SIZE;
    private static final double INFOCONTAINER_WIDTH = ViewSettings.BOARD_SIZE * 0.5;
    private static final double TRANSLATE_RATIO = 1.0 / 6.0;
    private static final double SEPARATOR_STROKE_RATIO = 0.01;

    private final String playerName;
    private final GameController gameController;
    private final Stage stage;
    private final Pane root;
    private final Group gameContainer;
    private final Group infoContainer;
    private Label playerLabel;
    private Label scoreLabel;
    private Label multiplierLabel;
    private Label ballsRemainingLabel;

    /**
     * The constructor of GameViewImpl.
     * @param baseController
     *      The instance of {@link BaseController}.
     * @param stage
     *      The main stage.
     * @param playerName
     *      The name chosen by the player.
     * @param mapChosen
     *      The map chosen by the player.
     * @param characterChosen
     *      The character chosen by the player.
     */
    public GameViewImpl(final BaseController baseController, final Stage stage, final String playerName, 
            final String mapChosen, final String characterChosen) {
        this.playerName = playerName;
        this.stage = stage;
        this.setDimension();
        this.root = new Pane();
        this.gameContainer = new Group();
        final Scale s = new Scale(1, -1);
        final Translate t = new Translate(GAMECONTAINER_SIZE / 2, GAMECONTAINER_SIZE);
        this.gameContainer.getTransforms().addAll(t, s);
        this.infoContainer = new Group();
        final Translate t2 = new Translate(GAMECONTAINER_SIZE, 0);
        this.infoContainer.getTransforms().add(t2);
        this.setupInfo();
        this.root.getChildren().addAll(this.gameContainer, this.infoContainer);
        this.stage.getScene().setRoot(this.root);
        this.gameController = new GameControllerImpl(baseController, mapChosen, characterChosen);
    }

    private void setDimension() {
        this.stage.setHeight(GAMECONTAINER_SIZE + (this.stage.getScene().getWindow().getHeight() - this.stage.getScene().getHeight()));
        this.stage.setWidth(INFOCONTAINER_WIDTH + GAMECONTAINER_SIZE);
        this.stage.centerOnScreen();
    }

    private void setupInfo() {
        this.playerLabel = new Label("Name: " + this.playerName);
        this.scoreLabel = new Label();
        this.multiplierLabel = new Label();
        this.ballsRemainingLabel = new Label();
        final Line separator = new Line(0, 0, 0, GAMECONTAINER_SIZE);
        separator.setStrokeWidth(GAMECONTAINER_SIZE * SEPARATOR_STROKE_RATIO);
        separator.setTranslateX(separator.getStrokeWidth());
        this.infoContainer.getChildren().addAll(this.playerLabel, this.scoreLabel, this.multiplierLabel, this.ballsRemainingLabel, separator);
    }

    @Override
    public final void initiateGame() {
        this.gameController.initialize();
        this.prepareStage();
        this.getGameLoop().start();
    }

    private void prepareStage() {
        this.scoreLabel.setText("Score: " + this.gameController.getScore());
        this.multiplierLabel.setText("Multiplier: " + this.gameController.getMultiplier());
        this.playerLabel.setTranslateX(INFOCONTAINER_WIDTH / 2);
        this.playerLabel.setTranslateY(GAMECONTAINER_SIZE * TRANSLATE_RATIO);
        this.scoreLabel.setTranslateX(INFOCONTAINER_WIDTH / 2);
        this.scoreLabel.setTranslateY(GAMECONTAINER_SIZE * 2 * TRANSLATE_RATIO);
        this.multiplierLabel.setTranslateX(INFOCONTAINER_WIDTH / 2);
        this.multiplierLabel.setTranslateY(GAMECONTAINER_SIZE * 3 * TRANSLATE_RATIO);
        this.ballsRemainingLabel.setTranslateX(INFOCONTAINER_WIDTH / 2); 
        this.ballsRemainingLabel.setTranslateY(GAMECONTAINER_SIZE * 4 * TRANSLATE_RATIO); 
    }

    @Override
    public final void terminateGame() {
        if (this.gameController.isGameWon()) {
            final Alert winAlert = new Alert(AlertType.INFORMATION, "Winner winner chicken dinner!");
            Platform.runLater(winAlert::showAndWait);
        } else {
            final Alert loseAlert = new Alert(AlertType.INFORMATION, "Mission failed! You'll get 'em next time");
            Platform.runLater(loseAlert::showAndWait);
        }
        this.gameController.endGame(this.playerName);
    }
    // This is the game loop of the game: it updates and renders all the objects.
    private AnimationTimer getGameLoop() {
        return new AnimationTimer() {

            private long last = System.nanoTime();

            @Override
            public void handle(final long now) {
                final double delta = (now - this.last) / NANO_TO_BASE;
                root.setOnMouseMoved(e -> gameController.setMousePosition(e.getSceneX() - GAMECONTAINER_SIZE / 2, e.getSceneY()));
                root.setOnMouseClicked(e -> gameController.launchBall());
                gameController.update(delta);
                gameController.render(gameContainer);
                scoreLabel.setText("Score: " + gameController.getScore());
                multiplierLabel.setText("Multiplier: " + gameController.getMultiplier());
                ballsRemainingLabel.setText("Balls remaining: " + gameController.getBallsRemaining());
                this.last = now;
                if (gameController.isGameOver()) {
                    this.stop();
                    terminateGame();
                }
            }
        };
    }
}
