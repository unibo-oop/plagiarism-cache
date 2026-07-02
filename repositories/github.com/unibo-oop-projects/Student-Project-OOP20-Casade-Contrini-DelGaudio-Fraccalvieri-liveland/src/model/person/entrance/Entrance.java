package model.person.entrance;



import model.person.ticket.PersonTicket;

public interface Entrance {

    /**
     * Increments the profit based on ticket type.
     * @param personTicket person ticket
     */
    void addPerson(PersonTicket personTicket);

    /**
    * @return the total profit of the simulation day
    */
    int getProfit();

    /**@return the number of people sold
     * 
     */
    int getNumTickets();

    /**
     * @return the total profit of the adult tickets
     */
    int getAdultProfit();

    /**
     * @return the total profit of the reduced tickets
     */
    int getReducedProfit();

    /**
     * @return the total profit of the season pass
     */
    int getPassProfit();
}
