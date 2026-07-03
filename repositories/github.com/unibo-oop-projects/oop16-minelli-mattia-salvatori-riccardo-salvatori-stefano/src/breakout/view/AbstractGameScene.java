package breakout.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import breakout.controller.Input;
import breakout.view.graphics.GraphicStyle;
import javafx.application.Platform;
import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * AbstractGameScene contains all the features that Classical and Advanced
 * scenes have in common.
 */
public abstract class AbstractGameScene extends Scene implements GameScene {

    private static final String CSS = "stylesheet.css";

    private final Stage mainStage;
    private final BorderPane mainPane = new BorderPane();
    private final Text centerText = new Text();
    private final GridPane statsContainer = new GridPane();
    private final Canvas canvas = new Canvas();
    private final AnchorPane centerPane = new AnchorPane(canvas, centerText);
    private final List<Input> inputs = new ArrayList<>();

    /**
     * A constructor for a generic GameScene.
     * 
     * @param style
     *            the style of the scene
     * @param mainStage
     *            The stage form where the scene is called
     */
    protected AbstractGameScene(final Stage mainStage, final GraphicStyle style) {
        super(new StackPane(), mainStage.getWidth(), mainStage.getHeight());
        this.mainStage = mainStage;
        this.mainPane.setTop(statsContainer);
        this.mainPane.setPrefSize(this.mainStage.getWidth(), this.mainStage.getHeight());
        this.statsContainer.setMaxWidth(this.mainStage.getWidth());
        this.statsContainer.setCache(true);
        this.statsContainer.setCacheHint(CacheHint.SPEED);
        this.statsContainer.setCacheShape(true);
        this.centerText.setFont(style.getFont(this.centerTextSize()));
        this.centerText.setId(style.getTextStyle());
        this.centerText.setVisible(false);
        this.centerText.setCache(true);
        this.centerText.setCacheHint(CacheHint.SPEED);
        this.canvas.setHeight(this.mainStage.getHeight());
        this.canvas.setWidth(this.mainStage.getWidth());
        this.mainPane.setCenter(this.centerPane);
        this.setRoot(mainPane);
        this.mainPane.setId(style.mainPaneStyle());
        this.getStylesheets().add(CSS);
        this.getStylesheets().add(style.getCSS());

        // Inputs
        this.setOnKeyPressed(event -> {
            final Input input = this.selectInput(event.getCode());
            if (event.getCode().equals(KeyCode.ESCAPE) || event.getCode().equals(KeyCode.P)) {
                this.pause();
            } else if (!Objects.isNull(input) && !this.inputs.contains(input)) {
                this.inputs.add(input);
            }

        });
        this.setOnKeyReleased(event -> {
            final Input input = this.selectInput(event.getCode());
            this.inputs.remove(input);
        });
    }

    /**
     * {@inheritDoc}
     */
    public List<Input> getInputs() {
        return new ArrayList<>(this.inputs);
    }

    /**
     * Clear the canvas and draw set the backgroundimage.
     * 
     * @param image
     *            the background of the canvas
     */
    public void clearCanvas(final Image image) {
        this.canvas.getGraphicsContext2D().clearRect(0, 0, this.canvas.getWidth(), this.canvas.getHeight());
        this.canvas.getGraphicsContext2D().drawImage(image, 0, 0, this.canvas.getWidth(), this.canvas.getHeight());
    }

    /**
     * {@inheritDoc}
     */
    public void draw(final Image image, final double positionX, final double positionY, final double width,
            final double height) {
        this.canvas.getGraphicsContext2D().drawImage(image, positionX, positionY, width, height);
    }

    /**
     * {@inheritDoc}
     */

    public void showText(final String text, final long time) {
        final Timer timer = new Timer();
        this.centerText.setText(text);
        this.centerText.relocate(this.mainStage.getWidth() / 2 - this.centerText.getLayoutBounds().getWidth() / 2,
                this.mainStage.getHeight() / 2 - this.centerText.getLayoutBounds().getHeight());
        this.centerText.setVisible(true);
        timer.schedule(new TimerTask() {
            /**
             * {@inheritDoc}
             */
            public void run() {
                Platform.runLater(() -> {
                    timer.cancel();
                    centerText.setVisible(false);
                });
            }
        }, time, 1);
    }

    /**
     * @return The top box that contains score,life and level values
     */
    protected GridPane getStatsContainer() {
        return this.statsContainer;
    }

    /**
     * @return The center pane of the scene
     */
    protected AnchorPane getCenterPane() {
        return this.centerPane;
    }

    /**
     * 
     * @return return the size of the text that is visible when
     *         {@link #showText(String, long)} is called
     */
    protected abstract double centerTextSize();

    /**
     * {@inheritDoc}
     */
    public abstract void pause();

    /**
     * {@inheritDoc}
     */
    public abstract void unPause();

    private Input selectInput(final KeyCode keyCode) {
        switch (keyCode) {
        case A:
        case LEFT:
            return Input.LEFT;
        case D:
        case RIGHT:
            return Input.RIGHT;
        case SPACE:
            return Input.START;
        default:
            return null;
        }
    }
}
