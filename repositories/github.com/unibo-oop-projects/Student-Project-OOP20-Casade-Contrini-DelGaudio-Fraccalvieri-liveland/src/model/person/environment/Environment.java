package model.person.environment;


import java.util.List;

import model.person.entrance.EntranceImpl;
import model.person.ticket.PersonTicket;

public interface Environment {

    /**
     * Adding a new person into the park.
     * @param personTicket personTicket
     */
    void peopleEntrance(PersonTicket personTicket);

    /**
     * Deletes the first person of the list.
     */
    void exitPeople();

    /**
     * @return the list of people into the park
     */
    List<PersonTicket> getPersonList();

    /**
     * @return list with all the profits
     */
    List<Integer> getEntranceProfit();

    /**
     * @return the class entrance
     */
    EntranceImpl getEntrance();
}
