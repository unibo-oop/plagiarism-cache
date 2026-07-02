package model.person.environment;


import java.util.LinkedList;
import java.util.List;


import model.person.entrance.EntranceImpl;
import model.person.ticket.PersonTicket;

public final class EnvironmentImpl implements Environment {

    private final List<PersonTicket> personList = new LinkedList<>();
    private final EntranceImpl entrance = new EntranceImpl();

    /**
     * {@inheritDoc}
     */
    public void peopleEntrance(final PersonTicket personTicket) {
        personList.add(personTicket);
        entrance.addPerson(personTicket);
    }

    /**
     * {@inheritDoc}
     */
    public void exitPeople() {
        personList.remove(0);
    }

    /**
     * {@inheritDoc}
     */
    public List<PersonTicket> getPersonList() {
        return personList;
    }

    /**
     * {@inheritDoc}
     */
    public void addPersonList(final List<PersonTicket> listPerson) {
        personList.addAll(listPerson);
    }

    /**
     * {@inheritDoc}
     */
    public List<Integer> getEntranceProfit() {
        final List<Integer> profit = new LinkedList<>();
        profit.add(this.entrance.getAdultProfit());
        profit.add(this.entrance.getReducedProfit());
        profit.add(this.entrance.getPassProfit());
        profit.add(this.entrance.getNumTickets());
        return profit;
    }

    /**
     * {@inheritDoc}
     */
    public EntranceImpl getEntrance() {
        return entrance;
    }
}
