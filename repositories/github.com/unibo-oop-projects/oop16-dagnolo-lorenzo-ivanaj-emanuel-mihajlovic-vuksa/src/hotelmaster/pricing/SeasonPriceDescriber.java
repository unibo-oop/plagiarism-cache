package hotelmaster.pricing;

import hotelmaster.utility.time.FixedPeriod;

/**
 * A season which defines a starting date and an ending date.
 */
public class SeasonPriceDescriber extends PriceDescriber {

    private final FixedPeriod seasonDuration;

    /**
     * Creates a new {@link SeasonPriceDescriber} with the given description and
     * price.
     * 
     * @param description
     *            the description
     * @param price
     *            the price
     * @param duration
     *            the duration of the season
     */
    public SeasonPriceDescriber(final String description, final double price, final FixedPeriod duration) {
        super(description, price);
        this.seasonDuration = duration;
    }

    /**
     * Returns the duration of this {@link SeasonPriceDescriber}.
     * 
     * @return the duration
     */
    public FixedPeriod getPeriod() {
        return this.seasonDuration;
    }
}
