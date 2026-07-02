package model.rules;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Map.Entry;

import static constraints.VehicleConstraints.CONST_ACCELERATION;
import static constraints.VehicleConstraints.Status;
import model.ScenarioImpl;
import model.entities.trafficlight.TrafficLight;
import model.entities.trafficlight.TrafficLight.Phases;
import model.entities.vehicle.Vehicle;
import model.environment.Point;
import model.observer.Observer;
import model.rules.sight.DriverSight;
import model.rules.sight.TurningLeft;
import model.rules.utils.Update;

public class StandardDriverBehaviour extends AbstractDriverBehaviour implements Observer {

    private final DriverSight sight;

    private final Update setUpdater;
    private ScenarioImpl scenario;
    private Set<Vehicle> vehicles;
    private Set<TrafficLight> trafficLights;
    private Map<Set<Vehicle>, LinkedList<Point>> carPaths;
    private final TurningLeft turn;

    public StandardDriverBehaviour(final DriverSight sight) {
        this.sight = sight;
        setUpdater = new Update();
        vehicles = new HashSet<>();
        trafficLights = new HashSet<>();
        carPaths = new HashMap<>();
        turn = new TurningLeft();
    }

    /**
     * @param object the object updated from the observer
     */
    @Override
    public final void update(final Object object) {
        scenario = (ScenarioImpl) object;
        vehicles = scenario.getVehicles();
        carPaths = scenario.getCarPaths();
        trafficLights = scenario.getTrafficLights();
        setUpdater.setScenario(scenario);
        turn.setCarPaths(carPaths);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final void roadSight() {
        setVelocity();
        move();
    }

    /*
     * To set vehicle's velocity based on visible's obstacles
     */
    private void setVelocity() {
        for (final Vehicle v : vehicles) {
            final Optional<Integer> distFromObstacle = sight.getObstacle(v, getVehicles(), scenario.getCrossRoad(),
                    turn);
            final Map<Phases, Optional<Integer>> distFromTrafficLight = sight.getTrafficLightObstacle(v, trafficLights);
            if (distFromObstacle.isPresent() && !isPresent(distFromTrafficLight)
                    || distFromObstacle.isPresent() && isPresent(distFromTrafficLight)) {
                v.setVelocity((distFromObstacle.get() <= v.getVelocity()) ? distFromObstacle.get()
                        : Math.min(v.getVelocity() + CONST_ACCELERATION, v.getMaxVel()));

            } else if (distFromObstacle.isEmpty() && isPresent(distFromTrafficLight)) {
                if (getTrafficLightStatus(distFromTrafficLight).equals(Phases.RED)
                        || getTrafficLightStatus(distFromTrafficLight).equals(Phases.YELLOW)) {
                    v.setVelocity(getTrafficLightDistance(distFromTrafficLight).get() <= v.getVelocity()
                            ? getTrafficLightDistance(distFromTrafficLight).get()
                            : Math.min(v.getVelocity() + CONST_ACCELERATION, v.getMaxVel()));
                } else { // if trafficLight phase is green
                    v.setVelocity(Math.min(v.getVelocity() + CONST_ACCELERATION, v.getMaxVel()));
                }
            } else { // there aren't obstacles in front of the vehicle
                v.setVelocity(Math.min(v.getVelocity() + CONST_ACCELERATION, v.getMaxVel()));
            }
            if (v.getVelocity() == 0) {
                v.setStatus(Status.IDLE);
            } else {
                v.setStatus(Status.RUNNING);
            }
        }
        setUpdater.accept(getVehicles());
    }

    /**
     * used to change vehicle's position, based on his velocity and carPaths.
     */
    private void move() {
        for (final Vehicle v : vehicles) {
            for (final Entry<Set<Vehicle>, LinkedList<Point>> entry : carPaths.entrySet()) {
                if (entry.getKey().contains(v)) {
                    for (int i = 0; i < entry.getValue().indexOf(entry.getValue().getLast()); i++) {
                        if (entry.getValue().get(i).equals(v.getPosition())) {
                            v.setPosition(entry.getValue().get(Math
                                    .min(entry.getValue().indexOf(entry.getValue().getLast()), i + v.getVelocity())));
                            if (v.getPosition().equals(entry.getValue().getLast())) {
                                v.setStatus(Status.EXITING);
                            }
                            break;
                        }
                    }
                }
            }
        }
        setUpdater.accept(getVehicles());
    }

    public final Set<Vehicle> getVehicles() {
        return vehicles;
    }

}
