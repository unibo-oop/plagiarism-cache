package controller.scene;

import java.net.URL;
import java.util.ResourceBundle;

import controller.settings.SettingsController;
import controller.settings.SettingsControllerImpl;
import controller.sound.SoundController;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.utilities.GameUtilities;
import model.utilities.ScreenUtilities;
import resource.routing.PersonalFonts;
import resource.routing.PersonalSounds;
import resource.routing.PersonalStyle;
import view.PersonalViews;

public class ControllerSettings implements Initializable, FXMLMenuController {

        @FXML
        private AnchorPane window;

        @FXML
        private BorderPane panel;

        @FXML
        private HBox titleContainer;

        @FXML
        private Label lblTitle;

        @FXML
        private VBox radioButtonContainer;

        @FXML
        private CheckBox ckSoundFX;

        @FXML
        private CheckBox ckSound;

        @FXML
        private RadioButton rbUseLeftRight;

        @FXML
        private RadioButton rbUseUpDown;

        @FXML
        private Button btnBack;

        private SettingsController controller;


        /**
         * Method that initialize all javaFx component.
         */
        @Override
        public void initialize(final URL location, final ResourceBundle resources) {
            this.controller = new SettingsControllerImpl(GameUtilities.SETTINGS_PATH);
            this.resizable();
            this.loadFont();
            this.loadAnimation();
            this.loadListener();
            this.updateViewComponent(this.controller.isSoundFxEnable(), this.controller.isMusicEnable(), 
                                     this.controller.isLeftAndRightEnable(), this.controller.isUpAndDownEnable());

        }

        /**
         * Method used to update view component.
         * @param isSoundsFxEnable - used to enable or disable SoundFx radio button.
         * @param isMusicEnable - used to enable or disable Music radio button.
         * @param isLeftAndRightEnable - used to enable or disable Left and right check box.
         * @param isUpAndDownEnable - used to enable or disable Up and Down check box.
         */
        public void updateViewComponent(final boolean isSoundsFxEnable, final boolean isMusicEnable, final boolean isLeftAndRightEnable,
                                        final boolean isUpAndDownEnable) {

            this.ckSoundFX.setSelected(isSoundsFxEnable);
            this.ckSound.setSelected(isMusicEnable);
            this.rbUseLeftRight.setSelected(isLeftAndRightEnable);
            this.rbUseUpDown.setSelected(isUpAndDownEnable);
        }

        /**
         * 
         * {@inheritDoc}
         *
         */
        @Override
        public void loadListener() {

            //Button back Listener
            this.btnBack.setOnAction(event -> {
                this.controller.saveNewSettings();
                FXMLMenuController.switchScene((Stage) this.window.getScene().getWindow(), 
                                               PersonalViews.SCENE_MAIN_MENU, PersonalStyle.DEFAULT_STYLE, 
                                               this.window.getScene().getWindow().getWidth(),
                                               this.window.getScene().getWindow().getHeight(), 
                                               true);
             });

            //CheckBox SoundFx Listener
            this.ckSoundFX.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.changeSoundFxState(newV);
                this.soundClick();
            });

            this.ckSound.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.changeMusicState(newV);
                this.soundClick();
            });

            this.rbUseLeftRight.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.useLeftAndRightCommand();
                this.soundClick();
            });

            this.rbUseUpDown.selectedProperty().addListener((obs, oldV, newV) -> {
                this.controller.useUpAndDownCommand();
                this.soundClick();
            });
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
        public void loadFont() {
            this.lblTitle
                .setFont(Font.loadFont(PersonalFonts.FONT_TITLE.getResourceAsStream(), ScreenUtilities.FONT_NORMAL_LABEL_SIZE));
            this.ckSoundFX
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
            this.ckSound
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
            this.rbUseLeftRight
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
            this.rbUseUpDown
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
            this.btnBack
                .setFont(Font.loadFont(PersonalFonts.FONT_BUTTON.getResourceAsStream(), ScreenUtilities.FONT_SUB_LABEL_SIZE));
        }

        /**
         * 
         * {@inheritDoc}
         *
         */
        @Override
        public void loadAnimation() {
            final Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1.00), evt -> this.lblTitle.setVisible(false)),
                    new KeyFrame(Duration.seconds(0.50), evt -> this.lblTitle.setVisible(true)));
                    timeline.setCycleCount(Animation.INDEFINITE);
                    timeline.play();
        }

        /**
         * 
         * {@inheritDoc}
         *
         */
        @Override
        public void resizable() {
            this.panel.setPrefSize(Double.MAX_VALUE, Double.MAX_VALUE);
            this.panel.prefHeightProperty().bind(this.window.heightProperty());
            this.panel.prefWidthProperty().bind(this.window.widthProperty());

            this.ckSoundFX.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
            this.ckSound.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
            this.rbUseLeftRight.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));
            this.rbUseUpDown.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));

            this.btnBack.prefWidthProperty().bind(this.radioButtonContainer.widthProperty().divide(ScreenUtilities.CENTER_DIVIDER));

            this.lblTitle.setWrapText(true);
        }

}
