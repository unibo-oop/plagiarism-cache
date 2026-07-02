package view.controllers.minigames.oneway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import controller.minigames.oneway.OneWayController;
import controller.minigames.oneway.OneWayControllerImpl;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.DifficultyLevel;
import model.minigames.oneway.Direction;
import utility.Pair;
import view.DimensionCalculatorImpl;
import view.SceneType;
import view.controllers.minigames.AbstractMiniGameControllerFx;

/**
 * The implementation of the {@link OneWayFx}.
 *
 */
public class OneWayFxImpl extends AbstractMiniGameControllerFx implements OneWayFx {

    private static final Supplier<RuntimeException> ILLEGAL_ARGUMENT_EXCEPTION_SUPPLIER = () -> new IllegalArgumentException();

    private static final String PLAY_GRID_BUTTON_BACKGROUND = "-fx-background-color: rgb(143,187,189)";
    private static final String GREEN = "-fx-border-color: #56b72d; -fx-border-width: 5px;";
    private static final String RED = "-fx-border-color: #ff0000; -fx-border-width: 5px;";
    private static final String BLUE = "-fx-accent: #49618C";
    private static final String START = "/images/start.png";
    private static final String FINISH = "/images/finish.png";
    private static final double BUTTON_DIM = 300;
    private static final double IMG_DIM = 40;
    private static final double ARROW_DIM = 30;
    private static final double IMG_PERCENTAGE = 0.003;

    private OneWayController controller;
    private Map<Button, Pair<Integer, Integer>> buttons;
    private List<Button> arrows;
    private boolean firstInit = true;

    @FXML
    private GridPane grid;

    @FXML
    private GridPane arrowRow;

    @FXML
    private GridPane outerGrid;

//    @FXML
//    private Button quitBn;

    private Button clickedBtn;
    private Button correctBtn;

    private final PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
    private final PauseTransition finish = new PauseTransition(Duration.seconds(0.1));

    private final Predicate<Pair<Integer, Integer>> predicate = finalPosition -> this.buttons.get(clickedBtn)
            .getX() == finalPosition.getX() && this.buttons.get(clickedBtn).getY() == finalPosition.getY();

    private final EventHandler<MouseEvent> gridEventHandler = (e) -> {
        this.clickedBtn = (Button) e.getSource();
        this.controller.hit(buttons.get(clickedBtn).getX(), buttons.get(clickedBtn).getY());
        this.handlePauseTransitions();
        this.controller.resetGame();
    };

    private final BiFunction<String, Double, ImageView> image = (path, size) -> {
        final ImageView image = new ImageView(path);
        image.setFitHeight(size);
        image.setFitWidth(size);
        this.resizeImage(image);
        return image;
    };

    private void resizeImage(final ImageView image) {
        final Stage stage = (Stage) this.getRoot().getScene().getWindow();
        final DimensionCalculatorImpl calculator = new DimensionCalculatorImpl();
        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            calculator.setImageViewDimension(image, IMG_PERCENTAGE, (double) newVal / 2);
        });
    }

    private void raiseException(final boolean condition, final Supplier<RuntimeException> supplier) {
        if (condition) {
            throw supplier.get();
        }
    }

    private void handlePauseTransitions() {
        this.buttons.keySet().forEach(b -> {
            b.setDisable(true);
            b.setStyle("-fx-opacity: 1");
        });
        this.controller.showSolution();

        finish.setOnFinished(event -> {
            this.correctBtn.setGraphic(image.apply(FINISH, this.correctBtn.getWidth() / 2));
        });
        finish.playFromStart();

        pause.setOnFinished(event -> {
            this.buttons.keySet().forEach(b -> b.setDisable(false));
            this.correctBtn.setStyle(null);
            this.correctBtn.setGraphic(null);
        });
        pause.playFromStart();
    }

    private void loadImage(final int idx, final Direction step) {
        this.arrows.get(idx).setGraphic(this.image.apply(step.getPath(), ARROW_DIM));
    }

    /**
     * 
     * {@inheritDoc}
     */
    @Override
    public void initGame(final DifficultyLevel difficultyLevel) {
        this.getProgressBar().setStyle(BLUE);
        this.controller = new OneWayControllerImpl(this, difficultyLevel);
        this.buttons = new HashMap<>();
        this.arrows = new ArrayList<>();
        this.controller.firstInit();
        this.controller.startTimer();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawArrows(final List<Direction> steps) {
        raiseException(steps.isEmpty(), ILLEGAL_ARGUMENT_EXCEPTION_SUPPLIER);
        IntStream.range(0, steps.size()).forEach(i -> {
            final Button btn = new Button();
            btn.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
            this.arrowRow.add(btn, i, 0);
            this.arrows.add(btn);
            this.loadImage(i, steps.get(i));
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateArrows(final List<Direction> steps) {
        raiseException(steps.isEmpty(), ILLEGAL_ARGUMENT_EXCEPTION_SUPPLIER);
        IntStream.range(0, steps.size()).forEach(i -> this.loadImage(i, steps.get(i)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void drawGrid(final int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                final Button btn = new Button();
                btn.setPrefSize(BUTTON_DIM, BUTTON_DIM);
                btn.setPadding(new Insets(-1, -1, -1, -1));
                btn.addEventHandler(MouseEvent.MOUSE_CLICKED, gridEventHandler);
                this.grid.add(btn, j, i);
                this.buttons.put(btn, new Pair<>(i, j));
            }
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateView(final Pair<Integer, Integer> initialPosition) {
        raiseException(initialPosition == null, ILLEGAL_ARGUMENT_EXCEPTION_SUPPLIER);
        this.buttons.entrySet().stream().peek(e -> e.getKey().setGraphic(null))
                .filter(bt -> bt.getValue().equals(initialPosition)).forEach(bt -> {
                    bt.getKey().setGraphic(this.firstInit ? this.image.apply(START, IMG_DIM) : this.image.apply(START, bt.getKey().getWidth() / 2));
                });
        this.firstInit = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showCorrectAnswer(final Pair<Integer, Integer> finalPosition) {
        raiseException(finalPosition == null, ILLEGAL_ARGUMENT_EXCEPTION_SUPPLIER);
        this.correctBtn = this.buttons.entrySet().stream()
                .filter(e -> e.getValue().getX() == finalPosition.getX() && e.getValue().getY() == finalPosition.getY())
                .map(e -> e.getKey()).findAny().get();
        this.correctBtn.setStyle(PLAY_GRID_BUTTON_BACKGROUND);
        this.correctBtn.setStyle(predicate.test(finalPosition) ? GREEN : RED);
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

//    @FXML
//    private void quitGame() { // NOPMD
//        this.getController().updateProgress(ZERO);
//        this.getView().loadProgress(SceneType.END_GAME.getFilePath());
//    }

}
