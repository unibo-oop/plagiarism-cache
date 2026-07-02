package model.entities.trafficlight;

/**
 *
 * Interface that defines an object for managing phase's transition of traffic
 * lights.
 *
 * This component handles a fixed amount of phases, specified at the moment of
 * creation. Each phase has a duration identified by an integer. The manager
 * transitions to each phase always with the same order specified in the list
 * given.
 *
 * @param <T> the object that represents a phase
 */
public interface PhaseManager<T> extends TrafficLightSubject {

    /**
     * Tells the phaseManager that a slice of time is passed. Eventually the phase
     * manager will update the phase of each TrafficLight that is currently
     * observing.
     */
    void update();

    /**
     * @return The current phase
     */
    T getCurrentPhase();

    /**
     * @return The time remaining until the next phase change
     */
    int getCurrentPhaseDuration();

}
