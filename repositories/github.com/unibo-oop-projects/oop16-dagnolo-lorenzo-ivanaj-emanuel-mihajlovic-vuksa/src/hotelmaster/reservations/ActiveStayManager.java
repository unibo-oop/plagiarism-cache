package hotelmaster.reservations;

import hotelmaster.structure.Hotel;

/**
 * A {@link StayManager} which allows modifying an active {@link Stay}'s state
 * and parameters.
 */
public class ActiveStayManager extends StayManager {

    ActiveStayManager(final Stay stay) throws IllegalArgumentException {
        super(stay, StayState.ACTIVE);
    }

    /**
     * Concludes the {@link Stay}, removing it from the {@link Hotel} and
     * returning its total cost. If the end date is not today's date, it is set
     * to today's date and the price is calculated as such.
     * 
     * @throws IllegalStateException
     *             the stay is not active
     * 
     * @return the cost of the removed stay
     */
    public double checkout() throws IllegalStateException {
        this.checkState();
        final double total = this.getStay().getTotalPrice();
        this.closeStay();
        return total;
    }
}
