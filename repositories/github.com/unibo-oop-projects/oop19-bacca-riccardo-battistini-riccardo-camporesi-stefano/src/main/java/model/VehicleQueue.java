package model;

import model.observer.Observer;

/**
 * Handles the creation of vehicles and their elimination. It must be attached
 * to a Lane to work properly.
 */
public interface VehicleQueue extends Observer {

    /**
     *
     * Vehicles will be created at the starting point of the associated lane and
     * removed when they reach the last point of the associated lane.
     */
    void attachToLane();

    /**
     * If there are vehicles in the ending point of the lane, removes them.
     *
     */
    void flush();

    void update();
}
