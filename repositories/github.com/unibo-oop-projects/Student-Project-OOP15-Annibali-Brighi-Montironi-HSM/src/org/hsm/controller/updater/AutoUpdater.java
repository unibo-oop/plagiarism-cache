package org.hsm.controller.updater;

import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import org.hsm.controller.Controller;
import org.hsm.controller.ControllerImpl;
import org.hsm.controller.simulator.Simulator;
import org.hsm.controller.simulator.SimulatorImpl;

/**
 *
 * Automatically update the plants values every n seconds.
 *
 */
public class AutoUpdater extends Thread {

    private static final int MS_TO_S = 1000;

    private final Controller controller = ControllerImpl.getController();
    private final Simulator simulator = new SimulatorImpl();

    private int time;
    private volatile boolean stopped;

    /**
     * Create a thread called Auto_Updater_Thread.
     */
    public AutoUpdater() {
        super("Auto_Updater_Thread");
    }

    /**
     * Set refresh rate.
     *
     * @param time
     *            the refresh rate in seconds
     */
    public void setTime(final int time) {
        this.time = time;
    }

    @Override
    public void run() {
        this.stopped = false;
        while (!this.stopped) {
            this.controller.getGreenhouse().incrementCounter();
            try {
                SwingUtilities.invokeAndWait(() -> {
                    this.controller.getView().cleanGreenhousePlants();
                    this.controller.getGreenhouse().getPlants().forEach((a, b) -> {
                        final double ph = this.simulator.getSimulatedPh(b);
                        final double bright = this.simulator.getSimulatedBrightness(b);
                        final double cond = this.simulator.getSimulatedConductibility(b);
                        final double temp = this.simulator.getSimulatedTemperature(b);
                        this.controller.getView().insertPlant(a, b.getModel().getName(), b.getCost(), ph, bright, cond,
                                temp);
                        b.addPhValue(ph);
                        b.addBrightValue(bright);
                        b.addConductValue(cond);
                        b.addTempValue(temp);
                        b.addPhValueTraditional(this.simulator.getRealPh(b));
                        b.addBrightValueTraditional(this.simulator.getRealBrightness(b));
                        b.addConductValueTraditional(this.simulator.getRealConductibility(b));
                        b.addTempValueTraditional(this.simulator.getRealTemperature(b));
                    });
                });
            } catch (InvocationTargetException | InterruptedException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(time * MS_TO_S);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void interrupt() {
        this.stopped = true;
    }

}
