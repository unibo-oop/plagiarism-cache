package view;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;
import org.controlsfx.control.RangeSlider;
import controller.VirusManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

/**
 *
 */
public class VirusSetupImpl implements VirusSetup, Initializable {

    @FXML
    private TextField txtVirusName;

    @FXML
    private Slider slInfectivity;

    @FXML
    private Label lblInfectivityValue;

    @FXML
    private Label lblMortalityValue;

    @FXML
    private Slider slMortality;

    @FXML
    private RangeSlider slIncubationPeriod;

    @FXML
    private Label lblIncubationMaxValue;

    @FXML
    private RangeSlider slRecoveryTime;

    @FXML
    private Label lblRecoveryMaxValue;

    @FXML
    private ComboBox<String> cmbSelecVirus;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblIncubationMinValue;

    @FXML
    private Label lblRecoveryMinValue;

    private VirusManager virusManager;

    private Alert alert;

    /**
     * 
     */
    @FXML
    public void changeVirus() {
        if (cmbSelecVirus.getValue() != null) {
          virusManager.loadVirusSettings(cmbSelecVirus.getValue());
          txtVirusName.setText(cmbSelecVirus.getValue());
        }
    }

    /**
     * 
     */
    @FXML
    public void saveVirus() {
        if (!txtVirusName.getText().isBlank()) {
            virusManager.saveVirus();
            cmbSelecVirus.setValue(txtVirusName.getText());
        } else {
            alert.show();
        }
    }

    /**
     * 
     */
    @Override
    public void setController(final VirusManager vm) {
        this.virusManager = vm;
    }

    /**
     * 
     */
    @Override
    public String getName() {
        return txtVirusName.getText();
    }

    /**
     * 
     */
    @Override
    public double getInfectivity() {
        return slInfectivity.getValue();
    }

    /**
     * 
     */
    @Override
    public int getMinIncubationPeriod() {
        return (int) slIncubationPeriod.getLowValue();
    }

    /**
     * 
     */
    @Override
    public int getMaxIncubationPeriod() {
        return (int) slIncubationPeriod.getHighValue();
    }

    /**
     * 
     */
    @Override
    public int getMinRecoveryPeriod() {
        return (int) slRecoveryTime.getLowValue();
    }

    /**
     * 
     */
    @Override
    public int getMaxRecoveryPeriod() {
        return (int) slRecoveryTime.getHighValue();
    }

    /**
     * 
     */
    @Override
    public double getMortality() {
        return slMortality.getValue();
    }

    /**
     * 
     */
    @Override
    public void setInfectity(final double infectivity) {
        slInfectivity.setValue(infectivity);
    }

    /**
     * 
     */
    @Override
    public void setIncubationPeriod(final int minIncubationPeriod, final int maxIncubationPeriod) {
        slIncubationPeriod.setLowValue(minIncubationPeriod);
        slIncubationPeriod.setHighValue(maxIncubationPeriod);
    }

    /**
     * 
     */
    @Override
    public void setRecoveryPeriod(final int minRecoveryPeriod, final int maxRecoveryPeriod) {
        slRecoveryTime.setLowValue(minRecoveryPeriod);
        slRecoveryTime.setHighValue(maxRecoveryPeriod);
    }

    /**
     * 
     */
    @Override
    public void setMortality(final double mortality) {
        slMortality.setValue(mortality);
    }

    /**
     * 
     */
    @Override
    public void setName(final String name) {
        txtVirusName.setText(name);
    }

    /**
     * Initialize the label for visualize the value of the slider.
     * 
     * @param slider the slider who change value
     * @param label  the label that have to display the value of the slider
     */
    private void initSlider(final Slider slider, final Label label) {
        slider.valueProperty().addListener((observable, oldValue, newValue) -> {
            label.setText(Double.toString(newValue.intValue()));
        });
    }

    /**
     * Initialize the label for visualize the value of the rangeSlider.
     * 
     * @param slider        the slider who change value
     * @param minValueLabel the label that display the min value of the slider
     * @param maxValueLabel the label that display the max value of the slider
     */
    private void initRangeSlider(final RangeSlider slider, final Label minValueLabel, final Label maxValueLabel) {
        slider.highValueProperty().addListener((observable, oldValue, newValue) -> {
            maxValueLabel.setText(Double.toString(newValue.intValue()));
        });
        slider.lowValueProperty().addListener((observable, oldValue, newValue) -> {
            minValueLabel.setText(Double.toString(newValue.intValue()));
        });
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initSlider(slInfectivity, lblInfectivityValue);
        initSlider(slMortality, lblMortalityValue);
        initRangeSlider(slIncubationPeriod, lblIncubationMinValue, lblIncubationMaxValue);
        initRangeSlider(slRecoveryTime, lblRecoveryMinValue, lblRecoveryMaxValue);
        alert = new Alert(AlertType.ERROR);
        alert.setHeaderText("The virus name field can't be blank");

    }

    /**
     *
     */
    @Override
    public void uploadVirus(final Collection<String> virusNames) {
        cmbSelecVirus.getItems().clear();
        cmbSelecVirus.getItems().addAll(virusNames);
    }

}
