package view.controllers.minigames.perilouspath;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import controller.minigames.perilouspath.PerilousPathController;
import controller.minigames.perilouspath.PerilousPathControllerImpl;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.minigames.perilouspath.Mine;
import utility.Pair;
import view.SceneType;
import view.controllers.minigames.AbstractMiniGameControllerFx;

/**
 * Implementation of {@link PerilousPathFx}.
 */
public class PerilousPathFxImpl extends AbstractMiniGameControllerFx implements PerilousPathFx {

    /**
     * The size of the button.
     */
    private static final int BUTTON_SIZE = 200;

    /**
     * The size of the image.
     */
    private static final double IMAGE_SIZE = BUTTON_SIZE / 3.5;

    @FXML
    private GridPane grid;

    @FXML
    private Label instructions;

    private PerilousPathController controller;
    private Map<Button, Pair<Integer, Integer>> buttons;

    private final EventHandler<MouseEvent> eventHit = (e) -> {
        final Button bt = (Button) e.getSource();
        final Pair<Integer, Integer> position = this.buttons.get(bt);
        this.controller.hitAttempt(position.getX(), position.getY());
    };

    private final EventHandler<ActionEvent> eventReset = (e) -> {
        this.restore();
        this.controller.resetGame();
    };

    private final EventHandler<ActionEvent> eventUpdate = (e) -> {
        this.restore();
        this.instructions.setText(Instructions.FIND_SAFE_PATH.toString());
        this.controller.updateView();
    };

    private final Function<String, ImageView> image = (imageName) -> {
        final ImageView image = new ImageView("/images/" + imageName + ".png");
        image.setFitHeight(IMAGE_SIZE);
        image.setFitWidth(IMAGE_SIZE);
        return image;
    };

    private void setProperty(final Button bt) {
        bt.setPrefSize(BUTTON_SIZE, BUTTON_SIZE);
        bt.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void initGame(final DifficultyLevel difficultyLevel) {
        this.getProgressBar().setStyle("-fx-accent: #9e3d64");
        this.controller = new PerilousPathControllerImpl(difficultyLevel, this);
        this.buttons = new HashMap<>();
        this.controller.startGame();
        this.controller.startTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(final int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Button bt = new Button();
                this.setProperty(bt);
                bt.addEventHandler(MouseEvent.MOUSE_CLICKED, eventHit);
                this.grid.add(bt, j, i);
                this.buttons.put(bt, new Pair<>(i, j));
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void animation(final int millis, final boolean mineExplosion) {
        final Timeline timeline = new Timeline();
        KeyFrame keyFrame;
        if (mineExplosion) {
            this.instructions.setText(Instructions.PERILOUS_PATH.toString());
            keyFrame = new KeyFrame(Duration.millis(millis), eventReset);
        } else {
            this.instructions.setText(Instructions.MINES_MEMORIZATION.toString());
            keyFrame = new KeyFrame(Duration.millis(millis), eventUpdate);
        }
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showStart(final Pair<Integer, Integer> start) {
        Objects.requireNonNull(start);
        this.buttons.entrySet().stream()
                               .filter(bt -> bt.getValue().equals(start))
                               .forEach(bt -> bt.getKey().setGraphic(image.apply("start")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showFinish(final Pair<Integer, Integer> finish) {
        Objects.requireNonNull(finish);
        this.buttons.entrySet().stream()
                               .filter(bt -> bt.getValue().equals(finish))
                               .forEach(bt -> bt.getKey().setGraphic(image.apply("finish")));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMines(final Set<Mine> mines) {
        Objects.requireNonNull(mines);
        mines.stream()
             .forEach(m -> this.buttons.entrySet().stream()
                                                  .filter(bt -> bt.getValue().equals(m.getMinePosition()))
                                                  .forEach(bt -> bt.getKey().setGraphic(image.apply("mine"))));
        this.buttons.entrySet().stream().forEach(bt -> bt.getKey().setDisable(true));
        this.buttons.entrySet().stream().forEach(bt -> bt.getKey().setStyle("-fx-opacity: 1"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showStep(final int row, final int col) {
        this.buttons.entrySet().stream().filter(bt -> bt.getValue().equals(new Pair<>(row, col))).forEach(
                bt -> bt.getKey().setStyle("-fx-background-color: rgb(158,61,100); -fx-border-color: darkred;"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showExplosion(final int row, final int col) {
        this.buttons.entrySet().stream()
                          .filter(bt -> bt.getValue().equals(new Pair<>(row, col)))
                          .forEach(bt -> bt.getKey().setGraphic(image.apply("bang")));
        this.buttons.entrySet().stream().forEach(bt -> bt.getKey().setDisable(true));
        this.buttons.entrySet().stream().forEach(bt -> bt.getKey().setStyle("-fx-opacity: 1"));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void restore() {
        this.buttons.entrySet().stream().forEach(bt -> bt.getKey().setGraphic(null));
        this.buttons.entrySet().stream().forEach(bt -> bt.getKey().setDisable(false));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showEndGame(final int finalScore) {
        Platform.runLater(() -> {
            this.getController().updateProgress(finalScore);
            this.getView().loadProgress(SceneType.END_GAME.getFilePath());
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void stopTimer() {
        this.controller.stopTimer();
    }
}
