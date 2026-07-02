package controller;

import controller.engine.SimulationLoop;
import controller.engine.SimulationLoopThread;
import model.Scenario;
import view.View;

/**
 * Concrete implementation of the Controller.
 */
public class ControllerImpl implements Controller {

    private final Scenario scenario;
    private final View view;
    private final SimulationLoop simLoop;
    private boolean simStarted;

    public ControllerImpl(final View view, final Scenario scenario) {
        this.scenario = scenario;
        this.view = view;
        this.simLoop = SimulationLoopThread.createMainLoop(view, scenario);
        simStarted = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void loadSimulation() {
        final ParametersHolder paramHolder = new ParametersHolder(view.getParameters());
        paramHolder.createScenario();
        this.scenario.setInfo(paramHolder.getScenarioData());
        this.scenario.createRulesDatabase();
        this.scenario.createEntities();
        this.view.loadBackground(scenario.getGrid());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startSimulation() {
        if (!simStarted) {
            simStarted = true;
            simLoop.startLoop();
        }
       simLoop.resumeLoop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseSimulation() {
        simLoop.pauseLoop();
    }

}
