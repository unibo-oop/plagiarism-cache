package controller.data;

import java.util.HashSet;
import java.util.Set;

/**
 * This class holds all the parameters needed to configure the scenario that
 * will be loaded.
 */
public final class ScenarioData {

    private Set<VehicleGroupData> vehicles;
    private Set<RoadData> roads;
    private TrafficLightData trafficLights;

    public ScenarioData() {
        this.vehicles = new HashSet<>();
        this.roads = new HashSet<>();
    }

    public Set<VehicleGroupData> getVehicles() {
        return vehicles;
    }

    public Set<RoadData> getRoads() {
        return roads;
    }

    public TrafficLightData getTrafficLight() {
        return trafficLights;
    }

    public void setVehicles(final Set<VehicleGroupData> vehicles) {
        this.vehicles = vehicles;
    }

    public void setRoads(final Set<RoadData> roads) {
        this.roads = roads;
    }

    public void setTrafficLight(final TrafficLightData trafficLights) {
        this.trafficLights = trafficLights;
    }

    @Override
    public String toString() {
        return "vehicles=" + vehicles + ", roads=" + roads + "]";
    }

}
