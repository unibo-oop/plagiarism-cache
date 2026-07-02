package view.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import view.utils.ViewParameters;
import view.loader.stagefactory.StageManager;
import view.mediator.ControllerMediator;
import view.utils.ControllerVariablesNames;

public class ControlPanelController {

    private ViewParameters viewParam;

    private boolean simulationStarted = false;
    private boolean simulationInitialized = false;

    private final ControllerMediator mediator = ControllerMediator.getInstance();

    @FXML
    private VBox controls;
    @FXML
    private Slider north;
    @FXML
    private Slider south;
    @FXML
    private Slider east;
    @FXML
    private Slider west;
    @FXML
    private Slider maxVel;
    @FXML
    private Slider greenLight;

    private final Map<ControllerVariablesNames, Double> values = new HashMap<>();

    /**
     * Method reported to start/stop GUI button, used to start and pause the simulation.
     * When pressed, thanks to mediator pattern, it warns UIcontroler to start the simulation.
     */
    public final void start() {
        if (!simulationStarted) {
            simulationStarted = true;
            if (!simulationInitialized) {
                this.viewParam.setValues(this.values);
                this.deActivateParameters(true);
                simulationInitialized = true;
                mediator.createScenarioInstance();
            } else {
                mediator.startSimulation();
            }
        } else {
            simulationStarted = false;
            mediator.pauseSimulation();
        }
    }

    /**
     * To deactivate parameters choices when pressing the start button.
     * @param flag
     */
    private void deActivateParameters(final boolean flag) {
        this.north.setDisable(flag);
        this.south.setDisable(flag);
        this.west.setDisable(flag);
        this.east.setDisable(flag);
        this.maxVel.setDisable(flag);
        this.greenLight.setDisable(flag);
    }

    /**
     * sets the values of all the different sliders.
     */
    @FXML
    public void setFromNorth() {
        this.values.compute(ControllerVariablesNames.NORTH, (key, val) -> val = this.north.getValue());

        this.viewParam.setValues(this.values);
    }

    @FXML
    public final void setFromSouth() {
        this.values.compute(ControllerVariablesNames.SOUTH, (key, val) -> val = this.south.getValue());

        this.viewParam.setValues(this.values);
    }

    @FXML
    public final void setFromEast() {
        this.values.compute(ControllerVariablesNames.EAST, (key, val) -> val = this.east.getValue());

        this.viewParam.setValues(this.values);
    }

    @FXML
    public final void setFromWest() {
        this.values.compute(ControllerVariablesNames.WEST, (key, val) -> val = this.west.getValue());

        this.viewParam.setValues(this.values);
    }

    @FXML
    public final void setMaxVel() {
        this.values.compute(ControllerVariablesNames.MAXVEL, (key, val) -> val = this.maxVel.getValue());

        this.viewParam.setValues(this.values);
    }

    @FXML
    public final void setGreenLight() {
        this.values.compute(ControllerVariablesNames.GREENLIGHT, (key, val) -> val = this.greenLight.getValue());

        this.viewParam.setValues(this.values);
    }

    /**
     * resets all the parameters about the simulation.
     */
    public void resetParameters() {
        this.values.entrySet().stream().filter(x -> x.getKey().equals(ControllerVariablesNames.MAXVEL))
                .map(x -> x.setValue(4.0));

        this.deActivateParameters(false);

        this.north.setValue(0);
        this.south.setValue(0);
        this.east.setValue(0);
        this.west.setValue(0);
        this.maxVel.setValue(0);
        this.greenLight.setValue(0);

        this.viewParam.setValues(this.values);
    }

    /**
     * resets wizard parameters.
     */
    private void resetWizard() {
        this.values.compute(ControllerVariablesNames.NLANES, (key, val) -> val = (double) 0);
    }

    /**
     * resets completely the simulation.
     */
    public void resetAll() {
        simulationInitialized = false;
        mediator.stopSimulation();
        this.resetParameters();
        this.resetWizard();
        this.controls.setDisable(true);
    }

    /**
     * @throws IOException called by menuBarController, create a new scene for the
     *                     wizard
     */
    public final void operateOn() throws IOException {
        StageManager manager = new StageManager();
        manager.getScenefactory().wizardLoader().load();
    }

    public final void confirmWizardParameters(final ChoiceController controller) {
        this.sliderActivation(controller);
        this.values.compute(ControllerVariablesNames.NLANES, (key, val) -> val = (double) controller.getNlanes());
        this.viewParam.setValues(this.values);
        this.controls.setDisable(false);
    }

    /**
     * sets slider's activation based on road and lane choices.
     *
     * @param controller
     */
    private void sliderActivation(final ChoiceController controller) {
        if (controller.getNlanes().equals(1)) {
            east.setDisable(true);
            north.setDisable(true);
        } 
    }

    /**
     * @return the controls
     */
    public VBox getControls() {
        return this.controls;
    }

    /**
     * @return the viewParam
     */
    public ViewParameters getViewParam() {
        return this.viewParam;
    }

    /**
     * @param viewParam the viewParam to set
     */
    public void setViewParam(final ViewParameters viewParam) {
        this.viewParam = viewParam;
    }

}
