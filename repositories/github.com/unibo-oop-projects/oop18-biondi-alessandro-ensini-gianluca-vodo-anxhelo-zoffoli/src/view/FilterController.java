package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.FilterHandler;
import controller.HistoryHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import view.util.language.LanguageManagerUtils;

/**
 * Contoller of the Filter.
 */
public class FilterController implements Initializable {

    private final FilterHandler fHandler = new FilterHandler();
    @FXML
    private Label labelFilters;
    @FXML
    private Button buttonBW;
    @FXML
    private Button buttonGS;
    @FXML
    private Button buttonN;
    @FXML
    private Button buttonS;
    @FXML
    private Label labelCF;
    @FXML
    private Slider sliderRed;
    @FXML
    private Slider sliderGreen;
    @FXML
    private Slider sliderBlue;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonCancel;

    /**
     * Apply the Black and White filter.
     */
    public void showBW() {
        resetSliders();
        fHandler.showBlackAndWhite();
    }

    /**
     * Apply the Gray Scale filter.
     */
    public void showGS() {
        resetSliders();
        fHandler.showGrayScale();
    }

    /**
     * Apply the Negative filter.
     */
    public void showN() {
        resetSliders();
        fHandler.showNegative();
    }

    /**
     * Apply the Sepia filter.
     */
    public void showS() {
        resetSliders();
        fHandler.showSepia();
    }

    /**
     * Show the red ColorFilter effect with the given value.
     * 
     * @param value of the slider
     */
    public void showCFR(final int value) {
        fHandler.showColorFilterRed(value);
    }

    /**
     * Show the green ColorFilter effect with the given value.
     * 
     * @param value of the slider
     */
    public void showCFG(final int value) {
        fHandler.showColorFilterGreen(value);
    }

    /**
     * Show the blue ColorFilter effect with the given value.
     * 
     * @param value of the slider
     */
    public void showCFB(final int value) {
        fHandler.showColorFilterBlue(value);
    }

    /**
     * Apply the shown filter.
     */
    public void applyFilter() {
        fHandler.applyFilter();
    }

    /**
     * Cancel the shown filter.
     */
    public void cancelFilter() {
        resetSliders();
        MainWindowController.setImage(HistoryHandler.getHistoryHandler().getCurrentImage());
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL arg0, final ResourceBundle arg1) {

        labelFilters.textProperty().bind(LanguageManagerUtils.createStringBinding("button.filter"));
        buttonBW.textProperty().bind(LanguageManagerUtils.createStringBinding("button.BW"));
        buttonGS.textProperty().bind(LanguageManagerUtils.createStringBinding("button.GS"));
        buttonN.textProperty().bind(LanguageManagerUtils.createStringBinding("button.N"));
        buttonS.textProperty().bind(LanguageManagerUtils.createStringBinding("button.S"));
        buttonApply.textProperty().bind(LanguageManagerUtils.createStringBinding("button.applyFilter"));
        buttonCancel.textProperty().bind(LanguageManagerUtils.createStringBinding("button.cancel"));
        labelCF.textProperty().bind(LanguageManagerUtils.createStringBinding("label.CF"));
        sliderRed.setBlockIncrement(1);
        sliderRed.valueProperty().addListener((observable, oldValue, newValue) -> showCFR(newValue.intValue()));
        sliderGreen.valueProperty().addListener((observable, oldValue, newValue) -> showCFG(newValue.intValue()));
        sliderBlue.valueProperty().addListener((observable, oldValue, newValue) -> showCFB(newValue.intValue()));
    }

    private void resetSliders() {
        sliderRed.setValue(0);
        sliderGreen.setValue(0);
        sliderBlue.setValue(0);
    }

}
