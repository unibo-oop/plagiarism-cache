package controller;

import java.util.List;

import model.people.Person;

/**
 * Interface to manage infected people.
 */
public interface InfectedManager {

    /**
     * Stores information about a new infected people.
     * 
     * @param instant the instant when the person become infected
     * @param person  the person that contracts the infection
     */
    void addInfected(int instant, Person person);

    /**
     * Checks if in the specified instant the people have terminate the incubation
     * period and change the status to Ill.
     * 
     * @param instant the instant where execute the operations
     * 
     * @return list of ILL people
     */
    List<Person> changeStatus(int instant);

    /**
     * Checks if there are infected people.
     * 
     * @return True if one or more people are infected False if nobody is infected
     */
    boolean isAnyoneInfected();
}
