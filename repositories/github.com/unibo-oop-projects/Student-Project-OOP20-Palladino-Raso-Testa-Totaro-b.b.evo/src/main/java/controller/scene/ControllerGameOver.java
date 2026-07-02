package controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import controller.sound.SoundController;
import controller.utilities.GUILayout;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import view.PersonalViews;
import view.SceneLoader;

public class ControllerGameOver implements Initializable, FXMLMenuController, GUILayout {

    @FXML
    private SplitPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private VBox buttonContainer;

    @FXML
    private Button btnMenu;

    @FXML
    private Button btnRanking;

    @FXML
    private HBox coinContainer;

    @FXML
    private Label lblCoins;

    @FXML
    private Label lblHighscore;

    @FXML
    private Label lblScore;

    /**
     * Initialize all view components.
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        this.resizable();
        this.loadFont();
        this.loadAnimation();
        this.loadListener();
        this.loadMusic();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadListener() {
        this.btnMenu.setOnAction(this.switchPage(PersonalViews.SCENE_MAIN_MENU, PersonalStyle.DEFAULT_STYLE,
                                                 this.getCurrentWidth(), this.getCurrentHeight(), true));


        this.btnRanking.setOnAction(this.switchPage(PersonalViews.SCENE_RANKING, PersonalStyle.DEFAULT_STYLE,
                                                    this.getCurrentWidth(), this.getCurrentHeight(), true));
    }

    /**
     * This method allows to switch the current scene whit the next scene.
     * @param scene - use to set the next scene.
     * @param style - use to set the style for the next scene.
     * @param width - use to set the width for next scene.
     * @param height - use to set the height for next scene.
     * @param resizable - use to understand if the next scene will be resizable or not.
     * @return an ActionEvent that allow to change between the current scene and the next scene.
     */
    private EventHandler<ActionEvent> switchPage(final PersonalViews scene, final PersonalStyle style, 
                                                 final double width, final double height, final boolean resizable) {

        return new EventHandler<ActionEvent>() {

            @Override
            public void handle(final ActionEvent event) {
                final Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                //Switch Scene
                SceneLoader.switchScene(currentStage, 
                scene.getURL(), 
                scene.getTitleScene(), 
                width, 
                height,
                style.getStylePath());
                currentStage.setResizable(resizable);
                soundClick();
            }

        };
    }

    /**
     * Use to get the current scene width.
     */
    private double getCurrentWidth() {
        return this.window.getWidth();
    }

    /**
     * Use to get the current scene height.
     */
    private double getCurrentHeight() {
        return this.window.getHeight();
    }
    /**
     * Method that allow to play the button's sound.
     */
    private void soundClick() {
        SoundController.playSoundFx(PersonalSounds.TICK_BUTTON.getURL());
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadAnimation() {
        //Blink insert coin label
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1.00), evt -> this.lblCoins.setVisible(false)),
                new KeyFrame(Duration.seconds(0.50), evt -> this.lblCoins.setVisible(true)));
                timeline.setCycleCount(Animation.INDEFINITE);
                timeline.play();
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void loadFont() {
        this.lblTitle
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_LARGE_LABEL_SIZE));
        this.btnMenu
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnRanking
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.lblCoins
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
        this.lblHighscore
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
        this.lblScore
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void resizable() {
        this.btnMenu.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.btnRanking.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.lblTitle.setWrapText(true);
        this.lblHighscore.setWrapText(true);
    }

    /**
     * 
     * This method allow to start the game music.
     *
     */
    private void loadMusic() {
        //Play Button CLick Sound
        SoundController.playMusic(PersonalSounds.MAIN_THEME.getURL());
    }

    /**
     * Updates Score and Highscore labels.
     * @param playerScore player's score
     * @param podiumScore best score from ranking
     */
    public void updateScore(final int playerScore, final String podiumScore) {
        this.lblScore.setText("YOUR SCORE: " + playerScore);
        this.lblHighscore.setText("HIGHSCORE: " + podiumScore);
    }
}
