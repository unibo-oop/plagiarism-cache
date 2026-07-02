package view;

import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class SuperMarioRunViewImpl implements SuperMarioRunView {

    private static final String TITLE = "Super Mario Run";
    private static final double HEIGHT = 400;
    private static final double WIDTH = 600;
    private static final int PREF_POINTS_WIDTH = 110;
    private static final int PREF_POINTS_HEIGHT = 50;
    private static final int PREF_SCORE_WIDTH = 130;
    private static final int PREF_SCORE_HEIGHT = 50;
    private static final int TRASL_X_SCORE = 30;
    private static final int TRASL_X_POINTS = 150;

    private final Label score;
    private final Label points;
    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Stage firstStage;
    private Scene scene;
    private AnchorPane root;
    private SuperMarioRunGameViewObserver obs;

    /**
     * This is the SuperMarioRunViewImpl constructor
     * @param firstStage
     * @param observer
     */
    public SuperMarioRunViewImpl(final Stage firstStage, final SuperMarioRunGameViewObserver observer) {
        this.firstStage = firstStage;
        this.obs = observer;
        this.score = new Label();
        this.points = new Label();
    }

    @Override
    public void start() {
        screenSize.setSize(WIDTH, HEIGHT);
        this.firstStage.setTitle(TITLE);
        this.firstStage.centerOnScreen();
        this.setBackgroundGame(screenSize);
    }

    @Override
    public void setBackgroundGame(final Dimension screenSize) {

        /*Background initialization*/
        this.root = new AnchorPane();
        this.scene = new Scene(this.root, screenSize.getWidth(), screenSize.getHeight());
        final ImageView background = new ImageView(new Image(ClassLoader.getSystemResource("images/marioRunSkyBackground.jpg").toString()));
        this.root.getChildren().add(background);
        background.fitWidthProperty().bind(this.root.widthProperty());
        background.fitHeightProperty().bind(this.root.heightProperty());

        //Add the style.css file to the scene
        this.scene.getStylesheets().add("style/style.css");

        //Implementation of the Score on the scene, top-left corner
        this.score.setText("SCORE: ");
        this.score.setAlignment(Pos.CENTER_RIGHT);
        this.score.setTextAlignment(TextAlignment.RIGHT);
        this.score.setTranslateX(TRASL_X_SCORE);
        this.score.getStyleClass().add("game-score");
        this.score.setPrefSize(PREF_SCORE_WIDTH, PREF_SCORE_HEIGHT);
        this.root.getChildren().add(this.score);

        //Implementation of the points
        this.points.getStyleClass().add("game-points");
        this.points.setPrefSize(PREF_POINTS_WIDTH, PREF_POINTS_HEIGHT);
        this.points.setAlignment(Pos.CENTER_RIGHT);
        this.points.setTranslateX(TRASL_X_POINTS);
        this.root.getChildren().add(this.points);

        //Implementation of the Handler that handles the Space Bar action when KEY RELEASED
        this.obs.newGame();
        firstStage.setScene(scene);

        this.scene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                this.obs.pressSpace();
            }
        });
    }

    @Override
    public void endGame(final int playerScore) {
        final EndGameImpl endGame = new EndGameImpl(this, playerScore);
        this.root.getChildren().add(endGame.getButton());
        endGame.gameOver();
    }

    @Override
    public void setScore(final int score) {
        this.points.setText(" " + score);
    }

    @Override
    public void setObserver(final SuperMarioRunGameViewObserver observer) {
        this.obs = observer;
    }

    @Override
    public void addChildren(final Node n) {
        this.root.getChildren().add(n);
    }

    @Override
    public double getHeight() {
        return this.screenSize.getHeight();
    }

    @Override
    public double getWidth() {
        return this.screenSize.getWidth();
    }
}
