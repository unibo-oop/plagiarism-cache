package model.entities.trafficlight;

public interface TrafficLightSubject {

    /**
     * To register a new observer to the specified subject.
     *
     * @param obs the observer to be added
     */
    void attach(TrafficLightObserver obs);

    /**
     * To unregister an observer from this subject.
     *
     * @param obs the observer to be removed
     */
    void detach(TrafficLightObserver obs);

    /**
     * To notify observers that their state should be updated.
     */
    void notifyObservers();

}
