package model.places;

import java.util.List;

import model.people.Person;

/**
 * Interface that models a place where people can meet.
 */
public interface MeetingPlace extends Place {

    /**
     * Check if is it possible to access the place.
     * 
     * @return true if the place is open. 
     *         false if the place is closed.
     */
    boolean isOpen();

    /**
     * Checks if the number of people in the place has reached maximum capacity.
     * 
     * @return true if the number of people equals max capacity; 
     *         false if the number of people is less than max capacity.
     */
    boolean isFull();

    /**
     * When a person enters the place check if there are new infections.
     * 
     * @param person The person who enters the place.
     * @param time   the actual instant
     * @return List of new-infected people.
     */
    List<Person> enterAndInfect(Person person, int time);

    /**
     * Close the place, nobody can enter.
     * 
     * @return List of people inside the place.
     */
    List<Person> close();

    /**
     * Reopens the place after alert state has ended.
     */
    void open();

    /**
     * Check if a person is in the place.
     * 
     * @param person the person to look for in the place.
     * @return true if the person is present. 
     *         false if the person isn't present.
     */
    boolean checkPresence(Person person);
}
