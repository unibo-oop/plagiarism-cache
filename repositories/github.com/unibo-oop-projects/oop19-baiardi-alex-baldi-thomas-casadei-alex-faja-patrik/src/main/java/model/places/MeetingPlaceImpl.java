package model.places;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import model.people.Person;
import model.people.Status;

/**
 *
 */
public class MeetingPlaceImpl extends AbstractPlace implements MeetingPlace {
    private static final int MAX_TIME = 24;
    private boolean accessible;
    private static final int MAX_CAPACITY = 20;
    private final List<Person> people; // list that stores susceptible people and those who can infect.

    /**
     * Constructor method for a meeting place.
     */
    public MeetingPlaceImpl() {
        this.accessible = true;
        this.people = new LinkedList<>();
    }

    /**
     * 
     */
    @Override
    public boolean checkPresence(final Person person) {
        return getAllPeople().contains(person);
    }

    /**
     * 
     */
    @Override
    public boolean isFull() {
        return super.getAllPeople().size() == MAX_CAPACITY;
    }

    /**
     * 
     */
    @Override
    public boolean isOpen() {
        return this.accessible;
    }

    /**
     * 
     */
    @Override
    public void open() {
        this.accessible = true;
    }

    /**
     * 
     */
    @Override
    public List<Person> close() {
        this.accessible = false;
        final List<Person> allPeople = super.getAllPeople();
        super.removeAll();
        this.people.clear();
        return allPeople;
    }

    /**
     * 
     */
    @Override
    public List<Person> enterAndInfect(final Person person, final int time) {
        final int exitTime = time + this.calculateTime();
        super.enter(person, exitTime);
        return person.getStatus() == Status.INFECTED ? this.infectOther(person)
                : person.getStatus() == Status.SUSCEPTIBLE ? this.infectedBy(person) : List.of();
    }

    /**
     * An infected person infects susceptible person.
     * 
     * @param person The infected person.
     * @return List of new-infected people
     */
    private List<Person> infectOther(final Person person) {
        final List<Person> infected = new LinkedList<>();
        this.people.add(person);
        for (final Person p : this.people.stream().filter(p -> p.getStatus().equals(Status.SUSCEPTIBLE))
                .filter(p -> person.tryToInfect()).collect(Collectors.toList())) {
            infected.add(p);
            this.people.remove(p);
        }
        infected.forEach(p -> p.infect(person.getVirus().get().duplicate()));
        return infected;
    }

    /**
     * A susceptible person is infected by infected people inside the place.
     * 
     * @param person The susceptible person.
     * @return The new-infected person
     */
    private List<Person> infectedBy(final Person person) {
        final List<Person> infected = new LinkedList<>();
        this.people.stream().filter(p -> p.getStatus() == Status.INFECTED).filter(p -> p.tryToInfect()).findFirst()
                .ifPresent(p1 -> person.infect(p1.getVirus().get().duplicate()));
        if (person.getStatus() == Status.INFECTED) {
            infected.add(person);
        } else {
            this.people.add(person);
        }
        return infected;
    }

    /**
     * 
     */
    @Override
    public List<Person> exit(final int time) {
        final List<Person> exitPeople = super.exit(time);
        this.people.removeIf(p -> exitPeople.contains(p));
        return exitPeople;
    }

    /**
    * 
    */
    @Override
    public void exitSinglePerson(final Person person) {
        super.exitSinglePerson(person);
        this.people.remove(person);
    }

    private int calculateTime() {
        final Random r = new Random();
        return r.nextInt(MAX_TIME);
    }
}
