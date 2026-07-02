package model.rules.sight;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import constraints.DirOfMovement;
import model.entities.trafficlight.TrafficLight;
import model.entities.vehicle.Vehicle;
import model.environment.Point;
import model.entities.trafficlight.TrafficLight.Phases;

public class DriverSight {


    /**
     * To calculate vehicle's distance from the vehicle in front.
     * To calculate if the vehicle at the intersection will have to turn left, and see if he can turn left.
     * @param vehicle the selected vehicle
     * @param set the vehicle's set
     * @param crossRoad the position of the cross road into the simulation
     * @param turn turn left instance
     * @return vehicle's distance from the vehicle in front
     */
    public final Optional<Integer> getObstacle(final Vehicle vehicle, final Set<Vehicle> set, final Point crossRoad, final TurningLeft turn) {
        final Set<Vehicle> equalSense = set.stream()
                .filter(x -> x.getSense().equals(vehicle.getSense()) && !x.getPosition().equals(vehicle.getPosition())).collect(Collectors.toSet());

        if (turn.canTurnLeft(vehicle, set, crossRoad)) {
            Optional<Point> nearestVehiclePos = findNear(equalSense, vehicle.getSense(), vehicle.getPosition());
            if (nearestVehiclePos.isPresent()) {
                switch (vehicle.getSense()) {
                case NORTH_SOUTH:
                    return this.getDistFromObstacle(vehicle.getPosition().getY(), nearestVehiclePos.get().getY());
                case SOUTH_NORTH:
                    return this.getDistFromObstacle(nearestVehiclePos.get().getY(), vehicle.getPosition().getY());
                case EAST_WEST:
                    return this.getDistFromObstacle(vehicle.getPosition().getX(), nearestVehiclePos.get().getX());
                case WEST_EAST:
                    return this.getDistFromObstacle(nearestVehiclePos.get().getX(), vehicle.getPosition().getX());
                default:
                    return Optional.empty();
                }
            }
        } else {
            return Optional.of(0);
        }
        return Optional.empty();
    }

    /**
     * 
     * @param vehicles
     * @param mySense
     * @param actualPos
     * @return vehicle's distance from the nearest vehicle in front.
     */
    private Optional<Point> findNear(final Set<Vehicle> vehicles, final DirOfMovement mySense, final Point actualPos) {
        Optional<Point> pos;
        switch (mySense) {
        case NORTH_SOUTH:
            pos = vehicles.stream().filter(x -> x.getPosition().getY() < actualPos.getY())
                             .filter(x -> x.getPosition().getY() > actualPos.getY() - x.getAreaOfControl())
                             .map(x -> x.getPosition())
                             .max(Comparator.comparing(Point :: getY));
            return pos;

        case SOUTH_NORTH:
            pos = vehicles.stream().filter(x -> x.getPosition().getY() > actualPos.getY())
                                   .filter(x -> x.getPosition().getY() < actualPos.getY() + x.getAreaOfControl())
                                   .map(x -> x.getPosition())
                                   .min(Comparator.comparing(Point :: getY));
            return pos;

        case WEST_EAST:
            pos = vehicles.stream().filter(x -> x.getPosition().getX() > actualPos.getX())
                                   .filter(x -> x.getPosition().getX() < actualPos.getX() + x.getAreaOfControl())
                                   .map(x -> x.getPosition())
                                   .min(Comparator.comparing(Point :: getX));
            return pos;

        case EAST_WEST:
            pos = vehicles.stream().filter(x -> x.getPosition().getX() < actualPos.getX())
                                   .filter(x -> x.getPosition().getX() > actualPos.getX() - x.getAreaOfControl())
                                   .map(x -> x.getPosition())
                                   .max(Comparator.comparing(Point :: getX));
            return pos;

        default:
            return Optional.empty();
        }
    }

    /**
     * 
     * @param pos1
     * @param pos2
     * @return distance from 2 integers.
     */
    private Optional<Integer> getDistFromObstacle(final int pos1, final int pos2) {
        return Optional.of(pos1 - pos2 - 1);
    }

    /**
     * 
     * @param vehicle the selected vehicle
     * @param trafficLights all the traffic lights of the simulation
     * @return map composed by traffic light phase and distance, based on what the vehicle can see.
     */
    public final Map<Phases, Optional<Integer>> getTrafficLightObstacle(final Vehicle vehicle, final Set<TrafficLight> trafficLights) {
        final TrafficLight light = trafficLights.stream().filter(x -> x.getSense().equals(vehicle.getSense())).findFirst().get();
        switch (vehicle.getSense()) {
        case EAST_WEST:
            return ((light.getPosition().getX() < vehicle.getPosition().getX())
                    && (light.getPosition().getX() > vehicle.getPosition().getX() - vehicle.getAreaOfControl()))
                            ? new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), DriverSight.this
                                            .getDistFromObstacle(vehicle.getPosition().getX(), light.getPosition().getX()));
                                }
                            }
                            : new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), Optional.empty());
                                }
                            };
        case WEST_EAST:
            return ((light.getPosition().getX() > vehicle.getPosition().getX())
                    && (light.getPosition().getX() < vehicle.getPosition().getX() + vehicle.getAreaOfControl()))
                            ? new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), DriverSight.this
                                            .getDistFromObstacle(light.getPosition().getX(), vehicle.getPosition().getX()));
                                }
                            }
                            : new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), Optional.empty());
                                }
                            };
        case NORTH_SOUTH:
            return ((light.getPosition().getY() < vehicle.getPosition().getY())
                    && (light.getPosition().getY() > vehicle.getPosition().getY() - vehicle.getAreaOfControl()))
                            ? new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), DriverSight.this
                                            .getDistFromObstacle(vehicle.getPosition().getY(), light.getPosition().getY()));
                                }
                            }
                            : new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), Optional.empty());
                                }
                            };
        case SOUTH_NORTH:
            return ((light.getPosition().getY() > vehicle.getPosition().getY())
                    && (light.getPosition().getY() < vehicle.getPosition().getY() + vehicle.getAreaOfControl()))
                            ? new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), DriverSight.this
                                            .getDistFromObstacle(light.getPosition().getY(), vehicle.getPosition().getY()));
                                }
                            }
                            : new HashMap<Phases, Optional<Integer>>() {
                                private static final long serialVersionUID = 1L;
                                {
                                    this.put(light.getCurrentPhase(), Optional.empty());
                                }
                            };
        default:
            return null;
        }
    }

}
