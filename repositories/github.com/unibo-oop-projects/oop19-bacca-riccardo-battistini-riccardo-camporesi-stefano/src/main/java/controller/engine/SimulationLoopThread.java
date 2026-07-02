package controller.engine;

import model.Scenario;
import model.ScenarioStatus;
import view.View;

import static view.utils.ViewConstraints.MS_BETWEEN_FRAMES;

import static view.utils.ViewConstraints.MSEC_TO_SEC;

public final class SimulationLoopThread extends Thread implements SimulationLoop {

    private final View view;
    private final Scenario scenario;
    private volatile boolean paused;
    private volatile boolean stopped;

    private SimulationLoopThread(final View view, final Scenario scenario) {
        this.view = view;
        this.scenario = scenario;
        paused = false;
        stopped = false;
    }

    /**
     * Static factory to create the simulation loop, cannot be instantiated outside
     * package controller.
     * 
     * @param view     the view of the application
     * @param scenario the current loaded scenario
     * @return the new thread that performs the loop
     */
    public static SimulationLoopThread createMainLoop(final View view, final Scenario scenario) {
        final SimulationLoopThread simLoop = new SimulationLoopThread(view, scenario);
        simLoop.setName("SimulationLoop");
        simLoop.setDaemon(true);
        return simLoop;
    }

    private void updateSimulation() {
        this.scenario.update();
    }

    private void updateReport(final double time) {
        this.view.updateReport(scenario.getVehicles(), scenario.getPhaseManager(), time);
    }

    private void render() {
        this.view.render(scenario.getVehicles(), scenario.getTrafficLights());
    }

    private void waitForNextFrame(final long current) {
        final long dt = System.currentTimeMillis() - current;
        if (dt < MS_BETWEEN_FRAMES) {
            try {
                Thread.sleep(MS_BETWEEN_FRAMES - dt);
            } catch (InterruptedException ex) {
            }
        }
    }

    private synchronized void handlePauseState() {
        while (this.paused) {
            try {
                this.wait();
            } catch (InterruptedException e) {
            }
        }
    }

    @Override
    public void run() {
        long lastTime = System.currentTimeMillis();
        while (!this.stopped) {
            if (this.paused) {
                this.handlePauseState();
                lastTime = System.currentTimeMillis() - MS_BETWEEN_FRAMES;
            }
            final long current = System.currentTimeMillis();
            this.updateSimulation();
            this.render();
            this.updateReport((current - lastTime) * MSEC_TO_SEC);
            this.waitForNextFrame(current);
            lastTime = current;
        }
    }

    @Override
    public synchronized void pauseLoop() {
        this.paused = true;
        scenario.setStatus(ScenarioStatus.STOPPED);
    }

    @Override
    public synchronized void resumeLoop() {
        this.paused = false;
        scenario.setStatus(ScenarioStatus.RUNNING);
        this.notifyAll();
    }

    @Override
    public synchronized void startLoop() {
        scenario.setStatus(ScenarioStatus.RUNNING);
        this.start();
    }

}
