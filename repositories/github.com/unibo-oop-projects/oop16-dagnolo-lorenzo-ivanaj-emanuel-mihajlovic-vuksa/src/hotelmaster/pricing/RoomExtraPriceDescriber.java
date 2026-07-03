package hotelmaster.pricing;


/**
 * The price for a room's extra.
 */
public class RoomExtraPriceDescriber extends RoomPriceDescriber {

    /**
     * Create a new {@link RoomExtraPriceDescriber} for a certain room extra.
     * 
     * @param description
     *            a short description
     * @param price
     *            the price
     */
    public RoomExtraPriceDescriber(final String description, final double price) {
        super(description, price);
    }
}
