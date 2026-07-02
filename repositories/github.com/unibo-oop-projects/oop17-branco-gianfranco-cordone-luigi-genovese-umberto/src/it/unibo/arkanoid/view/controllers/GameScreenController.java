package it.unibo.arkanoid.view.controllers;

import it.unibo.arkanoid.controller.Command;
import it.unibo.arkanoid.controller.Controller;
import it.unibo.arkanoid.controller.GameStats;
import it.unibo.arkanoid.controller.MovePaddleCommand;
import it.unibo.arkanoid.view.Drawer;
import it.unibo.arkanoid.view.ImageID;
import it.unibo.arkanoid.view.ImageLoaders;
import it.unibo.arkanoid.view.JavaFXDrawer;
import it.unibo.arkanoid.view.View;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The Controller class of  GameScreen Scene.
 * 
 */
public class GameScreenController extends SubViewController {

    private boolean gameStarted;
    private Drawer drawer;
    private GameStats gameStats;
    @FXML
    private Label score;
    @FXML
    private VBox instructions;
    @FXML
    private Button pauseButton;
    @FXML
    private BorderPane containerCanvas;
    @FXML
    private Canvas canvas;
    @FXML
    private HBox lifeContainer;
    @FXML
    private BorderPane pauseView;
    @FXML
    private Button audioButton;

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final Controller controller, final View view) { //NOPMD
        super.init(controller, view);
        this.gameStarted = false;
        this.gameStats = controller.getGameStats();
        this.drawer = new JavaFXDrawer(this.canvas, this.gameStats.getWorldWidth(), this.gameStats.getWorldHeight(),
                this.containerCanvas);
    }

    /**
     * Getter for Drawer component.
     * 
     * @return The Drawer.
     */
    public Drawer getDrawer() {
        return drawer;
    }

    @FXML
    private void pauseClicked() { //NOPMD
        pauseView.setVisible(true);
        this.getController().getGameLoop().setPause();
    }

    @FXML
    private void resumeClicked() { //NOPMD
        pauseView.setVisible(false);
        this.getController().getGameLoop().resumeGame();
    }

    @FXML
    private void menuClicked() { //NOPMD
        this.getController().getGameLoop().stopGame();
        this.getController().next();
    }

    @FXML
    private void audioClicked() { //NOPMD
        if (this.getView().getSongLoop().isPaused()) {
            audioButton.setText("Audio OFF");
            this.getView().getSongLoop().resume();
        } else {
            audioButton.setText("Audio ON");
            this.getView().getSongLoop().pause();
        }
    }

    @FXML
    private void onKeyReleased(final KeyEvent event) { //NOPMD
        if (!this.gameStarted && event.getCode() == KeyCode.SPACE) {
            this.getController().next();
            this.gameStarted = true;
            this.pauseButton.setDisable(false);
            this.instructions.setVisible(false);
        }
    }

    @FXML
    private void onMouseMoved(final MouseEvent event) { //NOPMD
        final double x = (event.getX() - this.canvas.getWidth() / 2) * this.gameStats.getWorldWidth()
                / this.canvas.getWidth();
        final Command command = new MovePaddleCommand(x);
        this.getController().getGameLoop().addInput(command);
    }

    /**
     * 
     * @param score
     *          current score.
     */
    public void setScore(final int score) {
        this.score.setText(Integer.toString(score));
    }

    private Node createLifeIcon() {
        final ImageView imageView = new ImageView();
        imageView.setImage(ImageLoaders.getImageLoader().getImage(ImageID.LIFE));
        imageView.fitHeightProperty().bind(this.lifeContainer.prefHeightProperty());
        imageView.setPreserveRatio(true);
        return imageView;
    }

    /**
     * 
     * @param lives
     *          current lives.
     */
    public void setLives(final int lives) {
        this.lifeContainer.getChildren().clear();
        for (int i = 0; i < lives; i++) {
            this.lifeContainer.getChildren().add(this.createLifeIcon());
        }
    }

}
