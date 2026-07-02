package model.places;

import java.util.LinkedList;
import java.util.List;

import model.people.Person;

/**
 * 
 *
 */
public class HospitalizationOutcomeImpl implements HospitalizationOutcome {
    private final List<Person> deadPeople;
    private final List<Person> recoveredPeople;

    /**
     * Constructor method for the hospitalization outcome.
     */
    public HospitalizationOutcomeImpl() {
        this.deadPeople = new LinkedList<>();
        this.recoveredPeople = new LinkedList<>();
    }

    /**
     * 
     */
    @Override
    public List<Person> getDeadPeople() {
        return this.deadPeople;
    }

    /**
     * 
     */
    @Override
    public List<Person> getRecoveredPeople() {
        return this.recoveredPeople;
    }

}
