package model.person.entrance;


import model.person.ticket.PersonTicket;


public final class EntranceImpl implements Entrance {

    private int profit;
    private int numTickets;
    private int adultProfit;
    private int reducedProfit;
    private int passProfit;

    /**
     * {@inheritDoc}
     */
    public void addPerson(final PersonTicket personTicket) {

        switch (personTicket.getTicket()) {
            case ADULT:
                adultProfit += personTicket.getTicket().getPrice();
                break;
            case REDUCED:
                reducedProfit += personTicket.getTicket().getPrice();
                break;
            default:
                passProfit += personTicket.getTicket().getPrice();
                break;
        }
        profit = +personTicket.getTicket().getPrice();
        System.out.println(numTickets);
        numTickets++;
    }

    /**
     * {@inheritDoc}
     */
    public int getProfit() {
        return profit;
    }

    /**
     * {@inheritDoc}
     */
    public int getNumTickets() {
        return numTickets;
    }

    /**
     * {@inheritDoc}
     */
    public int getAdultProfit() {
        return adultProfit;
    }

    /**
     * {@inheritDoc}
     */
    public int getReducedProfit() {
        return reducedProfit;
    }

    /**
     * {@inheritDoc}
     */
    public int getPassProfit() {
        return passProfit;
    }
}


