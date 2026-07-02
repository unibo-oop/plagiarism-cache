package view;

import java.net.URL;
import java.util.ResourceBundle;
import controller.RegolationHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import view.util.language.LanguageManagerUtils;

/**
 * View controller of Regolation.
 */
public class RegolationsController implements Initializable {

    // field of this object
    @FXML
    private ResourceBundle resources;
    @FXML
    private Label labelRegolations;
    @FXML
    private TitledPane titledPaneBrightness;
    @FXML
    private TitledPane titledPaneContrast;
    @FXML
    private TitledPane titledPaneSaturation;
    @FXML
    private TitledPane titledPaneTemperature;
    @FXML
    private TitledPane titledPaneExposure;

    private RegolationHandler regolationHandler;

    private static final String BRIGHTNESS = "Brightness";
    private static final String CONTRAST = "Contrast";
    private static final String SATURATION = "Saturation";
    private static final String TEMPERATURE = "Temperature";
    private static final String EXPOSURE = "Exposure";

    /**
     * elect regolation: Brightness.
     */
    public void selectBrightness() {
        regolationHandler.setRegolation(BRIGHTNESS);
        this.reinitialize();
    }

    /**
     * Select regolation: Contrast.
     */
    public void selectContrast() {
        regolationHandler.setRegolation(CONTRAST);
        this.reinitialize();
    }

    /**
     * Select regolation: Saturation.
     */
    public void selectSaturation() {
        regolationHandler.setRegolation(SATURATION);
        this.reinitialize();
    }

    /**
     * Select regolation: Temperature.
     */
    public void selectTemperature() {
        regolationHandler.setRegolation(TEMPERATURE);
        this.reinitialize();
    }

    /**
     * Select regolation: Exposure.
     */
    public void selectExposure() {
        regolationHandler.setRegolation(EXPOSURE);
        this.reinitialize();
    }

    /**
     * Change content of titledpanes with new RegolationSettings.
     */
    public void reinitialize() {
        titledPaneBrightness.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneContrast.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneSaturation.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneTemperature.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneExposure.setContent(regolationHandler.setPaneSettings().getPaneSettings());
    }

    /**
     * Close panes with setExpanded(false).
     */
    public void closePanes() {
        titledPaneBrightness.setExpanded(false);
        titledPaneContrast.setExpanded(false);
        titledPaneSaturation.setExpanded(false);
        titledPaneTemperature.setExpanded(false);
        titledPaneExposure.setExpanded(false);
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        regolationHandler = RegolationHandler.getRegolationHandler();
        regolationHandler.setRegolationsController(this);
        labelRegolations.textProperty().bind(LanguageManagerUtils.createStringBinding("label.regolations"));
        titledPaneBrightness.textProperty().bind(LanguageManagerUtils.createStringBinding("label.brightness"));
        titledPaneBrightness.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneContrast.textProperty().bind(LanguageManagerUtils.createStringBinding("label.contrast"));
        titledPaneContrast.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneSaturation.textProperty().bind(LanguageManagerUtils.createStringBinding("label.saturation"));
        titledPaneSaturation.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneTemperature.textProperty().bind(LanguageManagerUtils.createStringBinding("label.temperature"));
        titledPaneTemperature.setContent(regolationHandler.setPaneSettings().getPaneSettings());
        titledPaneExposure.textProperty().bind(LanguageManagerUtils.createStringBinding("label.exposure"));
        titledPaneExposure.setContent(regolationHandler.setPaneSettings().getPaneSettings());

    }

}
