package controller;

import java.util.List;

import org.dyn4j.collision.Fixture;

import model.people.Person;

/**
 * Interface to detect the infection. 
 */
public interface InfectionObserver {

    /**
     * Detects if susceptible people have a contact with an infected one, in this
     * case calculate if the susceptible become infected.
     * 
     * @param peopleFixture 
     *          list of fixture where everyone is an association between
     *                      person and his shape.
     * @return 
     *          list of infected people
     */
    List<Person> detectInfectionBeetweenPeople(List<Fixture> peopleFixture);

    /**
     * Detects the people that try to enter in meeting places and the infection that
     * the entrance cause, if someone enter. The people that enter in MeetingPlace
     * are removed from peopleFixture. The new infected are returned.
     * 
     * @param peopleFixture
     *                  list of fixture where everyone is an association
     *                  between Person and his shape.
     * @param meetingPlacesFixture 
     *                  list of fixture where everyone is an association
     *                  between MeetingPlace and his shape.
     * @param time
     *                  the instant of time when people
     * @return 
     *          list of infected people
     */
    List<Person> tryToEnterAndDetectInfectionInMeetingPlace(List<Fixture> peopleFixture,
            List<Fixture> meetingPlacesFixture, int time);

}
