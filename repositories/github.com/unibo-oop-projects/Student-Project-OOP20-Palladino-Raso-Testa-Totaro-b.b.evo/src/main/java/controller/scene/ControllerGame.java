package controller.scene;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import controller.utilities.GUILayout;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import model.utilities.ScreenUtilities;
import resource.routing.BackGround;
import resource.routing.PersonalFonts;
import view.graphics.AdapterGraphics;
import view.graphics.AdapterGraphicsImpl;
import model.entities.GameObject;

public class ControllerGame implements Initializable, GUILayout {

    private GraphicsContext gc;

    @FXML
    private Canvas canvas;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblPowerUp;

    @FXML
    private Label lblScore;

    @FXML
    private Label lblLives;

    @FXML
    private Label lblPlay;

    @FXML
    private Label lblEsc;

    @FXML
    private Pane panel;

    @FXML
    private VBox dashBoard;

    /**
     * When Game.fxml is loaded, it initializes the width and height of the canvas, 
     * sets its graphic context for the drawing of the various entities, 
     * sets the animations and fonts.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.canvas.setWidth(ScreenUtilities.CANVAS_WIDTH);
        this.canvas.setHeight(ScreenUtilities.CANVAS_HEIGHT);
        this.panel.setMinWidth(ScreenUtilities.CANVAS_WIDTH);
        this.panel.setMaxWidth(ScreenUtilities.CANVAS_WIDTH);
        this.panel.setMinHeight(ScreenUtilities.CANVAS_HEIGHT);
        this.panel.setMaxHeight(ScreenUtilities.CANVAS_HEIGHT);
        this.gc = canvas.getGraphicsContext2D();
        this.loadFont();
        this.loadAnimation();
    }

    /**
     * This method allows to set the fonts for the view components.
     */
    private void loadFont() {
        this.lblTitle
        .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
    }

    /**
     * This method allows to set the animations for the view components.
     */
    private void loadAnimation() {
        final Timeline timelineTitolo = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                timelineTitolo.setCycleCount(Animation.INDEFINITE);
                timelineTitolo.play();
    }

    /**
     * This method sets the visibility of the label.
     * @param play boolean value that sets the visibility of the label
     */
    public void setPlay(final boolean play) {
        this.lblPlay.setVisible(play);
    }

    /**
     * Draw all entities of the game adapted to the current resolution.
     * @param gameEntities Collection of each game entity taken from the game loop.
     * @param score The score of the player.
     * @param lives The remaining life ot the player.
     * @param powerUpType The type of the powerUp.
     */
    public void render(final Set<GameObject> gameEntities, final int score, final int lives, final String powerUpType) {
        drawScoreLivesandPwUp(score, lives, powerUpType);
        drawWorld(gameEntities);
    }

    /**
     * Calls the graphic component of the game entities by updating them, 
     * which will be drawn on the canvas.
     * @param gameEntities to draw
     */
    private void drawWorld(final Set<GameObject> gameEntities) {
        gc.clearRect(0, 0, ScreenUtilities.CANVAS_WIDTH, ScreenUtilities.CANVAS_HEIGHT);
        final AdapterGraphics ga = new AdapterGraphicsImpl(gc);
        gameEntities.stream().forEach(e -> {
            e.updateGraphics(ga);
        });
    }

    /**
     * Draw information about your current score and lives.
     * @param score The score of the player.
     * @param lives The remaining life ot the player.
     *  @param lives The type of the powerUp.
     */
    private void drawScoreLivesandPwUp(final Integer score, final Integer lives, final String powerUpType) {
        this.lblScore.setText("SCORE: " + score.toString());
        this.lblLives.setText("LIVES: " + lives.toString());
        this.lblPowerUp.setText("LAST ACTIVE POWERUP: " + powerUpType);
    }

    /**
     * Set the background image of the canvas.
     * @param backGround input image to set as background
     */
    public void setBackgroundImage(final BackGround backGround) {

        final BackgroundImage bg = new BackgroundImage(new Image(backGround.getPath(), 
                                                    ScreenUtilities.CANVAS_WIDTH,
                                                    ScreenUtilities.CANVAS_HEIGHT,
                                                    false,
                                                    true),
                                                    BackgroundRepeat.REPEAT, 
                                                    BackgroundRepeat.NO_REPEAT, 
                                                    BackgroundPosition.DEFAULT,
                                                    BackgroundSize.DEFAULT);
        this.panel.setBackground(new Background(bg));
    }

    /**
     * Getter of the canvas.
     * @return canvas
     */
    public final Canvas getCanvas() {
        return this.canvas;
    }

}
