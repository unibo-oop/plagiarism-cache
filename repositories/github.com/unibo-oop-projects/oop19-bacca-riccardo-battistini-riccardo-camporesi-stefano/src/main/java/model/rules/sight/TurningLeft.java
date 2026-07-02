package model.rules.sight;

import static constraints.GridConstraints.GRID_HEIGHT;
import static constraints.GridConstraints.GRID_WIDTH;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import constraints.DirOfMovement;
import model.entities.vehicle.Vehicle;
import model.environment.Point;

public final class TurningLeft {

    private Map<Set<Vehicle>, LinkedList<Point>> carPaths;

    /**
     * @return the carPaths
     */
    public Map<Set<Vehicle>, LinkedList<Point>> getCarPaths() {
        return this.carPaths;
    }

    /**
     * @param carPaths the carPaths to set
     */
    public void setCarPaths(final Map<Set<Vehicle>, LinkedList<Point>> carPaths) {
        this.carPaths = carPaths;
    }

    /**
     * @param sense the sense of the passed vehicle
     * @param destination the destination of the vehicle
     * @return true if the vehicle, at the intersection will have to turn left
     */
    public boolean turnLeft(final DirOfMovement sense, final Point destination) {
        switch (sense) {
        case NORTH_SOUTH:
            if (destination.equals(Point.of(GRID_WIDTH, GRID_HEIGHT))) {
                return true;
            }
            return false;

        case SOUTH_NORTH:
            if (destination.equals(Point.of(0, (GRID_HEIGHT / 2) + 1))) {
                return true;
            }
            return false;

        case EAST_WEST:
            if (destination.equals(Point.of(GRID_HEIGHT / 2, 0))) {
                return true;
            }
            return false;

        case WEST_EAST:
            if (destination.equals(Point.of((GRID_WIDTH / 2) + 1, GRID_HEIGHT))) {
                return true;
            }
            return false;

        default:
            return false;
        }
    }

    /**
     * @param vehicle the vehicle selected
     * @param set the set of vehicles
     * @param crossRoad the position of the intersection into the simulation
     * @return true if the vehicle can turn left because it is at the intersection,
     *         false either
     */
    public boolean canTurnLeft(final Vehicle vehicle, final Set<Vehicle> set, final Point crossRoad) {
        if (this.turnLeft(vehicle.getSense(), vehicle.getDestination())
                && (vehicle.getPosition().equals(crossRoad) || this.findPos(crossRoad, vehicle))) {
            Set<Vehicle> oppositeSense = new HashSet<>();
            switch (this.findOpposite(vehicle.getSense())) {
            case NORTH_SOUTH:
                oppositeSense = set.stream().filter(x -> x.getSense().equals(this.findOpposite(vehicle.getSense()))
                        && !x.getPosition().equals(vehicle.getPosition()) && x.getPosition().getY() >= GRID_HEIGHT / 2
                        && x.getPosition().getY() <= GRID_HEIGHT / 2 + 2).collect(Collectors.toSet());
                break;

            case SOUTH_NORTH:
                oppositeSense = set.stream().filter(x -> x.getSense().equals(this.findOpposite(vehicle.getSense()))
                        && !x.getPosition().equals(vehicle.getPosition()) && x.getPosition().getY() <= GRID_HEIGHT / 2
                        && x.getPosition().getX() >= GRID_HEIGHT / 2 - 2).collect(Collectors.toSet());
                break;

            case WEST_EAST:
                oppositeSense = set.stream().filter(x -> x.getSense().equals(this.findOpposite(vehicle.getSense()))
                        && !x.getPosition().equals(vehicle.getPosition()) && x.getPosition().getX() <= GRID_WIDTH / 2
                        && x.getPosition().getX() >= GRID_WIDTH / 2 - 2).collect(Collectors.toSet());
                break;

            case EAST_WEST:
                oppositeSense = set.stream().filter(x -> x.getSense().equals(this.findOpposite(vehicle.getSense()))
                        && !x.getPosition().equals(vehicle.getPosition()) && x.getPosition().getX() >= GRID_WIDTH / 2
                        && x.getPosition().getX() <= GRID_WIDTH / 2 + 2).collect(Collectors.toSet());
                break;

            default:
                break;
            }
            if (oppositeSense.isEmpty()) {
                return true;
            }
            return false;
        }

        return true;
    }

    /**
     * 
     * @param sense
     * @return the opposite
     */
    private DirOfMovement findOpposite(final DirOfMovement sense) {
        switch (sense) {
        case NORTH_SOUTH:
            return DirOfMovement.SOUTH_NORTH;
        case SOUTH_NORTH:
            return DirOfMovement.NORTH_SOUTH;
        case WEST_EAST:
            return DirOfMovement.EAST_WEST;
        case EAST_WEST:
            return DirOfMovement.WEST_EAST;
        default:
            return null;
        }
    }

    /**
     *
     * @param crossRoad
     * @param vehicle
     * @return true, if at the next iteration, with this velocity the vehicle will
     *         be on or after the intersection.
     */
    private boolean findPos(final Point crossRoad, final Vehicle vehicle) {
        int crossRoadIndex = 0;
        int carNextIterationIndex = 0;
        for (final Entry<Set<Vehicle>, LinkedList<Point>> entry : this.carPaths.entrySet()) {
            if (entry.getKey().contains(vehicle)) {
                for (int i = 0; i < entry.getValue().size() - 1; i++) {
                    if (entry.getValue().get(i).equals(crossRoad)) {
                        crossRoadIndex = i;
                        break;
                    }
                    if (entry.getValue().get(i).equals(vehicle.getPosition())) {
                        carNextIterationIndex = i + i + vehicle.getVelocity();
                    }
                }
            }
        }
        if (carNextIterationIndex >= crossRoadIndex) {
            return true;
        }
        return false;
    }
}
