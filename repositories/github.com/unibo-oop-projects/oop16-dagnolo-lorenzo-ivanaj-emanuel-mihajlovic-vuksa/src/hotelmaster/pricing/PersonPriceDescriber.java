package hotelmaster.pricing;

/**
 * The price for a type of person.
 */
public class PersonPriceDescriber extends PriceDescriber {

    /**
     * Create a new {@link PersonPriceDescriber} for a certain type of person.
     * 
     * @param description
     *            a short description
     * @param price
     *            the price
     */
    public PersonPriceDescriber(final String description, final double price) {
        super(description, price);
    }
}
