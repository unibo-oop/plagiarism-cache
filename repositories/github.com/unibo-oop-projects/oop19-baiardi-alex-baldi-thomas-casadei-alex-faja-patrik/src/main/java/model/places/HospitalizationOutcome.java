package model.places;

import java.util.List;

import model.people.Person;

/**
 * Interface that models the outcome of the hospitalization of a group of ill
 * people.
 */
public interface HospitalizationOutcome {

    /**
     * Gets the people dead from the virus.
     * 
     * @return List of dead people.
     */
    List<Person> getDeadPeople();

    /**
     * Gets the people healed from the virus.
     * 
     * @return List of recovered people.
     */
    List<Person> getRecoveredPeople();
}
