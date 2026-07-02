package person.environment.motion;

import java.util.Map;

import model.gui.position.Position;
import model.gui.position.RandomPosition;
import model.person.ticket.PersonTicket;

/**
 * This class models a correspondence between the internal park environment
 * and its graphical representation, by adding or removing people to the simulation.
 */
public class PeopleRecirculationGui {

    private final Map<PersonTicket, Position<Integer, Integer>> people;

    public PeopleRecirculationGui(final Map<PersonTicket, Position<Integer, Integer>> map) {
        this.people = map;
    }

    /**
     * This method adds a new person to the graphical simulation.
     * @param person to be added
     */
    public void peopleEntrance(final PersonTicket person) {
        this.people.put(person, new RandomPosition().randomPosition(this.people));
        System.out.println("persona: " + person.toString() + "entrata");
        System.out.println(this.people.size());
    }

    /**
     * This method removes a new person to the graphical simulation.
     * @param person who needs to exit
     */
    public void peopleExit(final PersonTicket person) {
        this.people.remove(person);
        System.out.println("persona: " + person.toString() + "uscita");
    }

}
