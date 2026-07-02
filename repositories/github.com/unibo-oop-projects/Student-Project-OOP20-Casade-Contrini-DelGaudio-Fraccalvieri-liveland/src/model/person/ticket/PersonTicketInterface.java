package model.person.ticket;

import model.ticket.Ticket;

public interface PersonTicketInterface {
    /**
     * @return a random age for the person
     */
    int randAge();

    /**
     * 
     * @return age of the person
     */
    int getAge();

    /**
     * 
     * @return the ticket of the person
     */
    Ticket getTicket();

    /**
     * 
     * @return a random ticket for the person
     */
    Ticket randTicket();
}
