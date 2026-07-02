package controller;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import constraints.DirOfMovement;
import constraints.TrafficLightConstraints;
import controller.data.RoadData;
import controller.data.ScenarioData;
import controller.data.TrafficLightData;
import controller.data.VehicleGroupData;
import view.utils.ControllerVariablesNames;

/**
 * This class receives parameters from the class ViewParameters and initialises
 * the *Data classes.
 */
public class ParametersHolder {

    private final Map<ControllerVariablesNames, Double> parameters;
    private ScenarioData scenario;

    public ParametersHolder(final Map<ControllerVariablesNames, Double> parameters) {
        this.parameters = parameters;
        scenario = new ScenarioData();
    }

    /**
     * create the date of the scenario.
     */
    public void createScenario() {
        final Set<VehicleGroupData> vehicleGroup = new HashSet<>();
        Set<RoadData> roadsData = new HashSet<>();
        final TrafficLightData trafficLightData = new TrafficLightData(TrafficLightConstraints.DEFAULT_PHASE_DURATION);

        final int maxVel = parameters.get(ControllerVariablesNames.MAXVEL).intValue();

        for (final Entry<ControllerVariablesNames, Double> entry : parameters.entrySet()) {

            switch (entry.getKey()) {

            case NORTH:
                vehicleGroup.add(new VehicleGroupData(DirOfMovement.NORTH_SOUTH, entry.getValue().intValue(), maxVel));
                break;

            case SOUTH:
                vehicleGroup.add(new VehicleGroupData(DirOfMovement.SOUTH_NORTH, entry.getValue().intValue(), maxVel));
                break;

            case EAST:
                vehicleGroup.add(new VehicleGroupData(DirOfMovement.EAST_WEST, entry.getValue().intValue(), maxVel));
                break;

            case WEST:
                vehicleGroup.add(new VehicleGroupData(DirOfMovement.WEST_EAST, entry.getValue().intValue(), maxVel));
                break;

            case NLANES:
                roadsData = createRoadData(entry.getValue().intValue());
                break;

            case GREENLIGHT:
                trafficLightData.changePhaseDuration(entry.getValue().intValue());
                trafficLightData.setPhasesList();
                break;

            default:
                break;
            }
        }

        scenario.setVehicles(vehicleGroup);
        scenario.setRoads(roadsData);
        scenario.setTrafficLight(trafficLightData);
    }

    /**
     *
     * @param nlanes number of lanes
     * @return create all roadsData
     */
    private Set<RoadData> createRoadData(final int nlanes) {
        final Set<RoadData> roadsData = new HashSet<>();
        roadsData.add(new RoadData("WEST_EAST", nlanes, DirOfMovement.WEST_EAST));
        roadsData.add(new RoadData("SOUTH_NORTH", nlanes, DirOfMovement.SOUTH_NORTH));
        return roadsData;
    }

    /**
     * @return the scenario
     */
    public ScenarioData getScenarioData() {
        return scenario;
    }

    /**
     * @param scenario the scenario to set
     */
    public void setScenarioData(final ScenarioData scenario) {
        this.scenario = scenario;
    }
}
