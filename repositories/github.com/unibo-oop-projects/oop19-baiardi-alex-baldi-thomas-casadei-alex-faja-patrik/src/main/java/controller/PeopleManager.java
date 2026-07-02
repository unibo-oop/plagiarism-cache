package controller;

import java.util.List;

import model.people.Person;

/**
 * Interface that manages birth, natural death and chooses which people go home.
 */
public interface PeopleManager {

    /**
     * Generate a list of person who will born.
     * 
     * @param totPerson total number of person in the simulation
     * @return list of person who born
     */
    List<Person> birth(int totPerson);

    /**
     *
     * Generates the list of person who will die.
     *
     * @param list list of people outside
     * @return list of dead person
     */
    List<Person> death(List<Person> list);

    /**
     * Generate the list of person who will go to home.
     * 
     * @param list list of people outside
     * @return list of person who will go to home
     */
    List<Person> goHome(List<Person> list);

    /**
     * set the increment of the home tendency.
     * 
     * @param homeTendencyIncrement value of the increment
     */
    void setHomeTendencyIncrement(double homeTendencyIncrement);

}
