package view;

import java.awt.Toolkit;

import controller.MenuController;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;

/**
 * 
 * This Class create the Options Menu View.
 *
 */
public class OptionsView extends StackPane {

    private static final double PADDING = Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 20;
    private static final double FONT_SIZE = Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.025;

    private final MenuTitle title = new MenuTitle(3.7, 4);
    private final MenuBox optionsBox = new MenuBox();
    private final Label musicLabel = new Label("MUSIC VOLUME");
    private final Label sfxLabel = new Label("SFX VOLUME");
    private final Slider musicSlider;
    private final Slider sfxSlider;
    private final MenuButton back = new MenuButton("BACK");

    /**
     * 
     * @param controller The menu controller.
     * @param scene      The main scene.
     * @param musicVol   The actual value of music volume.
     * @param sfxVol     The actual value of sfx volume.
     */
    public OptionsView(final MenuController controller, final Scene scene, final double musicVol, final double sfxVol) {
        super();

        musicLabel.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
        musicSlider = new Slider(0.0, 1.0, musicVol);
        musicSlider.setMaxWidth(back.getMaxWidth());
        musicSlider.setPadding(new Insets(0, 0, PADDING, 0));
        musicSlider.setStyle("-track-color: linear-gradient(to right, #ff0000 " + musicVol * 100 + "%, white "
                + musicVol * 100 + ("%);"));
        musicSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double percentage = 100.0 * (newVal.doubleValue() - musicSlider.getMin())
                    / (musicSlider.getMax() - musicSlider.getMin());
            musicSlider.setStyle("-track-color: linear-gradient(to right, #ff0000 " + percentage + "%, white "
                    + percentage + ("%);"));
            controller.musicVolumeChanger(newVal.doubleValue());
        });

        sfxLabel.setStyle(String.format("-fx-font-size: %dpx;", (int) ((FONT_SIZE))));
        sfxSlider = new Slider(0.0, 1.0, sfxVol);
        sfxSlider.setMaxWidth(back.getMaxWidth());
        sfxSlider.setPadding(new Insets(0, 0, PADDING, 0));
        sfxSlider.setStyle("-track-color: linear-gradient(to right, #ff0000 " + sfxVol * 100 + "%, white "
                + sfxVol * 100 + ("%);"));
        sfxSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            double percentage = 100.0 * (newVal.doubleValue() - sfxSlider.getMin())
                    / (sfxSlider.getMax() - sfxSlider.getMin());
            sfxSlider.setStyle("-track-color: linear-gradient(to right, #ff0000 " + percentage + "%, white "
                    + percentage + ("%);"));
            controller.sfxVolumeChanger(newVal.doubleValue());
        });

        this.back.setOnAction(e -> {
            controller.goToMainMenu();
        });

        this.optionsBox.getChildren().addAll(musicLabel, musicSlider, sfxLabel, sfxSlider, back);
        this.getChildren().addAll(title, optionsBox);
        this.setId("mainPane");
        this.getStylesheets().add("style.css");
        scene.setRoot(this);
    }
}
