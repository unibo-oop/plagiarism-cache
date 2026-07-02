package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * This class manage the view of the game when it starts.
 */
public class FlappyBirdViewImpl implements FlappyBirdView {

    private static final String TITLE = "Flappy Bird";
    private static final double HEIGHT = 335;
    private static final double WIDTH = 600;
    private static final int FONT_SIZE = 18;
    private final Label score = new Label();
    private final Stage primaryStage;
    private Pane root;
    private FlappyGameViewObserver observer;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    /**
     *This is the constructor method, which initializes the Stage and the Observer.
     * @param primaryStage PrimaryStage is the window.
     * @param observer FlappyGameViewObserver.
     */
    public FlappyBirdViewImpl(final Stage primaryStage, final FlappyGameViewObserver observer) {
        this.primaryStage = primaryStage;
        this.observer = observer;
    }

    @Override
    public final void start() {
        screenSize.setSize(WIDTH, HEIGHT);
        this.primaryStage.setTitle(TITLE);
        this.primaryStage.centerOnScreen();
        /*this.primaryStage.setMaximized(false);
        this.primaryStage.setFullScreen(false);*/
        /*this.primaryStage.setMinHeight(HEIGHT);
        this.primaryStage.setMinWidth(WIDTH);*/
        this.setGameBackground(screenSize);
    }

    private void setGameBackground(final Dimension screenSize) {
        this.root = new Pane();
        final Scene scene = new Scene(this.root, screenSize.getWidth(), screenSize.getHeight());
        final ImageView background = new ImageView(new Image(ClassLoader.getSystemResource("background.jpeg").toString()));
        this.root.getChildren().add(background);

        this.score.setText("Score: 0");
        this.score.setFont(new Font("Arial", this.root.getHeight() / FONT_SIZE));
        this.score.setTextFill(Paint.valueOf(String.valueOf(Color.WHITE)));
        this.root.getChildren().add(this.score);

        background.fitWidthProperty().bind(root.widthProperty());
        background.fitHeightProperty().bind(root.heightProperty());
        //System.out.println(ClassLoader.getSystemResource("background.jpeg").toString());
        this.observer.startGame();

        primaryStage.setScene(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                this.observer.pressSpace();
            }
        });
    }

    @Override
    public final void endGame(final int userScore) {
        final EndGameImpl endGame = new EndGameImpl(this, userScore);
        this.root.getChildren().add(endGame.getButton());
        endGame.quitBtn();
    }

    @Override
    public final void setScore(final int score) {
        this.score.setText("Score: " + score);
    }

    @Override
    public final void setObserver(final FlappyGameViewObserver observer) {
        this.observer = observer;
    }

    @Override
    public final void addChildren(final Node n) {
        this.root.getChildren().add(n);
    }

    @Override
    public final void removeChildren(final Node n) {
        this.root.getChildren().remove(n);
    }

    @Override
    public final Pane getRoot() {
        return this.root;
    }

    @Override
    public final double getHeight() {
        return screenSize.getHeight();
    }

    @Override
    public final double getWidth() {
        return screenSize.getWidth();
        }
}




