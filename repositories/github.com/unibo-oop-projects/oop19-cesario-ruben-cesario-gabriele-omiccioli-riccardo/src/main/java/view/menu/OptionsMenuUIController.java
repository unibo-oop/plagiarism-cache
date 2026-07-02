package view.menu;

import java.io.IOException;
import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import view.AdaptableResolution;
import view.Sound;
import view.display.ScreenUtilities;
import view.image.Loader;
import view.image.ViewComponentUtilities;
import view.image.Loader.ImageID;

/**
 * The Controller related to the optionsMenu.fxml GUI.
 */
public final class OptionsMenuUIController implements AdaptableResolution {

    // X and Y positions of the backgorundImage in percentage relative to the stage.
    private static final double BACKGROUND_IMG_X = 50;
    private static final double BACKGROUND_IMG_Y = 50;

    // X and Y positions of the backButton image in percentage relative to the stage.
    private static final double BACK_BTN_X = 4;
    private static final double BACK_BTN_Y = 7;

    // X and Y positions of the resText image in percentage relative to the stage.
    private static final double RES_TEXT_X = 50;
    private static final double RES_TEXT_Y = 35;

    // X and Y positions of the resLButton image in percentage relative to the stage.
    private static final double RES_L_BTN_X = 37;
    private static final double RES_L_BTN_Y = 35;

    // X and Y positions of the resRButton image in percentage relative to the stage.
    private static final double RES_R_BTN_X = 63;
    private static final double RES_R_BTN_Y = 35;

    // X and Y positions of the modeText image in percentage relative to the stage.
    private static final double MODE_TEXT_X = 50;
    private static final double MODE_TEXT_Y = 45;

    // X and Y positions of the modeLButton image in percentage relative to the stage.
    private static final double MODE_L_BTN_X = 37;
    private static final double MODE_L_BTN_Y = 45;

    // X and Y positions of the modeRButton image in percentage relative to the stage.
    private static final double MODE_R_BTN_X = 63;
    private static final double MODE_R_BTN_Y = 45;

    // X and Y positions of the audioVolText image in percentage relative to the stage.
    private static final double VOL_TEXT_X = 40;
    private static final double VOL_TEXT_Y = 55;

    // X and Y positions of the audioVolume progress bar in percentage relative to the stage.
    private static final double VOL_X = 60;
    private static final double VOL_Y = 55;

    // X and Y positions of the decVolButton image in percentage relative to the stage.
    private static final double VOL_D_BTN_X = 50;
    private static final double VOL_D_BTN_Y = 55;

    // X and Y positions of the incVolButton image in percentage relative to the stage.
    private static final double VOL_I_BTN_X = 70;
    private static final double VOL_I_BTN_Y = 55;

    // X and Y positions of the musicText image in percentage relative to the stage.
    private static final double MUSIC_TEXT_X = 40;
    private static final double MUSIC_TEXT_Y = 65;

    // X and Y positions of the musicButton image in percentage relative to the stage.
    private static final double MUSIC_BTN_X = 50;
    private static final double MUSIC_BTN_Y = 65;

    @FXML
    private Pane panel;

    @FXML
    private Rectangle background;

    @FXML
    private ImageView backgroundImage;

    @FXML
    private ImageView backButton;

    @FXML
    private ImageView resText;

    @FXML
    private ImageView resLButton;

    @FXML
    private ImageView resRButton;

    @FXML
    private ImageView modeText;

    @FXML
    private ImageView modeLButton;

    @FXML
    private ImageView modeRButton;

    @FXML
    private ImageView audioVolText;

    @FXML
    private ProgressBar audioVolume;

    @FXML
    private ImageView decVolButton;

    @FXML
    private ImageView incVolButton;

    @FXML
    private ImageView musicText;

    @FXML
    private ImageView musicButton;

    @FXML
    private void backButtonClicked() throws IOException {
            ((MainMenuUIController) Loader.loadFXML("layouts/mainMenu.fxml").getController()).draw();
    }

    @FXML
    private void resLButtonClicked() {
        Main.getStage().setFullScreen(false);
        ScreenUtilities.setFullscreen(false);
        ScreenUtilities.setPreviousResolution();
        this.draw();
    }

    @FXML
    private void resRButtonClicked() {
        Main.getStage().setFullScreen(false);
        ScreenUtilities.setFullscreen(false);
        ScreenUtilities.setNextResolution();
        this.draw();
    }

