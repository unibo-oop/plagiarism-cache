package view.mediator;

import java.io.IOException;

import view.controllers.ChoiceController;
import view.controllers.ControlPanelController;
import view.controllers.MenuBarController;
import view.controllers.UIController;
import view.utils.ViewParameters;

public class ControllerMediator implements IMediateControllers {

    private MenuBarController menuBarController;
    private ControlPanelController controlPanelController;
    private UIController uiController;

    /**
     * @param controller used to register MenuBarController into the mediator
     */
    @Override
    public void registerMenuBarController(final MenuBarController controller) {
        this.menuBarController = controller;

    }

    /**
     * @param controller used to register controlPanelController into the mediator
     */
    @Override
    public void registerControlPanelController(final ControlPanelController controller) {
        this.controlPanelController = controller;
    }

    /**
     * @param controller used to register UIController into the mediator
     */
    public void registerUIController(final UIController controller) {
        this.uiController = controller;
    }

    /**
     * ControlPanel can call this method. to tell menuBar to do something
     */
    @Override
    public void menuBarControllerDoSomething() {
        this.menuBarController.doSomething();
    }

    /**
     * @throws IOException this method is called from menuBar, and tells
     *                     controlPanel to operate on something
     */
    @Override
    public void controlPanelControllerOperateOn() throws IOException {
        this.controlPanelController.operateOn();
    }

    public final void controlPanelSetViewParam(final ViewParameters viewParam) {
        this.controlPanelController.setViewParam(viewParam);
    }

    /**
     * this method is called from wizard controller,and tells controlPanel to
     * confirm some parameters.
     *
     * @param controller the wizard controller instance
     */
    public final void controlPanelControllerConfirmedWizardParameters(final ChoiceController controller) {
        this.controlPanelController.confirmWizardParameters(controller);
    }

    /**
     * this method is called from controlPanel and tells UIController to create
     * scenario instance.
     */
    public final void createScenarioInstance() {
        this.uiController.createScenarioInstance();
    }

    /**
     * @return a new mediator instance
     */
    public static ControllerMediator getInstance() {
        return ControllerMediatorHolder.INSTANCE;
    }

    /**
     * @create a new ControllerMediator instance
     */
    private static class ControllerMediatorHolder {
        private static final ControllerMediator INSTANCE = new ControllerMediator();
    }

    /**
     * {@inheritDoc}
     */
    public final void startSimulation() {
        this.uiController.startSimulation();
    }

    /**
     * {@inheritDoc}
     */
    public final void pauseSimulation() {
        this.uiController.pauseSimulation();
    }

    /**
     * {@inheritDoc}
     */
    public final void stopSimulation() {
        this.uiController.stopSimulation();
    }

}
