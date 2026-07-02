package view.menu;

import controller.SceneManager;
import controller.SoundManager;
import controller.SoundManagerImpl;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import utilities.Utilities;

/**
 * Class of Settings Menu.
 *
 */
// CHECKSTYLE: MagicNumber OFF
public final class SettingsMenu {

    private static final SettingsMenu SINGLETON = new SettingsMenu();
    private final SoundManager soundManager;
    private final Scene settingsScene;
    private final Slider volumeSlider;

    /**
     * Constructor that creates all elements that appear on screen in Settings Menu.
     */
    private SettingsMenu() {
        this.soundManager = SoundManagerImpl.get();
        final StackPane root = new StackPane();
        this.settingsScene = new Scene(root);
        final Button returnBtn = new Button("Back to menu");
        final VBox buttonContainer = new VBox();
        final ImageView backgroundSettingsMenu = new ImageView(new Image("/images/backgroundSettingsMenu.png"));
        this.volumeSlider = new Slider();
        final FadeTransition buttonContainerFadeIn = new FadeTransition(Duration.millis(1000), buttonContainer);

        this.settingsScene.getStylesheets().add(this.getClass().getResource("styleSettings.css").toExternalForm());
        returnBtn.setId("returnBtn");

        backgroundSettingsMenu.fitWidthProperty().bind(root.widthProperty());
        backgroundSettingsMenu.fitHeightProperty().bind(root.heightProperty());

        returnBtn.setPrefWidth(Utilities.W / 3);
        returnBtn.setPrefHeight(Utilities.H / 12);
        returnBtn.setStyle(String.format("-fx-font-size: %dpx;", (int) (Utilities.H / 24)));

        buttonContainer.getChildren().add(returnBtn);
        buttonContainer.setAlignment(Pos.BOTTOM_CENTER);
        buttonContainer.setPadding(new Insets(0, 0, Utilities.H / 5, 0));
        buttonContainer.setSpacing(Utilities.H / 50);

        buttonContainerFadeIn.setFromValue(0.0);
        buttonContainerFadeIn.setToValue(1.0);
        buttonContainerFadeIn.play();

        this.volumeSlider.setMin(0);
        this.volumeSlider.setMax(1);
        this.volumeSlider.setMaxWidth(Utilities.W / 5);
        this.volumeSlider.setTranslateY(Utilities.H / 37);

        this.volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(final ObservableValue<? extends Number> ov, final Number oldVal, final Number newVal) {
                soundManager.updateMusicVolume(newVal.doubleValue());
            }
        });

        root.getChildren().add(backgroundSettingsMenu);
        root.getChildren().add(buttonContainer);
        root.getChildren().add(this.volumeSlider);

        returnBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                SceneManager.get().sceneSetter(StartMenu.get().getScene());
            }
        });

        returnBtn.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent e) {
                soundManager.startEffect("/songs/buttonSound.mp3");
            }
        });
    }

    /**
     * Updates the value of the volume. It allows the player to set his/her
     * preferred volume.
     */
    public void updateVolumeSlider() {
        this.volumeSlider.setValue(this.soundManager.getMusicVolume());
    }

    /**
     * Getter of the Settings Menu's scene.
     * 
     * @return settingsScene
     */
    public Scene getScene() {
        return this.settingsScene;
    }

    /**
     * Returns an instance of SettingsMenu.
     * 
     * @return an instance of SettingsMenu
     */
    public static SettingsMenu get() {
        return SINGLETON;
    }
}
