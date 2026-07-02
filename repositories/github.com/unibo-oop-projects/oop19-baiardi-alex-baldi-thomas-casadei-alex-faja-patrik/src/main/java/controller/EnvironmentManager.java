package controller;

import java.util.List;

import org.dyn4j.collision.Fixture;

import model.people.Person;
import model.places.HospitalizationOutcome;
import model.places.MeetingPlace;

/**
 * Control the simulation environment.
 */
public interface EnvironmentManager {

    /**
     * Moves people in the map.
     */
    void movePeople();

    /**
     * Move specified person into the home.
     * 
     * @param person
     *              person that move to home
     * @param time
     *              the instant of time
     */
    void moveToHome(Person person, int time);

    /**
     * Moves a specified person to the Hospital.
     * 
     * @param person 
     *              person to move to hospital
     * @param time
     *              the instant of time
     */
    void moveToHospital(Person person, int time);

    /**
     * Adds a Meeting Place in the map.
     * 
     * @param meetingPlace 
     *                  meetingPlace to be added in the map
     * 
     */
    void addMeetingPlace(MeetingPlace meetingPlace);

    /**
     * Lets out people from Home in a specified time instant.
     * 
     * @param time 
     *              specific time instant
     */
    void exitFromHome(int time);

    /**
     * Lets out people from MeetingPlaces in a specified time instant.
     * 
     * @param time 
     *              specific time instant
     */
    void exitFromMeetingPlaces(int time);

    /**
     * Lets out people from hospital in a specified time instant. It return the
     * people that exit whit their outcome.
     * 
     * @param time 
     *              specific time instant
     * @return HospitalizationOutcome that represent the exit of people from
     *         hospital
     */
    HospitalizationOutcome exitFromHospital(int time);

    /**
     * Adds a person in the map.
     * 
     * @param person person to be added in the map
     */
    void addPerson(Person person);

    /**
     * Removes specified person.
     * 
     * @param person 
     *              person to remove
     */
    void removePerson(Person person);

    /**
     * Sets the alert state closing meeting place and increase home tendency.
     * 
     * @param homeTendencyIncrement
     *              percent that represent the home tendency increment
     */
    void setAlertState(double homeTendencyIncrement);

    /**
     * Unsets the alert state.
     */
    void unsetAlertState();

    /**
     * Gets the list of people outside.
     * @return
     *          list of people in the environment.
     */
    List<Person> getPeopleOutside();

    /**
     * Gets the list of people in the simulation.
     * @return
     *          list of all people.
     */
    List<Person> getAllPeople();

    /**
     * Gets the home fixture.
     * 
     * @return 
     *          fixture that represents an association between home and its shape
     * 
     */
    Fixture getHomeFixture();

    /**
     * Gets the hospital fixture.
     * 
     * @return 
     *          fixture that represents an association between hospital and its
     *          shape
     */
    Fixture getHospitalFixture();

    /**
     * Gets the list of fixtures representing the people outside.
     * 
     * @return 
     *          list of fixtures that represent an association between person and its
     *          shape
     */
    List<Fixture> getPeopleFixtures();

    /**
     * Gets the list of Meeting places fixtures.
     * 
     * @return 
     *          list of fixtures that represent an association between a meeting
     *          place and its shape
     */
    List<Fixture> getMeetingPlaceFixtures();

    /**
     * Checks if the hospital is empty.
     * 
     * @return 
     *          true if the hospital is empty, false otherwise
     */
    boolean isHospitalEmpty();

    /**
     * Gets the size of the map as a square.
     * 
     * @return 
     *          map size
     */
    double getMapSize();

    /**
     * Returns the number of people inside the hospital.
     * @return number of people inside the hospital
     */

    int getPeopleHospital();

    /**
     * Returns the number of people inside meeting places.
     * @return number of people inside meeting places
     */
    int getPeopleMeetingPlace();

    /**
     * Returns the number of people at home.
     * @return number of people at home
     */
    int getPeopleHome();
}
