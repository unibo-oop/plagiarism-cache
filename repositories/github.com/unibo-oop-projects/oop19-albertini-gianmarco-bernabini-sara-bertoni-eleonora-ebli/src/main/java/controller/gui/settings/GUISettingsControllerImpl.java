package controller.gui.settings;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import view.SceneManager;
import view.Scenes;

/**
 * The Controller related to the settings.fxml GUI.
 *
 */
public class GUISettingsControllerImpl implements Initializable, GUISettingsController {
    private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private static final String FULL_SCREEN = "FULL SCREEN";
    private static final String BIG = "90%";
    private static final String MEDIUM = "75%";
    private static final String SMALL = "60%";

    private enum ScreenSizes {
        FULL_SCREEN("FULL SCREEN", 1.0),
        BIG("90%", 0.9),
        MEDIUM("75%", 0.75),
        SMALL("60%", 0.6);

        private final String label;
        private final Double value;

        ScreenSizes(final String string, final double d) {
            this.label = string;
            this.value = d;
        }

        public String getLabel() {
            return this.label;
        }

        public Double getValue() {
            return this.value;
        }
    }

    private final ObservableList<String> screenSizeList = FXCollections.observableArrayList(ScreenSizes.FULL_SCREEN.getLabel(), ScreenSizes.BIG.getLabel(), ScreenSizes.MEDIUM.getLabel(), ScreenSizes.SMALL.getLabel());

    @FXML
    private ImageView img;

    @FXML
    private Button exitBtn;

    @FXML
    private ComboBox<String> screenSizeBox;

    @FXML
    private CheckBox musicBox;

    @FXML
    private CheckBox soundBox;

    @Override
    public final void initialize(final URL location, final ResourceBundle resources) {
        SceneManager.setSceneBackground(img);

        screenSizeBox.setValue(ScreenSizes.BIG.getLabel());
        screenSizeBox.setItems(screenSizeList);
    }

    /**
     * 
     * @return secreenSizeBox.value
     *      Screen size set at the moment in double
     */
    @Override
    public final Double getScreenSizeDimensions() {
        switch (screenSizeBox.getValue()) {
        case FULL_SCREEN: 
            return ScreenSizes.FULL_SCREEN.getValue();
        case BIG:
            return ScreenSizes.BIG.getValue();
        case MEDIUM:
            return ScreenSizes.MEDIUM.getValue();
        case SMALL:
            return ScreenSizes.SMALL.getValue();
        default:
            System.out.println("Error, screenSizeSettings not detected.");
            return 0.0;
        }
    }

    /**
     * 
     * @return musicSettings
     *      Current settings for music
     */
    @Override
    public final boolean isMusicSettings() {
        return this.musicBox.isSelected();
    }

    /**
     * 
     * @return soundSettings
     *      Current settings for sounds
     */
    @Override
    public final boolean isSoundSettings() {
        return this.soundBox.isSelected();
    }

    /**
     * 
     * @return screenSize
     *      Current settings for screen
     */
    @Override
    public final String getScreenSize() {
        return this.screenSizeBox.getValue();
    }

    /**
     * 
     * @param music
     *      Sets the starting music setting
     * @param sound
     *      Sets the starting sound setting
     * @param screenSize
     *      Sets the starting screen size setting
     */
    @Override
    public final void initializeSettings(final boolean music, final boolean sound, final String screenSize) {
        this.musicBox.setSelected(music);
        this.soundBox.setSelected(sound);
        this.screenSizeBox.setValue(screenSize);
    }

    @FXML
    @Override
    public final void setMusicSettings() {
        if (this.musicBox.isSelected()) {
            Main.getBackgroundMusic().loop();
        } else {
            Main.getBackgroundMusic().stop();
        }
    }

    @FXML
    @Override
    public final void screenSizeSelection() {
        String screenSizeChoice;
        final Stage stage = (Stage) exitBtn.getScene().getWindow();
        screenSizeChoice = screenSizeBox.getSelectionModel().getSelectedItem();
        switch (screenSizeChoice) {
            case FULL_SCREEN: 
                stage.setWidth(SCREEN_SIZE.getWidth());
                stage.setHeight(SCREEN_SIZE.getHeight());
                stage.setX(0);
                stage.setY(0);
                break;
            case BIG:
                stage.setWidth(SCREEN_SIZE.getWidth() * ScreenSizes.BIG.getValue());
                stage.setHeight(SCREEN_SIZE.getHeight() * ScreenSizes.BIG.getValue());
                break;
            case MEDIUM:
                stage.setWidth(SCREEN_SIZE.getWidth() * ScreenSizes.MEDIUM.getValue());
                stage.setHeight(SCREEN_SIZE.getHeight() * ScreenSizes.MEDIUM.getValue());
                break;
            case SMALL:
                stage.setWidth(SCREEN_SIZE.getWidth() * ScreenSizes.SMALL.getValue());
                stage.setHeight(SCREEN_SIZE.getHeight() * ScreenSizes.SMALL.getValue());
                break;
            default:
                System.out.println("Error, screenSizeSettings not detected.");
                break;
        }
    }

    @FXML
    @Override
    public final void exitBtnOnClickHandler() throws IOException {
        SceneManager.showScene(Scenes.MENU.getNewScene());
    }
}
