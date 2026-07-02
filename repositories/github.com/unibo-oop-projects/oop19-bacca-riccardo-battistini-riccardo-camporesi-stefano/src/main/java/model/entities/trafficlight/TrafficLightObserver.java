package model.entities.trafficlight;

/**
 * Provides an implementation of the observer, used to update traffic lights. 
 */
public interface TrafficLightObserver {

    /**
     * Tells the traffic light to update its state.
     */
    void updatePhase();
}
