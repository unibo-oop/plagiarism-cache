package model.places;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.people.Person;

/**
 * Models an abstract implementation of Place.
 */
public abstract class AbstractPlace implements Place {
    private final Map<Integer, List<Person>> peopleMap = new HashMap<>();

    /**
     * 
     */
    @Override
    public void enter(final Person person, final int time) {
        check(this.getAllPeople().contains(person));
        peopleMap.computeIfAbsent(time, k -> new LinkedList<Person>()).add(person);
    }

    /**
     * 
     */
    @Override
    public List<Person> exit(final int time) {
        if (this.peopleMap.containsKey(time)) {
            return this.peopleMap.remove(time);
        }
        return List.of();
    }

    /**
     * 
     */
    @Override
    public void exitSinglePerson(final Person person) {
        check(!this.getAllPeople().contains(person));
        this.peopleMap.values().stream().filter(l -> l.contains(person)).forEach(l -> l.remove(person));
    }

    /**
    * 
    */
    @Override
    public List<Person> getAllPeople() {
        return this.peopleMap.values().stream().flatMap(l -> l.stream()).collect(Collectors.toList());
    }

    /**
     * Removes all the occurrences in the map.
     */
    protected void removeAll() {
        this.peopleMap.clear();
    }

    private void check(final boolean condition) {
        if (condition) {
            throw new IllegalStateException();
        }
    }

}
