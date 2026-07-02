package model;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import model.entities.road.Road;
import model.entities.vehicle.Vehicle;
import model.environment.Point;

import static constraints.VehicleConstraints.Status;

public final class VehicleQueueImpl implements VehicleQueue {

    private Queue<Vehicle> vehicleQueue = new LinkedList<>();
    private Set<Road> roads;
    private Set<Vehicle> vehicles;


    @Override
    public void attachToLane() {
        vehicles.forEach(v -> {
            if (v.getStatus().equals(Status.ENTERING)) {
                vehicleQueue.add(v);
            }
        });
    }

    @Override
    public void flush() {
        vehicles.forEach(v -> {
            if (v.getStatus().equals(Status.EXITING)) {
                v.setStatus(Status.ENTERING);
                vehicleQueue.add(v);
            }
        });
    }

    private Point findStart(final Vehicle vehicle, final Set<Road> roads) {
        final Queue<Point> startPoint = new LinkedList<>();
        roads.stream().flatMap(i -> i.getRoads().entrySet().stream()).flatMap(j -> j.getValue().stream())
                .filter(k -> k.getSense().equals(vehicle.getSense())).forEach(l -> startPoint.add(l.getStart()));
        return startPoint.poll();
    }


    public void update() {
        if (!vehicleQueue.isEmpty()) {
            Vehicle vehicle = vehicleQueue.poll();
            vehicle.setStatus(Status.RUNNING);
            vehicle.setPosition(this.findStart(vehicle, this.roads));
        }
    }

    @Override
    public void update(final Object object) {
        ScenarioImpl scenario = (ScenarioImpl) object;
        this.vehicles = scenario.getVehicles();
        this.roads = scenario.getRoads();
    }

}
