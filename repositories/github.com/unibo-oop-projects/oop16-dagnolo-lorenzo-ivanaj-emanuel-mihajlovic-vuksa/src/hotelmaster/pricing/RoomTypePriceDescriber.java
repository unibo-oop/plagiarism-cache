package hotelmaster.pricing;

/**
 * The price for the stay's type.
 */
public class RoomTypePriceDescriber extends RoomPriceDescriber {

    private final double missingPrice;
    private final int maxPeople;

    /**
     * Create a new {@link RoomTypePriceDescriber}.
     * 
     * @param description
     *            a short description
     * @param price
     *            the price
     * @param missingPrice
     *            the percentage price for each missing person (based on the
     *            maxPeople value) which is multiplied by the base price
     * @param maxPeople
     *            the maximum amount of people each room of this type can
     *            contain
     */
    public RoomTypePriceDescriber(final String description, final double price, final double missingPrice,
            final int maxPeople) {
        super(description, price);
        if (maxPeople < 1) {
            throw new IllegalArgumentException("A room can accomodate at least 1 person.");
        }
        this.missingPrice = missingPrice;
        this.maxPeople = maxPeople;
    }

    /**
     * Gets the missing person price.
     * 
     * @return the price
     */
    public double getMissingPrice() {
        return this.missingPrice;
    }

    /**
     * Gets the maximum amount of people that can stay in a room of such type.
     * 
     * @return the amount of people
     */
    public int getMaxPeople() {
        return this.maxPeople;
    }
}
