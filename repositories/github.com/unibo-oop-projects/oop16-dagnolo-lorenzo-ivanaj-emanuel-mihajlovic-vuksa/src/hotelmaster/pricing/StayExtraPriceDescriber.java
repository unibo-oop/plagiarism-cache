package hotelmaster.pricing;

/**
 * The price for a stay's extra.
 */
public class StayExtraPriceDescriber extends PriceDescriber {

    private final boolean perPerson;

    /**
     * Create a new {@link StayExtraPriceDescriber}.
     * 
     * @param description
     *            a short description
     * @param price
     *            the price
     * @param perPerson
     *            whether the extra is to be calculated on a per-person basis or
     *            not
     */
    public StayExtraPriceDescriber(final String description, final double price, final boolean perPerson) {
        super(description, price);
        this.perPerson = perPerson;
    }

    /**
     * Returns whether this {@link StayExtraPriceDescriber} is to be calculated
     * on a per-person basis or not.
     * 
     * @return the boolean
     */
    public boolean isPerPerson() {
        return this.perPerson;
    }
}
