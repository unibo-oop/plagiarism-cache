package view.mediator;

import java.io.IOException;

import view.controllers.ChoiceController;
import view.controllers.ControlPanelController;
import view.controllers.MenuBarController;
import view.controllers.UIController;
import view.utils.ViewParameters;

public interface IMediateControllers {

    /**
     *
     * @param controller new menu bar controller
     */
    void registerMenuBarController(MenuBarController controller);

    /**
     *
     * @param controller new control panel controller
     */
    void registerControlPanelController(ControlPanelController controller);

    /**
    *
    * @param controller new UIcontroller
    */
   void registerUIController(UIController controller);

    /**
     * To do something on Menu bar controller.
     */
    void menuBarControllerDoSomething();

    /**
     * To do something on control panel controller.
     * @throws IOException if controlPanelController does not load correctly wizard
     */
    void controlPanelControllerOperateOn() throws IOException;

    /**
     * Used by UIController to set ViewParam instance on ControlPanelController.
     * @param viewParam the parameters to be set
     */
    void controlPanelSetViewParam(ViewParameters viewParam);

    /**
     * Used by Wizard controller to confirm and send parameters to Control Panel Controller.
     * @param controller the wizard controller instance
     */
    void controlPanelControllerConfirmedWizardParameters(ChoiceController controller);

    /**
     * Called by ControlPanelController when "start/stop" button is pressed for the first time.
     */
    void startSimulation();

    /**
     * Called by ControlPanelController when "start/stop is pressed, and it pause the simulation.
     */
    void pauseSimulation();

    /**
     * Called by ControlPanelController when the user press "reset All" button on the interface.
     */
    void stopSimulation();
}
