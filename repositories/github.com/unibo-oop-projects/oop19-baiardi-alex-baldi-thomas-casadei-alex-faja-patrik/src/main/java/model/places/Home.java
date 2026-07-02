package model.places;

import model.people.Person;

/**
 *Interface that models the place where people retire.
 */
public interface Home extends Place {

    /**
     * 
     * @param person
     *              the person to look for in the place.
     * @return 
     *         true if the person is present.
     *         false if the person isn't present.
     */
    boolean checkPresence(Person person);

    /**
     * Sets the percentage increment of time that people spend at home.
     * 
     * @param increment Percentage increment of time
     */
    void setHomeTendencyIncrement(double increment);
}