    @FXML
    private void modeButtonClicked() {
        if (Main.getStage().isFullScreen()) { 
            Main.getStage().setFullScreen(false);
            ScreenUtilities.setFullscreen(false);
            this.draw();
        } else {
            ScreenUtilities.setMaxResolution();
            Main.getStage().setFullScreen(true);
            ScreenUtilities.setFullscreen(true);
            this.draw();
        }
    }

    @FXML
    private void decVolClicked() {
        Sound.decreaseVolume();
        audioVolume.setProgress(Sound.getVolume());
    }

    @FXML
    private void incVolClicked() {
        Sound.increaseVolume();
        audioVolume.setProgress(Sound.getVolume());
    }

    @FXML
    private void musicButtonClicked() {
        if (Sound.isMusicActive()) {
            //musicButton.getStyleClass().add("radio-button-inactive");
            musicButton.setStyle("-fx-effect: innershadow(gaussian, #000000, 0, 0, 0, 0);");
            Sound.setMusicActive(false);
        } else {
            //musicButton.getStyleClass().add("radio-button-active");
            musicButton.setStyle("-fx-effect: innershadow(gaussian, #00FF00, 50, 0, 0, 0);");
            Sound.setMusicActive(true);
        }
    }

    @FXML
    private void buttonMouseEntered() {
        Sound.play("mouseOver");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw() {
        ViewComponentUtilities.resizeAndReposition(panel, background);
        ViewComponentUtilities.resizeAndReposition(backgroundImage, BACKGROUND_IMG_X, BACKGROUND_IMG_Y);
        ViewComponentUtilities.resizeAndReposition(backButton, BACK_BTN_X, BACK_BTN_Y);

        Loader.ImageID imageID = ImageID.DEFAULT;
        if (ScreenUtilities.getCurrentHeight() == ScreenUtilities.get4KHeight()) {
            imageID = ImageID.TEXT_3840X2160;
        } else if (ScreenUtilities.getCurrentHeight() == ScreenUtilities.getQHDHeight()) {
            imageID = ImageID.TEXT_2560X1440;
        } else if (ScreenUtilities.getCurrentHeight() == ScreenUtilities.getFHDHeight()) {
            imageID = ImageID.TEXT_1920X1080;
        } else if (ScreenUtilities.getCurrentHeight() == ScreenUtilities.getHDPHeight()) {
            imageID = ImageID.TEXT_1600X900;
        } else if (ScreenUtilities.getCurrentHeight() == ScreenUtilities.getHDHeight()) {
            imageID = ImageID.TEXT_1280X720;
        } else {
            System.out.println("Resolution text image not found");
            System.exit(-1);
        }
        Loader.changeImageView(imageID, resText);
        ViewComponentUtilities.resizeAndReposition(resText, RES_TEXT_X, RES_TEXT_Y);
        ViewComponentUtilities.resizeAndReposition(resLButton, RES_L_BTN_X, RES_L_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(resRButton, RES_R_BTN_X, RES_R_BTN_Y);

        if (Main.getStage().isFullScreen()) {
            imageID = ImageID.TEXT_FULLSCREEN;
        } else {
            imageID = ImageID.TEXT_WINDOWED;
        }
        Loader.changeImageView(imageID, modeText);
        ViewComponentUtilities.resizeAndReposition(modeText, MODE_TEXT_X, MODE_TEXT_Y);
        ViewComponentUtilities.resizeAndReposition(modeLButton, MODE_L_BTN_X, MODE_L_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(modeRButton, MODE_R_BTN_X, MODE_R_BTN_Y);

        ViewComponentUtilities.resizeAndReposition(audioVolText, VOL_TEXT_X, VOL_TEXT_Y);
        ViewComponentUtilities.resizeAndReposition(audioVolume, VOL_X, VOL_Y);
        audioVolume.setProgress(Sound.getVolume());
        ViewComponentUtilities.resizeAndReposition(decVolButton, VOL_D_BTN_X, VOL_D_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(incVolButton, VOL_I_BTN_X, VOL_I_BTN_Y);
        ViewComponentUtilities.resizeAndReposition(musicText, MUSIC_TEXT_X, MUSIC_TEXT_Y);
        ViewComponentUtilities.resizeAndReposition(musicButton, MUSIC_BTN_X, MUSIC_BTN_Y);
        if (Sound.isMusicActive()) {
            //musicButton.getStyleClass().add("radio-button-inactive");
            musicButton.setStyle("-fx-effect: innershadow(gaussian, #00FF00, 50, 0, 0, 0);");
        } else {
            //musicButton.getStyleClass().add("radio-button-active");
            musicButton.setStyle("-fx-effect: innershadow(gaussian, #000000, 0, 0, 0, 0);");
        }
    }

}
