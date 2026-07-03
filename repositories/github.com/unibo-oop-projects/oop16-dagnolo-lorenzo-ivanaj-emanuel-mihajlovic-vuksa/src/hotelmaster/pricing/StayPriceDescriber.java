package hotelmaster.pricing;

/**
 * A {@link PriceDescriber} for stays.
 */
public abstract class StayPriceDescriber extends PriceDescriber {

    StayPriceDescriber(final String description, final double price) {
        super(description, price);
    }

}
