package model.people;

import java.util.Optional;

import model.virus.Virus;

/**
 * Represents a Person.
 */
public interface Person {

    /**
     * Gets the current person status.
     * 
     * @return person's status.
     * 
     */
    Status getStatus();

    /**
     * Sets a new person status.
     * 
     * @param status
     *          new person's status.
     * 
     */
    void setStatus(Status status);

    /**
     * Calculate if this person infect another person in contact with it.
     * 
     * @return 
     *          true infection occurred. 
     *          false infection not occurred.
     */
    boolean tryToInfect();

    /**
     * Infects a person with a virus that have specific parameters for the person.
     * 
     * @param virus 
     *              the instance of the virus that infected a person.
     */
    void infect(Virus virus);

    /**
     * Gets the virus if it is present.
     * 
     * @return 
     *         Optional describing the virus that infect the person or an empty
     *         Optional if the person isn't infected
     */
    Optional<Virus> getVirus();

}
