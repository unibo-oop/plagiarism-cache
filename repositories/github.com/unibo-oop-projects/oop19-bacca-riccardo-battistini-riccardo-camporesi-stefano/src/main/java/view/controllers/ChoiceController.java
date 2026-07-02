package view.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import view.mediator.ControllerMediator;

public final class ChoiceController implements Initializable {

    @FXML
    private ComboBox<Integer> nlanes;
    @FXML
    private Button closeButton;

    /**
     * To confirm and send selected parameters to ControlPanelController, thanks to mediator.
     */
    public void confirmParameters() {
        ControllerMediator.getInstance().controlPanelControllerConfirmedWizardParameters(this);
        this.closeWizard();
    }

    /**
     * To close wizard's stage.
     */
    public void closeWizard() {
        final Stage stage = (Stage) this.closeButton.getScene().getWindow();
        stage.close();
    }

    /**
     * @param location the fxml location
     * @param resources the resources
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final ObservableList<Integer> list = FXCollections.observableArrayList(1, 2);
        this.nlanes.setItems(list);
    }

    /**
     * @return the number of lanes.
     */
    public Integer getNlanes() {
        return this.nlanes.getValue();
    }

}
