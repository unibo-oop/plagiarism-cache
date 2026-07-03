package model;

/**
 * 
 * This is an implementation of {@link model.Booking}.
 * 
 */
public class BookingImpl implements Booking {

    private static final double STUDENT_DISCOUNT = 0.1;
    private static final double UNDER14_DISCOUNT = 0.2;
    private static final double ONLINE_PRIZE_INCREMENT = 0.2;
    private static final int MAX_TICKETS = 20;

    private Boolean online;
    private Room room;
    private final int nTickets;

    /**
     * Builds a new {@link BookingImpl}.
     * 
     * @param online
     *           true if you want to buy the tickets online
     * @param nTickets
     *           the number of tickets to buy
     */
    public BookingImpl(final Boolean online, final int nTickets) {
        this.nTickets = nTickets;
        if (this.nTickets > MAX_TICKETS) {
            final String msg = "The number of ticket is more than the number of seats";
            throw new IllegalArgumentException(msg);
        }
        this.online = online;
    }

    @Override
    public void setOnline() {
        if (this.online) {
            this.online = false;
        } else {
            this.online = true;
        }
    }

    @Override
    public void setRoomForBooking(final Room room) {
        this.room = room;
    }

    @Override
    public Room getRoomForBooking() {
        return this.room;
    }

    @Override
    public double computePrice(final Discount discount, final Balance balance) {
        if (this.nTickets < discount.getNumberUnder14() + discount.getNumberStudents()) {
            final String msg = "The discounted tickets are more than the booked tickets";
            throw new IllegalArgumentException(msg);
        }
        final double totalPriceDiscounted;
        final double totalStandardPrice;
        final double totalPrice;
        final double priceIncremented;
        if (this.online) {
            priceIncremented = balance.getTicketCost() + balance.getTicketCost() * ONLINE_PRIZE_INCREMENT;
            totalPriceDiscounted = discount.getNumberUnder14() * (priceIncremented - (priceIncremented * UNDER14_DISCOUNT))
                                   + discount.getNumberStudents() * (priceIncremented - (priceIncremented * STUDENT_DISCOUNT));
            totalStandardPrice = (this.nTickets - (discount.getNumberUnder14() + discount.getNumberStudents())) * priceIncremented;
        } else {
            totalPriceDiscounted = discount.getNumberUnder14() * (balance.getTicketCost() - (balance.getTicketCost() 
                                   * UNDER14_DISCOUNT)) + discount.getNumberStudents() * (balance.getTicketCost()
                                   - (balance.getTicketCost() * STUDENT_DISCOUNT));
            totalStandardPrice = (this.nTickets - (discount.getNumberUnder14() + discount.getNumberStudents()))
                                 * balance.getTicketCost();
        }
        totalPrice = totalPriceDiscounted + totalStandardPrice;
        balance.incBoxOffice(totalPrice);
        return totalPrice;
    }
}
