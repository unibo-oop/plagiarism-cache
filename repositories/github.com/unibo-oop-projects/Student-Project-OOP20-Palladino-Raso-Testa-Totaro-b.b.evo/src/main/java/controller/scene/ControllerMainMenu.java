package controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import controller.sound.SoundController;
import controller.utilities.GUILayout;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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

public class ControllerMainMenu implements Initializable, FXMLMenuController, GUILayout {

    /**
     * Dimension of Creative mode width, used also in controllerMapEditor.
     */
    public static final double CREATIVE_MODE_WIDTH = 800;

    /**
     * Dimension of Creative mode height, used also in controllerMapEditor.
     */
    public static final double CREATIVE_MODE_HEIGHT = 500;

    @FXML
    private AnchorPane window;

    @FXML
    private BorderPane panel;

    @FXML
    private HBox titleContainer;

    @FXML
    private HBox coinContainer;

    @FXML
    private Label lblTitle;

    @FXML
    private Label lblCoins;

    @FXML
    private VBox buttonContainer;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnCreativeMode;

    @FXML
    private Button btnTutorial;

    @FXML
    private Button btnRanking;

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
        this.btnPlay.setOnAction(event -> FXMLMenuController.switchScene(this.getCurrentStage(),
                                                                         PersonalViews.SCENE_CHARACTER_MENU, PersonalStyle.DEFAULT_STYLE, 
                                                                         this.getCurrentWidth(), this.getCurrentHeight(), true));

        this.btnSettings.setOnAction(event -> FXMLMenuController.switchScene(this.getCurrentStage(),
                                                                             PersonalViews.SCENE_SETTINGS, PersonalStyle.DEFAULT_STYLE,
                                                                             this.getCurrentWidth(), this.getCurrentHeight(), true));

        this.btnCreativeMode.setOnAction(event -> FXMLMenuController.switchScene(this.getCurrentStage(),
                                                                                 PersonalViews.SCENE_CREATIVEMODE, PersonalStyle.DEFAULT_STYLE, 
                                                                                 CREATIVE_MODE_WIDTH, 
                                                                                 CREATIVE_MODE_HEIGHT, false));

        this.btnTutorial.setOnAction(event -> FXMLMenuController.switchScene(this.getCurrentStage(),
                                                                             PersonalViews.SCENE_TUTORIAL, PersonalStyle.DEFAULT_STYLE,
                                                                             this.getCurrentWidth(), this.getCurrentHeight(), true));

        this.btnRanking.setOnAction(event -> FXMLMenuController.switchScene(this.getCurrentStage(),
                                                                            PersonalViews.SCENE_RANKING, PersonalStyle.DEFAULT_STYLE,
                                                                            this.getCurrentWidth(), this.getCurrentHeight(), true));
    }

    /**
     * Use to get the current scene width.
     */
    private double getCurrentWidth() {
        return this.getCurrentStage().getWidth();
    }

    /**
     * Use to get the current scene height.
     */
    private double getCurrentHeight() {
        return this.getCurrentStage().getHeight();
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
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnPlay
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnSettings
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnCreativeMode
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnTutorial
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.btnRanking
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
        this.lblCoins
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
    }

    /**
     * 
     * {@inheritDoc}
     *
     */
    @Override
    public void resizable() {
        this.btnPlay.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.btnSettings.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.btnCreativeMode.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.btnTutorial.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.btnRanking.prefWidthProperty().bind(this.buttonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
        this.lblTitle.setWrapText(true);
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
     * 
     * This method allow to get the current stage.
     *
     */
    private Stage getCurrentStage() {
        return (Stage) this.window.getScene().getWindow();
    }

}
