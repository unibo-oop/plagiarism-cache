package controller;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import model.people.Person;
import model.people.Status;

/**
 *
 */
public class InfectedManagerImpl implements InfectedManager {
    private final Map<Integer, List<Person>> infectedPeople;

    /**
     *
     */
    public InfectedManagerImpl() {
        this.infectedPeople = new HashMap<>();
    }

    /**
     * 
     */
    @Override
    public void addInfected(final int instant, final Person person) {
        final int time = instant + person.getVirus().get().getIncubationPeriod();
        this.infectedPeople.computeIfAbsent(time, k -> new LinkedList<Person>()).add(person);
    }

    /**
     * 
     */
    @Override
    public List<Person> changeStatus(final int instant) {
        if (this.infectedPeople.containsKey(instant)) {
            this.infectedPeople.get(instant).stream().forEach(p -> p.setStatus(Status.ILL));
            return this.infectedPeople.remove(instant);
        }
        return List.of();
    }

    /**
     * 
     */
    @Override
    public boolean isAnyoneInfected() {
        return !this.infectedPeople.isEmpty();
    }
}
