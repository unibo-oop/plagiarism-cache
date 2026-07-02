package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/**
 *
 */
public class EnvironmentSetup implements Initializable {

    @FXML
    private TextField txtNumberOfPeople;

    @FXML
    private TextField txtNumberOfMettingPlaces;

    @FXML
    private TextField txtInfectedPerson;

    @FXML
    private Slider slBrithRate;

    @FXML
    private Slider slDeathRate;

    @FXML
    private Slider slTendencyToHome;

    @FXML
    private Label lblBirthRate;

    @FXML
    private Label lblDeathRate;

    @FXML
    private Label lblTendencyToHome;

    /**
     * initialize the label for visualize the value of the slider.
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
     * initialize the label for visualize a double value of the slider.
     * 
     * @param slider the slider who change value
     * @param label  the label that have to display the value of the slider
     */
    private void initDoubleSlider(final Slider slider, final Label label) {
        label.textProperty().bind(slider.valueProperty().asString("%.1f"));
    }

    /**
     * 
     * @return the initial setting for the simulation (not the Virus setting)
     */
    public InitialSettings getInitialSetting() {
        final InitialSettings setting;
        int nInitialPeople = 0;
        int nInitialInfected = 0;
        int nMeetingPlace = 0;
        try {
            nInitialPeople = Integer.parseInt(txtNumberOfPeople.getText());
            nInitialInfected = Integer.parseInt(txtInfectedPerson.getText());
            nMeetingPlace = Integer.parseInt(txtNumberOfMettingPlaces.getText());
        } catch (NumberFormatException e) {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText("INSERT VALID VALUES");
            alert.show();
        }
        final double birthRate = slBrithRate.getValue();
        final double deathRate = slDeathRate.getValue();
        final double tendencyRate = slTendencyToHome.getValue();
        setting = new InitialSettings(nInitialPeople, nInitialInfected, nMeetingPlace, birthRate, deathRate,
                tendencyRate);
        return setting;
    }

    /**
     * allow a TextField only to accept digits.
     * 
     * @param textfield the TextField to check
     */
    private void numTextField(final TextField textfield) {
        textfield.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue,
                    final String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    textfield.setText(oldValue);
                }
            }
        });
    }

    /**
     * 
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        initDoubleSlider(slBrithRate, lblBirthRate);
        initDoubleSlider(slDeathRate, lblDeathRate);
        initSlider(slTendencyToHome, lblTendencyToHome);
        numTextField(txtInfectedPerson);
        numTextField(txtNumberOfMettingPlaces);
        numTextField(txtNumberOfPeople);
    }

}
