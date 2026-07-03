package hotelmaster.pricing;

import hotelmaster.reservations.Stay;

/**
 * The calculation of a price.
 */
@FunctionalInterface
public interface PriceCalculation {

    /**
     * Returns the price of a {@link Stay}.
     * 
     * @param stay
     *            the stay
     * @return the price
     */
    double calculate(final Stay stay);
}
