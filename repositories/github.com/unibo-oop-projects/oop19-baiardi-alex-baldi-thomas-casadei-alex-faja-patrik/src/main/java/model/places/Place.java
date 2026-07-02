package model.places;

import java.util.List;

import model.people.Person;

/**
 * Interface that models a place.
 */
public interface Place {

    /**
     * Inserts a person into the place.
     * 
     * @param person the person who enters the place.
     * @param time   the instant the person has to leave
     */
    void enter(Person person, int time);

    /**
     * Removes people from the place.
     * 
     * @param time the actual instant
     * @return List of people leaving the place at a given moment
     */
    List<Person> exit(int time);

    /**
     * Removes a person from the place.
     * 
     * @param person The person to be removed.
     */
    void exitSinglePerson(Person person);

    /**
     * Method that gets all the people inside the place.
     * 
     * @return The list of all people into the place.
     */
    List<Person> getAllPeople();
}
