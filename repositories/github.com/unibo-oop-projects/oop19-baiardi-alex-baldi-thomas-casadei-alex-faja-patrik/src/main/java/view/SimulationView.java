package view;

import java.util.List;

import org.dyn4j.collision.Fixture;

import controller.SimulationController;

/**
 *
 */
public interface SimulationView {

    /**
     * Initialize the map drawing home, hospital and meeting places.
     * 
     * @param home
     *              a fixture that represents an association between home
     *              and its shape
     * @param hospital
     *              a fixture that represents an association between
     *              hospital and its shape
     * @param meetingPlaces 
     *              list of fixtures that represent an association between a
     *              meeting place and its shape
     */
    void initializeMap(Fixture home, Fixture hospital, List<Fixture> meetingPlaces);

    /**
     * Update the position of the people in the map, drawing the given people.
     * 
     * @param people 
     *              list of fixtures that represent an association between a person
     *              and its shape
     */
    void updatePeoplePosition(List<Fixture> people);

    /**
     * Update information about simulation.
     * @param home
     *              number of people at home
     * @param outside
     *              number of people outside
     * @param meetingPlace
     *              number of people in meeting place
     * @param hospital
     *              number of people in the hospital
     * @param infected
     *              number of infected people
     * @param healthy
     *              number of healthy people
     * @param virusDeath
     *              number of virus death
     * @param recovered
     *              number of recovered people
     */
    void updateSimulationInfo(int home, int outside, int meetingPlace, int hospital, int infected, int healthy,
            int virusDeath, int recovered);

    /**
     * Sets the controller to communicate events.
     * 
     * @param sc 
     *          controller that manage virus
     */
    void setController(SimulationController sc);

}
