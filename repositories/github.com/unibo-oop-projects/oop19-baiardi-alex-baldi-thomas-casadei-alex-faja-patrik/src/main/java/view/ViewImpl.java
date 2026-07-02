package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.SimulationController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * 
 * 
 *
 */
public class ViewImpl implements View, Initializable {

    @FXML
    private TabPane tabPanel;

    @FXML
    private Tab tabSettings;

    @FXML
    private Tab tabMap;

    @FXML
    private Tab tabCharts;

    @FXML
    private Button btnStop;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnNewSimulation;

    private SimulationController simulationController;

    @FXML
    private EnvironmentSetup settingController;

    @FXML
    private VirusSetupImpl virusSetupController;

    @FXML
    private ChartsImpl chartsController;

    @FXML
    private SimulationViewImpl simulationViewController;

    private int numberSimulation;

    /**
     * Start the simulationController.
     */
    @FXML
    public void startSimulation() {
        this.btnStop.setDisable(false);
        this.btnStart.setDisable(true);
        this.btnNewSimulation.setDisable(true);
        this.simulationController.start();
    }

    /**
     * Stop the simulationController.
     */
    @FXML
    public void stopSimulation() {
        this.btnStop.setDisable(true);
        this.btnStart.setDisable(false);
        this.btnNewSimulation.setDisable(false);
        this.simulationController.stop();
    }

    /**
     * set all variable for a new incoming simulationController.
     */
    @FXML
    public void newSimulation() {
        if (runnableCheck()) {
            numberSimulation++;
            if (numberSimulation > 1) {
                try {
                    final FXMLLoader mapLoader = new FXMLLoader(ClassLoader.getSystemResource("scene/Map.fxml"));
                    final Parent root = mapLoader.load();
                    simulationViewController = mapLoader.getController();
                    simulationViewController.setController(simulationController);
                    tabMap.setContent(root);
                    final FXMLLoader chartsLoader = new FXMLLoader(ClassLoader.getSystemResource("scene/Charts.fxml"));
                    final Parent chartRoot = chartsLoader.load();
                    chartsController = chartsLoader.getController();
                    tabCharts.setContent(chartRoot);
                } catch (IOException e) {
                    final Alert alert = new Alert(AlertType.ERROR, "LOADING ERROR");
                    alert.show();
                    e.printStackTrace();
                }
            }
            try {
                this.simulationController.newSimulation(settingController.getInitialSetting());
                this.tabMap.setDisable(false);
                this.tabCharts.setDisable(false);
                this.btnStart.setDisable(false);
                this.btnStop.setDisable(true);
                this.tabPanel.getSelectionModel().select(tabMap);
            } catch (Exception e) {
                final Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("TOO MANY MEETINGPALCES");
                alert.show();
            }
        } else {
            final Alert alert = new Alert(AlertType.ERROR);
            alert.setHeaderText(
                    "Initial people value can't be zero \nInitial infected value can't be zero or higher than intial people");
        }
    }

    /**
     * check the textField value to ensure that is not zero or the infected are more
     * than people.
     * 
     * @return boolean
     */
    private boolean runnableCheck() {
        final InitialSettings s = settingController.getInitialSetting();
        return s.getnInitialPeople() > 0
                && (s.getnInitialInfected() > 0 && s.getnInitialInfected() < s.getnInitialPeople())
                && s.getnMeetingPlace() >= 0;
    }

    /**
     *
     */
    @Override
    public void simulationResult(final boolean result) {
        Platform.runLater(() -> {
            final Alert alert = new Alert(AlertType.INFORMATION);
            alert.setHeaderText(result ? "the virus has been eradicated" : "people didn't survive the virus");
            alert.showAndWait();
        });
        this.btnStop.setDisable(true);
        this.btnStart.setDisable(true);
        this.btnNewSimulation.setDisable(false);
    }

    /**
     *
     */
    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        numberSimulation = 0;
    }

    /**
     * 
     */
    @Override
    public ChartsImpl getCharts() {
        return this.chartsController;
    }

    /**
     * 
     */
    @Override
    public SimulationView getSimulationView() {
        return this.simulationViewController;
    }

    /**
     * 
     */
    @Override
    public VirusSetup getVirusSetup() {
        return this.virusSetupController;
    }

    /**
     * 
     */
    @Override
    public void setController(final SimulationController simController) {
        this.simulationController = simController;
    }
}
