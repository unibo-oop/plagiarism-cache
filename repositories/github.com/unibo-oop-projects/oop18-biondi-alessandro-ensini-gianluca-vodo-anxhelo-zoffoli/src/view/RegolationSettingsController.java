package view;

import java.net.URL;
import java.util.ResourceBundle;

import controller.RegolationHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

/**
 * View controller of Regolation.
 */
public class RegolationSettingsController implements Initializable {
    // field of this object
    @FXML
    private ResourceBundle resources;
    @FXML
    private Label labelValueOfRegolation;
    @FXML
    private Button buttonDecrement;
    @FXML
    private Slider sliderRegolation;
    @FXML
    private Button buttonIncrement;
    @FXML
    private Button buttonApply;
    @FXML
    private Button buttonCancel;

    private static final String DEFAULTVALUE = "0";
    private static final float MIN = -100f;
    private RegolationHandler regolationHandler;

    /**
     * Set regolation to preview.
     */
    public void setRegolation() {
        regolationHandler.showPreview(sliderRegolation.getValue());
    }

    /**
     * Show image after regolation increment.
     */
    public void increment() {
        sliderRegolation.increment();
        regolationHandler.showPreview(sliderRegolation.getValue());
    }

    /**
     * Show image after regolation decrement.
     */
    public void decrement() {
        sliderRegolation.decrement();
        regolationHandler.showPreview(sliderRegolation.getValue());
    }

    /**
     * Apply regolation selected.
     */
    public void apply() {
        regolationHandler.apply();
        regolationHandler.close();
    }

    /**
     * Cancel preview.
     */
    public void cancel() {
        regolationHandler.cancel();
        regolationHandler.close();
    }

    /**
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     *      java.util.ResourceBundle)
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        regolationHandler = RegolationHandler.getRegolationHandler();
        labelValueOfRegolation.setText(DEFAULTVALUE);
        buttonDecrement.setText("-");
        initializeSlider();
        buttonIncrement.setText("+");
        buttonApply.textProperty().bind(view.util.language.LanguageManagerUtils.createStringBinding("button.apply"));
        buttonCancel.textProperty().bind(view.util.language.LanguageManagerUtils.createStringBinding("button.cancel"));

    }

    private void initializeSlider() {
        sliderRegolation.setBlockIncrement(1);
        sliderRegolation.setMin(MIN);
        // listener with lambda expression
        sliderRegolation.valueProperty().addListener((obs, oldValue, newValue) -> {
            if (oldValue != newValue) {
                // set text of label
                labelValueOfRegolation.setText(String.valueOf((int) sliderRegolation.getValue()));
            }
        });
    }
}
