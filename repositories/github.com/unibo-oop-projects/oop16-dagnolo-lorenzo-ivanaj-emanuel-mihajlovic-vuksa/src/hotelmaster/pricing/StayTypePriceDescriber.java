package hotelmaster.pricing;

/**
 * The price for a room's type.
 */
public class StayTypePriceDescriber extends PriceDescriber {

    /**
     * Create a new {@link StayTypePriceDescriber}.
     * 
     * @param description
     *            a short description
     * @param price
     *            the price
     */
    public StayTypePriceDescriber(final String description, final double price) {
        super(description, price);
    }
}
