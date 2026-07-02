package model.rules.utils;

import java.util.Set;
import java.util.function.Consumer;

import model.ScenarioImpl;
import model.entities.vehicle.Vehicle;

/**
 * this class is used to update the vehicle list on scenarioIMpl class.
 */
public class Update implements Consumer<Set<Vehicle>> {

    private ScenarioImpl scenario;

    @Override
    public final void accept(final Set<Vehicle> vehicles) {
        this.scenario.setVehicles(vehicles);
    }

    /**
     * 
     * @return current scenario instance.
     */
    public final ScenarioImpl getScenario() {
        return this.scenario;
    }

    /**
     * To set current scenario instance.
     * @param scenario the scenario instance to be set
     */
    public final void setScenario(final ScenarioImpl scenario) {
        this.scenario = scenario;
    }

}
